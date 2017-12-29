package com.howtographql.repositories;

import com.howtographql.graphql.type.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/29/2017
 */
public interface VoteRepository extends MongoRepository<Vote, String> {

    List<Vote> findByUserId(String userId);

    List<Vote> findByLinkId(String linkId);

}
