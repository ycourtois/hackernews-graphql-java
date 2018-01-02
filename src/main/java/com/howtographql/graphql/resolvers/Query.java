package com.howtographql.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.howtographql.graphql.filter.LinkFilter;
import com.howtographql.graphql.type.Link;
import com.howtographql.repositories.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/26/2017
 */
@RequiredArgsConstructor
@Component
public class Query implements GraphQLQueryResolver {

    private final LinkRepository linkRepository;

    public List<Link> allLinks(LinkFilter filter, int skip, int limit) {
        return linkRepository.findAllLinks(filter, skip, limit);
    }

    public Link findByID(String id) {
        return linkRepository.findOne(id);
    }

}
