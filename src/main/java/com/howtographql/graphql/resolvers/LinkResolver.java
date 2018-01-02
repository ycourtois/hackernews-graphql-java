package com.howtographql.graphql.resolvers;

import com.howtographql.graphql.type.Link;
import com.howtographql.graphql.type.User;
import com.howtographql.repositories.UserRepository;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/28/2017
 */
@Component
@RequiredArgsConstructor
// We need it because "postedby" does not match any class property inside Link
public class LinkResolver {

    private final UserRepository userRepository;

    @GraphQLQuery
    public User postedBy(@GraphQLContext Link link) {
        if (link.getUserId() == null) {
            return null;
        }
        return userRepository.findOne(link.getUserId());
    }
}
