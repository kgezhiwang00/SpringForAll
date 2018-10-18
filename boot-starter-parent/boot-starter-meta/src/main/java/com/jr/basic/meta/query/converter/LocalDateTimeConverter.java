//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.query.converter;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

public class LocalDateTimeConverter extends PropertyEditorSupport implements Converter<String, LocalDateTime> {
    public LocalDateTimeConverter() {
    }

    public LocalDateTime convert(String source) {
        return source != null && !"".equals(source) ? LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(this.convert(text));
    }
}
