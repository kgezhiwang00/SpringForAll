//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.query.converter;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

public class LocalDateConverter extends PropertyEditorSupport implements Converter<String, LocalDate> {
    public LocalDateConverter() {
    }

    public LocalDate convert(String source) {
        return source != null && !"".equals(source) ? LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(this.convert(text));
    }
}
