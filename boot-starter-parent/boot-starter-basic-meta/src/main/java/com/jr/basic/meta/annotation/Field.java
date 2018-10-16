//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.annotation;

import com.jr.basic.meta.enums.Aggregate;
import com.jr.basic.meta.enums.UIType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Field {
    boolean isKey() default false;

    String name() default "";

    String label() default "";

    String columnName() default "";

    String[] validateType() default {};

    String defaultValue() default "";

    boolean isVirtual() default false;

    boolean isRequired() default false;

    boolean isUnique() default false;

    UIType[] uiType() default {};

    int colSpan() default 6;

    int rowSpan() default 1;

    boolean isQuery() default false;

    boolean isSort() default false;

    boolean isRangeQuery() default false;

    String[] queryRole() default {};

    int order() default 5;

    boolean isList() default true;

    String[] listRole() default {};

    boolean isCreate() default true;

    String[] createRole() default {};

    boolean isUpdate() default true;

    String[] updateRole() default {};

    String queryType() default "eq";

    String remark() default "";

    int importNum() default -1;

    Aggregate[] aggregates() default {};

    boolean isGroup() default false;
}
