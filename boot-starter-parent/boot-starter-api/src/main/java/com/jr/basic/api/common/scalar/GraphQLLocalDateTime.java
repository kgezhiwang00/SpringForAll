//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.common.scalar;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class GraphQLLocalDateTime extends GraphQLScalarType {
    public static final String NAME = "DateTime";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public GraphQLLocalDateTime() {
        super("DateTime", "DateTime type", new Coercing<LocalDateTime, String>() {
            public String serialize(Object o) throws CoercingSerializeException {
                if (o == null) {
                    return null;
                } else {
                    return o instanceof LocalDateTime ? ((LocalDateTime)o).format(GraphQLLocalDateTime.FORMAT) : o.toString();
                }
            }

            public LocalDateTime parseValue(Object o) throws CoercingParseValueException {
                return LocalDateTime.parse((String)o, GraphQLLocalDateTime.FORMAT);
            }

            public LocalDateTime parseLiteral(Object o) throws CoercingParseLiteralException {
                if (!(o instanceof StringValue)) {
                    return null;
                } else {
                    String value = ((StringValue)o).getValue();
                    return LocalDateTime.parse(value, GraphQLLocalDateTime.FORMAT);
                }
            }
        });
    }
}
