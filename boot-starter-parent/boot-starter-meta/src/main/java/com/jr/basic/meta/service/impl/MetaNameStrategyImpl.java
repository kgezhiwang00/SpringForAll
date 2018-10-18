//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.service.impl;

import com.google.common.base.CaseFormat;
import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.service.MetaNameStrategy;
import org.apache.commons.lang3.StringUtils;

public class MetaNameStrategyImpl implements MetaNameStrategy {
    private static final String PACKAGE_SPLIT = ".";

    public MetaNameStrategyImpl() {
    }

    public String domainClassToName(Class<Domain> clazz) {
        return StringUtils.uncapitalize(clazz.getSimpleName());
    }

    public String domainClassToTableName(Class<Domain> clazz) {
        return this.camelToUnderscore(clazz.getSimpleName());
    }

    public String domainClassToModuleName(Class<Domain> clazz) {
        String fullName = clazz.getName();
        String module = fullName.substring(fullName.lastIndexOf(".") + 1);
        return module.toUpperCase();
    }

    public String fieldNameToName(String fieldName) {
        return fieldName;
    }

    protected String camelToUnderscore(String input) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, input);
    }

    public String fieldNameToColumnName(String fieldName) {
        return this.camelToUnderscore(fieldName);
    }
}
