//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.vo;

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class SysApiParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String type;
    private String remark;
    private Boolean required;
    private Integer orderNum;
    private String defaultValue;

    @ConstructorProperties({"name", "type", "remark", "required", "orderNum", "defaultValue"})
    SysApiParam(String name, String type, String remark, Boolean required, Integer orderNum, String defaultValue) {
        this.name = name;
        this.type = type;
        this.remark = remark;
        this.required = required;
        this.orderNum = orderNum;
        this.defaultValue = defaultValue;
    }

    public static SysApiParam.SysApiParamBuilder builder() {
        return new SysApiParam.SysApiParamBuilder();
    }

    public SysApiParam.SysApiParamBuilder toBuilder() {
        return (new SysApiParam.SysApiParamBuilder()).name(this.name).type(this.type).remark(this.remark).required(this.required).orderNum(this.orderNum).defaultValue(this.defaultValue);
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getRemark() {
        return this.remark;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String toString() {
        return "SysApiParam(name=" + this.getName() + ", type=" + this.getType() + ", remark=" + this.getRemark() + ", required=" + this.getRequired() + ", orderNum=" + this.getOrderNum() + ", defaultValue=" + this.getDefaultValue() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SysApiParam)) {
            return false;
        } else {
            SysApiParam other = (SysApiParam)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof SysApiParam;
    }



    public static class SysApiParamBuilder {
        private String name;
        private String type;
        private String remark;
        private Boolean required;
        private Integer orderNum;
        private String defaultValue;

        SysApiParamBuilder() {
        }

        public SysApiParam.SysApiParamBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SysApiParam.SysApiParamBuilder type(String type) {
            this.type = type;
            return this;
        }

        public SysApiParam.SysApiParamBuilder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public SysApiParam.SysApiParamBuilder required(Boolean required) {
            this.required = required;
            return this;
        }

        public SysApiParam.SysApiParamBuilder orderNum(Integer orderNum) {
            this.orderNum = orderNum;
            return this;
        }

        public SysApiParam.SysApiParamBuilder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public SysApiParam build() {
            return new SysApiParam(this.name, this.type, this.remark, this.required, this.orderNum, this.defaultValue);
        }

        public String toString() {
            return "SysApiParam.SysApiParamBuilder(name=" + this.name + ", type=" + this.type + ", remark=" + this.remark + ", required=" + this.required + ", orderNum=" + this.orderNum + ", defaultValue=" + this.defaultValue + ")";
        }
    }
}
