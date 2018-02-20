package com.howtographql.graphql.config;

import com.coxautodev.graphql.tools.SchemaParser;
import com.howtographql.graphql.context.AuthContext;
import com.howtographql.graphql.resolvers.LinkResolver;
import com.howtographql.graphql.resolvers.Mutation;
import com.howtographql.graphql.resolvers.Query;
import com.howtographql.graphql.resolvers.VoteResolver;
import com.howtographql.graphql.type.User;
import com.howtographql.repositories.UserRepository;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContextBuilder;
import graphql.servlet.GraphQLServlet;
import graphql.servlet.SimpleGraphQLServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public GraphQLServlet graphQLServlet(Query query, Mutation mutation,
                                         LinkResolver linkResolver,
                                         VoteResolver voteResolver,
                                         GraphQLScalarType scalarDateTime) {

        final GraphQLSchema graphQLSchema =
                SchemaParser.newParser()
                        .file("schema.graphqls")
                        .resolvers(query, mutation) // root resolvers
                        .resolvers(linkResolver, voteResolver) // fields resolvers
                        .scalars(scalarDateTime)
                        .build()
                        .makeExecutableSchema();

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
