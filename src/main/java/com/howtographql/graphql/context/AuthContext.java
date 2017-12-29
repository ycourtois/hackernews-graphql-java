package com.howtographql.graphql.context;

import com.howtographql.graphql.type.User;
import graphql.servlet.GraphQLContext;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/28/2017
 */
@Getter
public class AuthContext extends GraphQLContext {
    private final User user;

    public AuthContext(User user, Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        super(request, response);
        this.user = user;
    }
}
