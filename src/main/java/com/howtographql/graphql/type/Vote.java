package com.howtographql.graphql.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/29/2017
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Vote {

    private String id;
    private final ZonedDateTime createdAt;
    private final String userId;
    private final String linkId;

}
