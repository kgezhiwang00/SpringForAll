//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.directive;

import com.jr.basic.meta.service.AuthenticateService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticateDirective implements SchemaDirectiveWiring {
    @Autowired
    private AuthenticateService authenticateService;

    public AuthenticateDirective() {
    }

    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        final List<String> targetRoles = (List)environment.getDirective().getArgument("roles").getValue();
        GraphQLFieldDefinition field = (GraphQLFieldDefinition)environment.getElement();
        final DataFetcher originalFetcher = field.getDataFetcher();
        DataFetcher authDataFetcher = new DataFetcher() {
            public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
                Map<String, Object> contextMap = (Map)dataFetchingEnvironment.getContext();
                AuthenticateService authenticateService = (AuthenticateService)contextMap.get("authenticateService");
                return authenticateService.currentUserHasRole((String[])targetRoles.toArray(new String[targetRoles.size()])).booleanValue() ? originalFetcher.get(dataFetchingEnvironment) : null;
            }
        };
        return field.transform((builder) -> {
            builder.dataFetcher(authDataFetcher);
        });
    }
}
