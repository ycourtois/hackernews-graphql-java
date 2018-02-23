package com.howtographql.graphql.type;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/26/2017
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "id")
public class Link {
    @Id
    private String id;
    @NonNull
    private String url;
    @NonNull
    private String description;
    @DBRef
    private Article article;
//    @NonNull
//    private String userId;


    public Link(String url, String description, Article article) {
        this.url = url;
        this.description = description;
        this.article = article;
    }
}
