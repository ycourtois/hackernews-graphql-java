package com.howtographql.graphql.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Iterables;
import graphql.ExceptionWhileDataFetching;
import graphql.execution.ExecutionPath;

/**
 * This wrapper doesn’t do much - it just instructs Jackson (the JSON (de)serialization library) to ignore the linked
 * exception during serialization. This way, the stack trace won’t reach the client.
 *
 * @author yann.courtois@ippon.fr
 * @since 12/29/2017
 */
public class SanitizedError extends ExceptionWhileDataFetching {
    public SanitizedError(ExceptionWhileDataFetching inner) {
        super(ExecutionPath.fromList(inner.getPath()), inner.getException(),
                Iterables.getOnlyElement(inner.getLocations()));
    }

    @Override
    @JsonIgnore
    public Throwable getException() {
        return super.getException();
    }
}
