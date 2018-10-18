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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class GraphQLLocalDate extends GraphQLScalarType {
    public static final String NAME = "Date";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public GraphQLLocalDate() {
        super("Date", "Date type", new Coercing<LocalDate, String>() {
            public String serialize(Object o) throws CoercingSerializeException {
                if (o == null) {
                    return null;
                } else {
                    return o instanceof LocalDateTime ? ((LocalDate)o).format(GraphQLLocalDate.FORMAT) : o.toString();
                }
            }

            public LocalDate parseValue(Object o) throws CoercingParseValueException {
                return LocalDate.parse((String)o, GraphQLLocalDate.FORMAT);
            }

            public LocalDate parseLiteral(Object o) throws CoercingParseLiteralException {
                if (!(o instanceof StringValue)) {
                    return null;
                } else {
                    String value = ((StringValue)o).getValue();
                    return LocalDate.parse(value, GraphQLLocalDate.FORMAT);
                }
            }
        });
    }
}
