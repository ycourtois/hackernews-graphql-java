package com.howtographql.graphql.scalar;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/29/2017
 */
@Slf4j
@Component
public class DateTimeCoercing implements Coercing<ZonedDateTime, String> {

    @Override
    public String serialize(Object input) {
        //serialize the ZonedDateTime into string on the way out
        return ((ZonedDateTime) input).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    @Override
    public ZonedDateTime parseValue(Object input) {
        log.warn("Unable to parse input object : {}", input);
        return null;
    }

    @Override
    public ZonedDateTime parseLiteral(Object input) {
        //parse the string values coming in
        if (input instanceof StringValue) {
            return ZonedDateTime.parse(((StringValue) input).getValue());
        } else {
            return null;
        }
    }
}
