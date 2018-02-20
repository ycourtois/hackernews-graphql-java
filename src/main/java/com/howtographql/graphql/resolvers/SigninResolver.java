package com.howtographql.graphql.resolvers;

import com.howtographql.graphql.type.SigninPayload;
import com.howtographql.graphql.type.User;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/28/2017
 */
// Useless because SigningPayload already has a user field
public class SigninResolver { // implements GraphQLResolver<SigninPayload> {

    public User user(SigninPayload payload) {
        return payload.getUser();
    }
}
