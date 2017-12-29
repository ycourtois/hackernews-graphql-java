package com.howtographql.graphql.type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/28/2017
 */
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private String id;
    @NonNull
    private final String name;
    @NonNull
    private final String email;
    @NonNull
    private final String password;
}
