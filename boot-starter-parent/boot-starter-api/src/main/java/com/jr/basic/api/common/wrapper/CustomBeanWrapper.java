//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.common.wrapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.BeanWrapperImpl;

public class CustomBeanWrapper extends BeanWrapperImpl {
    public CustomBeanWrapper(Object object) {
        super(object);
        this.registerDefaultEditors();
        this.registerCustomEditor(LocalDateTime.class, new LocalDateTimePropertyEditor());
        this.registerCustomEditor(LocalDate.class, new LocalDatePropertyEditor());
    }
}
