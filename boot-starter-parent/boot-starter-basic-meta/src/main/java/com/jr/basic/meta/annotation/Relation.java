//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.annotation;

import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.domain.MetaRelation.Type;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Relation {
    Type type();

    String joinField();

    Class<? extends Domain> domain();

    String showField() default "";

    String remark() default "";
}
