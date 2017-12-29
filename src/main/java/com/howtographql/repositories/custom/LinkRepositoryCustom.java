package com.howtographql.repositories.custom;

import com.howtographql.graphql.filter.LinkFilter;
import com.howtographql.graphql.type.Link;

import java.util.List;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/29/2017
 */
public interface LinkRepositoryCustom {
    List<Link> findAllLinks(LinkFilter filter);
}
