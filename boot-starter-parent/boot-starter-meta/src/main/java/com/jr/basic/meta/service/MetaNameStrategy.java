//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.service;

import com.jr.basic.meta.domain.Domain;

public interface MetaNameStrategy {
    String domainClassToName(Class<Domain> var1);

    String domainClassToTableName(Class<Domain> var1);

    String domainClassToModuleName(Class<Domain> var1);

    String fieldNameToName(String var1);

    String fieldNameToColumnName(String var1);
}
