//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.vo;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.List;

public class ValueObject implements Serializable {
    private String label;
    private String name;
    private Class<?> domainClass;
    private String domainName;
    private Boolean isState;
    private String remark;
    private List<ValueField> fields;

    @ConstructorProperties({"label", "name", "domainClass", "domainName", "isState", "remark", "fields"})
    ValueObject(String label, String name, Class<?> domainClass, String domainName, Boolean isState, String remark, List<ValueField> fields) {
        this.label = label;
        this.name = name;
        this.domainClass = domainClass;
        this.domainName = domainName;
        this.isState = isState;
        this.remark = remark;
        this.fields = fields;
    }

    public static ValueObject.ValueObjectBuilder builder() {
        return new ValueObject.ValueObjectBuilder();
    }

    public ValueObject.ValueObjectBuilder toBuilder() {
        return (new ValueObject.ValueObjectBuilder()).label(this.label).name(this.name).domainClass(this.domainClass).domainName(this.domainName).isState(this.isState).remark(this.remark).fields(this.fields);
    }

    public String getLabel() {
        return this.label;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getDomainClass() {
        return this.domainClass;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public Boolean getIsState() {
        return this.isState;
    }

    public String getRemark() {
        return this.remark;
    }

    public List<ValueField> getFields() {
        return this.fields;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDomainClass(Class<?> domainClass) {
        this.domainClass = domainClass;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setIsState(Boolean isState) {
        this.isState = isState;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setFields(List<ValueField> fields) {
        this.fields = fields;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ValueObject)) {
            return false;
        } else {
            ValueObject other = (ValueObject)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label95: {
                    Object this$label = this.getLabel();
                    Object other$label = other.getLabel();
                    if (this$label == null) {
                        if (other$label == null) {
                            break label95;
                        }
                    } else if (this$label.equals(other$label)) {
                        break label95;
                    }

                    return false;
                }

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$domainClass = this.getDomainClass();
                Object other$domainClass = other.getDomainClass();
                if (this$domainClass == null) {
                    if (other$domainClass != null) {
                        return false;
                    }
                } else if (!this$domainClass.equals(other$domainClass)) {
                    return false;
                }

                label74: {
                    Object this$domainName = this.getDomainName();
                    Object other$domainName = other.getDomainName();
                    if (this$domainName == null) {
                        if (other$domainName == null) {
                            break label74;
                        }
                    } else if (this$domainName.equals(other$domainName)) {
                        break label74;
                    }

                    return false;
                }

                label67: {
                    Object this$isState = this.getIsState();
                    Object other$isState = other.getIsState();
                    if (this$isState == null) {
                        if (other$isState == null) {
                            break label67;
                        }
                    } else if (this$isState.equals(other$isState)) {
                        break label67;
                    }

                    return false;
                }

                Object this$remark = this.getRemark();
                Object other$remark = other.getRemark();
                if (this$remark == null) {
                    if (other$remark != null) {
                        return false;
                    }
                } else if (!this$remark.equals(other$remark)) {
                    return false;
                }

                Object this$fields = this.getFields();
                Object other$fields = other.getFields();
                if (this$fields == null) {
                    if (other$fields != null) {
                        return false;
                    }
                } else if (!this$fields.equals(other$fields)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ValueObject;
    }


    public String toString() {
        return "ValueObject(label=" + this.getLabel() + ", name=" + this.getName() + ", domainClass=" + this.getDomainClass() + ", domainName=" + this.getDomainName() + ", isState=" + this.getIsState() + ", remark=" + this.getRemark() + ", fields=" + this.getFields() + ")";
    }

    public static class ValueObjectBuilder {
        private String label;
        private String name;
        private Class<?> domainClass;
        private String domainName;
        private Boolean isState;
        private String remark;
        private List<ValueField> fields;

        ValueObjectBuilder() {
        }

        public ValueObject.ValueObjectBuilder label(String label) {
            this.label = label;
            return this;
        }

        public ValueObject.ValueObjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ValueObject.ValueObjectBuilder domainClass(Class<?> domainClass) {
            this.domainClass = domainClass;
            return this;
        }

        public ValueObject.ValueObjectBuilder domainName(String domainName) {
            this.domainName = domainName;
            return this;
        }

        public ValueObject.ValueObjectBuilder isState(Boolean isState) {
            this.isState = isState;
            return this;
        }

        public ValueObject.ValueObjectBuilder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public ValueObject.ValueObjectBuilder fields(List<ValueField> fields) {
            this.fields = fields;
            return this;
        }

        public ValueObject build() {
            return new ValueObject(this.label, this.name, this.domainClass, this.domainName, this.isState, this.remark, this.fields);
        }

        public String toString() {
            return "ValueObject.ValueObjectBuilder(label=" + this.label + ", name=" + this.name + ", domainClass=" + this.domainClass + ", domainName=" + this.domainName + ", isState=" + this.isState + ", remark=" + this.remark + ", fields=" + this.fields + ")";
        }
    }
}
