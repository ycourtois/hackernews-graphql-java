package com.howtographql.graphql.type;

import lombok.*;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/26/2017
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    private String id;
    @NonNull
    private String url;
    @NonNull
    private String description;
    @NonNull
    private String userId;
}
