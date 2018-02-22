package com.howtographql.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.howtographql.repositories.LinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/27/2017
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class MutationLinkResolver implements GraphQLMutationResolver {

    private final LinkRepository linkRepository;

    public String deleteLink(String linkId) {
        linkRepository.delete(linkId);
        return linkId;
    }

}
