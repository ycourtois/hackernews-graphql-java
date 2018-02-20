package com.howtographql.graphql.config;

import com.howtographql.graphql.context.AuthContext;
import com.howtographql.graphql.resolvers.Query;
import com.howtographql.graphql.type.User;
import com.howtographql.repositories.UserRepository;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.servlet.GraphQLContextBuilder;
import graphql.servlet.GraphQLServlet;
import graphql.servlet.SimpleGraphQLServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/28/2017
 */
@Configuration
public class GraphqlConfig {

    // Override context with a customized one
    @Bean
    public GraphQLContextBuilder graphQLContextBuilder(UserRepository userRepository) {
        return (request, resp) -> {
            User user = request
                    .map(req -> req.getHeader("Authorization"))
                    .filter(id -> !id.isEmpty())
                    .map(id -> id.replace("Bearer ", ""))
                    .map(userRepository::findOne)
                    .orElse(null);

            return new AuthContext(user, request, resp);
        };
    }

    // all this section should be handled by spring boot starter auto configuration
    @Bean
    public GraphQLServlet graphQLServlet(Query query) throws URISyntaxException {

        final Path schemaPath = Paths.get(getClass().getResource("/schema.graphqls").toURI());
        SchemaParser schemaParser = new SchemaParser();
        final TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schemaPath.toFile());

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        final RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                        typeWiring.dataFetcher("allLinks", dfe -> query.allLinksNoArgs()))
                .build();

        final GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

        return SimpleGraphQLServlet
                .builder(graphQLSchema)
                .build();
    }

    // register endpoint
    @Bean
    ServletRegistrationBean graphQLServletRegistrationBean(GraphQLServlet servlet) {
        return new ServletRegistrationBean(servlet, "/graphql");
    }

}
