package com.howtographql.repositories;

import com.howtographql.graphql.type.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/28/2017
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
