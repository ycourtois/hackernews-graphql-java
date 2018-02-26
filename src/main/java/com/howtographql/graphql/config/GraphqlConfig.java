package com.howtographql.graphql.config;

import com.howtographql.graphql.context.AuthContext;
import com.howtographql.graphql.resolvers.LinkResolver;
import com.howtographql.graphql.resolvers.RootQueryResolver;
import com.howtographql.graphql.type.User;
import com.howtographql.repositories.UserRepository;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
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
    public GraphQLServlet graphQLServlet(RootQueryResolver rootQueryResolver, LinkResolver linkResolver) throws
            URISyntaxException {

        // 1. retrieve the schema files in classpath resources
        final Path schemaLinkPath = Paths.get(getClass().getResource("/schema_link.graphqls").toURI());
        final Path schemaArticlePath = Paths.get(getClass().getResource("/schema_article.graphqls").toURI());
        // 2. create a schema parser
        SchemaParser schemaParser = new SchemaParser();
        final TypeDefinitionRegistry tdrLinkSchema = schemaParser.parse(schemaLinkPath.toFile());
        final TypeDefinitionRegistry tdrArticleSchema = schemaParser.parse(schemaArticlePath.toFile());

        final TypeDefinitionRegistry typeDefinitionRegistry = tdrLinkSchema.merge(tdrArticleSchema);

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        // 3. specify the root query and the resolve function
        final RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                        typeWiring
                                .dataFetcher("allLinks", dfe -> rootQueryResolver.allLinksNoArgs())
                                .dataFetcher("allArticles", dfe -> rootQueryResolver.allArticles()))
                .type("Link", typeWiring ->
                        typeWiring.dataFetcher("createdBy", dfe -> linkResolver.createdBy(dfe.getSource())))
                .build();

        // 4. create schema instance
        final GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

        // 5. create servlet
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
