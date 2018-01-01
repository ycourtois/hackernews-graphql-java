package com.howtographql.repositories.custom.impl;

import com.howtographql.graphql.filter.LinkFilter;
import com.howtographql.graphql.type.Link;
import com.howtographql.repositories.custom.LinkRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/29/2017
 */
@RequiredArgsConstructor
public class LinkRepositoryImpl implements LinkRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Link> findAllLinks(LinkFilter filter) {
        final Optional<Criteria> criteria = Optional.ofNullable(filter).map(this::buildCriteria);

        Query query = new Query();
        criteria.map(query::addCriteria).orElse(query);

        return mongoTemplate.find(query, Link.class);
    }

    private Criteria buildCriteria(LinkFilter linkFilter) {
        String descriptionPattern = linkFilter.getDescriptionContains();
        String urlPattern = linkFilter.getUrlContains();
        Criteria descriptionCondition = null;
        Criteria urlCondition = null;

        if (descriptionPattern != null && !descriptionPattern.isEmpty()) {
            descriptionCondition = Criteria.where("description").regex(".*" + descriptionPattern + ".*", "i");
        }

        if (urlPattern != null && !urlPattern.isEmpty()) {
            urlCondition = Criteria.where("url").regex(".*" + urlPattern + ".*", "i");
        }

        if (descriptionCondition != null && urlCondition != null) {
            return descriptionCondition.andOperator(urlCondition);
        }
        return descriptionCondition != null ? descriptionCondition : urlCondition;
    }
}
