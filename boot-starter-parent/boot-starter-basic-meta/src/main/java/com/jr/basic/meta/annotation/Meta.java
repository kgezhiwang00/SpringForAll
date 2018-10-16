//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.annotation;

import com.jr.basic.meta.enums.OrderType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Meta {
    String module() default "";

    String name() default "";

    String label() default "";

    String table() default "";

    boolean allowDynamic() default true;

    boolean allowCreate() default true;

    boolean allowUpdate() default true;

    boolean allowDelete() default true;

    boolean enable() default true;

    boolean isCache() default false;

    boolean isLogicDelete() default false;

    String owner() default "";

    String showField() default "";

    String viewType() default "";

    String[] listRole() default {};

    String[] createRole() default {};

    String[] updateRole() default {};

    String[] deleteRole() default {};

    String remark() default "";

    String[] defaultOrder() default {};

    OrderType[] defaultOrderType() default {};
}
