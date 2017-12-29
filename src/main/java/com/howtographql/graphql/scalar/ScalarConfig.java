package com.howtographql.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/29/2017
 */
@Configuration
public class ScalarConfig {

    @Bean
    public GraphQLScalarType scalarDateTime(DateTimeCoercing dateTimeCoercing) {
        return new GraphQLScalarType("DateTime", "DataTime scalar", dateTimeCoercing);
    }
}
