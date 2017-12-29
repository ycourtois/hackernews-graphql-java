package com.howtographql.repositories.custom.impl;

import com.howtographql.graphql.filter.LinkFilter;
import com.howtographql.graphql.type.Link;
import com.howtographql.repositories.custom.LinkRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.regex;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/29/2017
 */
@RequiredArgsConstructor
public class LinkRepositoryImpl implements LinkRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Link> findAllLinks(LinkFilter filter) {
        Optional<Bson> mongoFilter = Optional.ofNullable(filter).map(this::buildFilter);

        // mongoTemplate.find(mongoFilter ,Link.class)

        return null;
    }

    //builds a Bson from a LinkFilter
    private Bson buildFilter(LinkFilter filter) {
        String descriptionPattern = filter.getDescriptionContains();
        String urlPattern = filter.getUrlContains();
        Bson descriptionCondition = null;
        Bson urlCondition = null;
        if (descriptionPattern != null && !descriptionPattern.isEmpty()) {
            descriptionCondition = regex("description", ".*" + descriptionPattern + ".*", "i");
        }
        if (urlPattern != null && !urlPattern.isEmpty()) {
            urlCondition = regex("url", ".*" + urlPattern + ".*", "i");
        }
        if (descriptionCondition != null && urlCondition != null) {
            return and(descriptionCondition, urlCondition);
        }
        return descriptionCondition != null ? descriptionCondition : urlCondition;
    }
}
