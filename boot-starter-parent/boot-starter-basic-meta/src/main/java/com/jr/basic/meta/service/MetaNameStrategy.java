//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.service;

public interface MetaNameStrategy {
    String domainClassToName(Class<?> var1);

    String domainClassToTableName(Class<?> var1);

    String domainClassToModuleName(Class<?> var1);

    String fieldNameToName(String var1);

    String fieldNameToColumnName(String var1);
}
