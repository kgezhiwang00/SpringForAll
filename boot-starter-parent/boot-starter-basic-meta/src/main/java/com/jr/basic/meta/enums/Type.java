//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.enums;

public enum Type {
    STRING("字符串"),
    INT("整数"),
    LONG("长整"),
    FLOAT("浮点"),
    DOUBLE("双精度"),
    DATE("日期"),
    DATETIME("日期时间"),
    TIME("时间"),
    BOOLEAN("布尔"),
    DECIMAL("高精度"),
    BIGINT("大数"),
    STRINGARRAY("字符集合"),
    INTARRAY("整数集合"),
    ENUM("枚举"),
    DOMAIN("域对象");

    String label;

    private Type(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }
}
