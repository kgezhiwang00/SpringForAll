//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.common.wrapper;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;

public class LocalDateTimePropertyEditor extends PropertyEditorSupport {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LocalDateTimePropertyEditor() {
    }

    public String getAsText() {
        Object value = this.getValue();
        return value == null ? null : FORMAT.format((LocalDateTime)value);
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isNotBlank(text)) {
            super.setValue(LocalDateTime.parse(text, FORMAT));
        } else {
            this.setValue((Object)null);
        }

    }
}
