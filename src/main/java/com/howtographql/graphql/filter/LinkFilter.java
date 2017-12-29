package com.howtographql.graphql.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/29/2017
 */
@Getter
@Setter
public class LinkFilter {
    @JsonProperty("description_contains") //the name must match the schema
    private String descriptionContains;
    @JsonProperty("url_contains")
    private String urlContains;
}
