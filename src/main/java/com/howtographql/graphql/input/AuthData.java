package com.howtographql.graphql.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/28/2017
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthData {
    private String email;
    private String password;
}

