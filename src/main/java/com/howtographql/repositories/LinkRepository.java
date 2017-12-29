package com.howtographql.repositories;

import com.howtographql.graphql.type.Link;
import com.howtographql.repositories.custom.LinkRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/26/2017
 */
public interface LinkRepository extends MongoRepository<Link, String>, LinkRepositoryCustom {
}