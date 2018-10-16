//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.spring.push.Util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class TspApplicationContext implements ApplicationContextAware {
    private static ApplicationContext _applicationContext;

    public TspApplicationContext() {
    }

    public static void initTspApplicationContext(ApplicationContext applicationContext) {
        if (_applicationContext == null) {
            _applicationContext = applicationContext;
        }

    }

    public static Object getBean(String beanid) {
        Object bean = null;
        if (null == _applicationContext) {
            throw new RuntimeException("Spring ApplicationContext为空");
        } else {
            try {
                bean = _applicationContext.getBean(beanid);
                return bean;
            } catch (Exception var3) {
                throw new RuntimeException("服务器中无法找到Spring Bean [beanid:" + beanid + "]。", var3);
            }
        }
    }

    public static Object getBean(Class clazz) {
        Object bean = null;
        if (null == _applicationContext) {
            throw new RuntimeException("Spring ApplicationContext为空");
        } else {
            try {
                bean = _applicationContext.getBean(clazz);
                return bean;
            } catch (Exception var3) {
                throw new RuntimeException("服务器中无法找到Spring Bean [" + clazz.getName() + "]。", var3);
            }
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initTspApplicationContext(applicationContext);
    }
}
