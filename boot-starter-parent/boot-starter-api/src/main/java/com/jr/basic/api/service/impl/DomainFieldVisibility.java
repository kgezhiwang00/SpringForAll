//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.impl;

import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.domain.MetaField;
import com.jr.basic.meta.service.AuthenticateService;
import com.jr.basic.meta.service.MetaService;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLFieldsContainer;
import graphql.schema.visibility.GraphqlFieldVisibility;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class DomainFieldVisibility implements GraphqlFieldVisibility {
    private static final String QUERY_TYPE = "QueryType";
    private static final String MUTATION_TYPE = "MutationType";
    @Autowired
    private MetaService metaService;
    @Autowired(
        required = false
    )
    private AuthenticateService authenticateService;

    public DomainFieldVisibility() {
    }

    public List<GraphQLFieldDefinition> getFieldDefinitions(GraphQLFieldsContainer graphQLFieldsContainer) {
        return graphQLFieldsContainer.getFieldDefinitions();
    }

    public GraphQLFieldDefinition getFieldDefinition(GraphQLFieldsContainer graphQLFieldsContainer, String s) {
        String name = graphQLFieldsContainer.getName();
        if (!"QueryType".equals(name) && !"MutationType".equals(name)) {
            MetaDomain meta = this.metaService.getByName(name);
            if (meta != null) {
                if (!meta.getAllowDynamic().booleanValue()) {
                    return null;
                }

                MetaField field = meta.getField(s);
                if (field == null) {
                    return null;
                }

                if (field.getListRole().length > 0 && this.authenticateService != null && !this.authenticateService.currentUserHasRole(field.getListRole()).booleanValue()) {
                    return null;
                }
            }

            return graphQLFieldsContainer.getFieldDefinition(s);
        } else {
            return graphQLFieldsContainer.getFieldDefinition(s);
        }
    }
}
