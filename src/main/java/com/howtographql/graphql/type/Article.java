package com.howtographql.graphql.type;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "id")
public class Article {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String writtenBy;
    @DBRef
    private Link link;
}