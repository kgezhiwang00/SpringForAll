//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta;

import com.jr.basic.meta.annotation.EnableMeta;
import com.jr.basic.meta.annotation.Meta;
import com.jr.basic.meta.service.impl.MetaServiceImpl;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public class MetaScanRegistrar implements ImportBeanDefinitionRegistrar {
    public MetaScanRegistrar() {
    }

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Set<String> packagesToScan = this.getPackagesToScan(importingClassMetadata);
        Set<Class<?>> allClassesList = new LinkedHashSet();
        packagesToScan.forEach((pkg) -> {
            Reflections reflections = new Reflections(pkg, new Scanner[0]);
            Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(Meta.class);
            allClassesList.addAll(classesList);
        });
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MetaServiceImpl.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(allClassesList);
        beanDefinition.setRole(2);
        beanDefinition.setSynthetic(true);
        registry.registerBeanDefinition("metaService", beanDefinition);
    }

    private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(EnableMeta.class.getName()));
        String[] basePackages = attributes.getStringArray("value");
        Set<String> packagesToScan = new LinkedHashSet();
        packagesToScan.addAll(Arrays.asList(basePackages));
        Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");
        Class[] var6 = basePackageClasses;
        int var7 = basePackageClasses.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Class<?> basePackageClass = var6[var8];
            packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
        }

        Assert.notEmpty(packagesToScan, " need to set basePackages for enableMeta");
        return packagesToScan;
    }
}
