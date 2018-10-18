//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

public final class ServiceUtils implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    public ServiceUtils() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static boolean isInitialize() {
        return CONTEXT != null;
    }

    public static <T> T getService(Class<T> clazz) {
        Assert.notNull(CONTEXT, "no application");

        try {
            return CONTEXT.getBean(clazz);
        } catch (Exception var2) {
            return null;
        }
    }
}
