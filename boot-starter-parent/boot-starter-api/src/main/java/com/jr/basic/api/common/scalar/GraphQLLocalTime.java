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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GraphQLLocalTime extends GraphQLScalarType {
    public static final String NAME = "Time";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public GraphQLLocalTime() {
        super("Time", "Time type", new Coercing<LocalTime, String>() {
            public String serialize(Object o) throws CoercingSerializeException {
                if (o == null) {
                    return null;
                } else {
                    return o instanceof LocalTime ? ((LocalTime)o).format(GraphQLLocalTime.FORMAT) : o.toString();
                }
            }

            public LocalTime parseValue(Object o) throws CoercingParseValueException {
                return LocalTime.parse((String)o);
            }

            public LocalTime parseLiteral(Object o) throws CoercingParseLiteralException {
                if (!(o instanceof StringValue)) {
                    return null;
                } else {
                    String value = ((StringValue)o).getValue();
                    return LocalTime.parse(value);
                }
            }
        });
    }
}
