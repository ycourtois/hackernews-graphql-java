package com.howtographql.graphql.config;

import com.howtographql.graphql.context.AuthContext;
import com.howtographql.graphql.resolvers.LinkResolver;
import com.howtographql.graphql.resolvers.Mutation;
import com.howtographql.graphql.resolvers.Query;
import com.howtographql.graphql.type.User;
import com.howtographql.repositories.UserRepository;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContextBuilder;
import io.leangen.graphql.GraphQLSchemaGenerator;
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

    @Bean
    public GraphQLSchema buildSchema(Query query, Mutation mutation, LinkResolver linkResolver) {
        return new GraphQLSchemaGenerator()
                .withOperationsFromSingleton(query)
                .withOperationsFromSingleton(mutation)
                .withOperationsFromSingleton(linkResolver)
                .generate();
    }
}
