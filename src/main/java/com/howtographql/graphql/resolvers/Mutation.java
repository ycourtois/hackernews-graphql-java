package com.howtographql.graphql.resolvers;

import com.howtographql.graphql.context.AuthContext;
import com.howtographql.graphql.exception.UserNotFoundException;
import com.howtographql.graphql.input.AuthData;
import com.howtographql.graphql.type.Link;
import com.howtographql.graphql.type.SigninPayload;
import com.howtographql.graphql.type.User;
import com.howtographql.graphql.type.Vote;
import com.howtographql.repositories.LinkRepository;
import com.howtographql.repositories.UserRepository;
import com.howtographql.repositories.VoteRepository;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/27/2017
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class Mutation { // implements GraphQLMutationResolver {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public Link createLink(String url, String description, DataFetchingEnvironment env) {
        AuthContext authContext = env.getContext();
//        authContext.getUser().getId()
        Link newLink = new Link(url, description, "0");
        linkRepository.save(newLink);
        return newLink;
    }

    public User createUser(String name, AuthData authData) {
        User newUser = new User(name, authData.getEmail(), authData.getPassword());
        return userRepository.save(newUser);
    }

    public SigninPayload signinUser(AuthData auth) {
        User user = userRepository.findByEmail(auth.getEmail());
        if (user == null) {
            throw new UserNotFoundException("Unable to find a user with specified email", auth.getEmail());
        }

        if (user.getPassword().equals(auth.getPassword())) {
            return new SigninPayload(user.getId(), user);
        }
        throw new GraphQLException("Invalid credentials");
    }

    public Vote createVote(String linkId, String userId) {
        ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
        return voteRepository.save(new Vote(now, userId, linkId));
    }
}
