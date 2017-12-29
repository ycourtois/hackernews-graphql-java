package com.howtographql.graphql.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/28/2017
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SigninPayload {
    private final String token;
    private final User user;
}
