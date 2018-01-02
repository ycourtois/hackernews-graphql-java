package com.howtographql.graphql.resolvers;

import com.howtographql.graphql.type.Link;
import com.howtographql.graphql.type.User;
import com.howtographql.graphql.type.Vote;
import com.howtographql.repositories.LinkRepository;
import com.howtographql.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/29/2017
 */
@Component
@RequiredArgsConstructor
public class VoteResolver {// implements GraphQLResolver<Vote> {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public User user(Vote vote) {
        return userRepository.findOne(vote.getUserId());
    }

    public Link link(Vote vote) {
        return linkRepository.findOne(vote.getLinkId());
    }
}
