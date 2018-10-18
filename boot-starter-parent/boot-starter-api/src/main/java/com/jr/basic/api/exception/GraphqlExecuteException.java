//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.exception;

import com.google.common.base.Preconditions;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import java.util.Iterator;
import java.util.List;

public class GraphqlExecuteException extends RuntimeException {
    private List<GraphQLError> errors;

    public GraphqlExecuteException(List<GraphQLError> errors) {
        Preconditions.checkArgument(errors != null && errors.size() > 0, "graphqlError size must not empty");
        this.errors = errors;
    }

    public String getMessage() {
        StringBuffer buffer = new StringBuffer();

        for(Iterator var2 = this.errors.iterator(); var2.hasNext(); buffer.append("\n")) {
            GraphQLError error = (GraphQLError)var2.next();
            if (error instanceof ExceptionWhileDataFetching) {
                ExceptionWhileDataFetching ex = (ExceptionWhileDataFetching)error;
                buffer.append(ex.getException().getMessage());
            } else {
                buffer.append(error.getMessage());
            }
        }

        return buffer.toString();
    }
}
