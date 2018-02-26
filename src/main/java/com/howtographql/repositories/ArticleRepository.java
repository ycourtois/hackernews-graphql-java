package com.howtographql.repositories;

import com.howtographql.graphql.type.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yann.courtois@ippon.fr
 * @since 2/23/2018
 */
public interface ArticleRepository extends MongoRepository<Article, String> {
}
