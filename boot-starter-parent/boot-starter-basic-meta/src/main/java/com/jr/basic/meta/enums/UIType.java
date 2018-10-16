//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.enums;

public enum UIType {
    TEXT("文本框"),
    TEXTAREA("文本域"),
    BOOL("布尔"),
    SELECT("下拉框"),
    COMBOX("输入下拉框"),
    DATETIME("日期时间"),
    DATE("日期"),
    TIME("时间"),
    POSITION("省市三级"),
    NUMBER("数字框"),
    ENUM("枚举选择"),
    DOMAINSELECT("域对象选择"),
    IMAGE("图片"),
    IMAGES("图片组"),
    VIDEO("视频"),
    HIDDEN("隐藏域");

    String label;

    private UIType(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }
}
