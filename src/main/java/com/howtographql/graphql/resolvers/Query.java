package com.howtographql.graphql.resolvers;

import com.howtographql.graphql.filter.LinkFilter;
import com.howtographql.graphql.type.Link;
import com.howtographql.repositories.LinkRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/26/2017
 */
@RequiredArgsConstructor
@Component
public class Query {

    private final LinkRepository linkRepository;

    @GraphQLQuery(description = "list all links")
    public List<Link> allLinks(LinkFilter filter,
                               @GraphQLArgument(name = "skip", defaultValue = "0") int skip,
                               @GraphQLArgument(name = "limit", defaultValue = "0") int limit) {
        return linkRepository.findAllLinks(filter, skip, limit);
    }

    @GraphQLQuery
    public Link findByID(@GraphQLArgument(name = "id") String id) {
        return linkRepository.findOne(id);
    }

}
