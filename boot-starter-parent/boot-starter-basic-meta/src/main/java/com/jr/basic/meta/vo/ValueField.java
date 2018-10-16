//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.vo;

import com.jr.basic.meta.enums.Type;
import java.beans.ConstructorProperties;

public class ValueField {
    private String label;
    private String name;
    private Type type;
    private String metaField;
    private String remark;

    @ConstructorProperties({"label", "name", "type", "metaField", "remark"})
    ValueField(String label, String name, Type type, String metaField, String remark) {
        this.label = label;
        this.name = name;
        this.type = type;
        this.metaField = metaField;
        this.remark = remark;
    }

    public static ValueField.ValueFieldBuilder builder() {
        return new ValueField.ValueFieldBuilder();
    }

    public ValueField.ValueFieldBuilder toBuilder() {
        return (new ValueField.ValueFieldBuilder()).label(this.label).name(this.name).type(this.type).metaField(this.metaField).remark(this.remark);
    }

    public String getLabel() {
        return this.label;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public String getMetaField() {
        return this.metaField;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setMetaField(String metaField) {
        this.metaField = metaField;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ValueField)) {
            return false;
        } else {
            ValueField other = (ValueField)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$label = this.getLabel();
                    Object other$label = other.getLabel();
                    if (this$label == null) {
                        if (other$label == null) {
                            break label71;
                        }
                    } else if (this$label.equals(other$label)) {
                        break label71;
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

                label57: {
                    Object this$type = this.getType();
                    Object other$type = other.getType();
                    if (this$type == null) {
                        if (other$type == null) {
                            break label57;
                        }
                    } else if (this$type.equals(other$type)) {
                        break label57;
                    }

                    return false;
                }

                Object this$metaField = this.getMetaField();
                Object other$metaField = other.getMetaField();
                if (this$metaField == null) {
                    if (other$metaField != null) {
                        return false;
                    }
                } else if (!this$metaField.equals(other$metaField)) {
                    return false;
                }

                Object this$remark = this.getRemark();
                Object other$remark = other.getRemark();
                if (this$remark == null) {
                    if (other$remark == null) {
                        return true;
                    }
                } else if (this$remark.equals(other$remark)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ValueField;
    }



    public String toString() {
        return "ValueField(label=" + this.getLabel() + ", name=" + this.getName() + ", type=" + this.getType() + ", metaField=" + this.getMetaField() + ", remark=" + this.getRemark() + ")";
    }

    public static class ValueFieldBuilder {
        private String label;
        private String name;
        private Type type;
        private String metaField;
        private String remark;

        ValueFieldBuilder() {
        }

        public ValueField.ValueFieldBuilder label(String label) {
            this.label = label;
            return this;
        }

        public ValueField.ValueFieldBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ValueField.ValueFieldBuilder type(Type type) {
            this.type = type;
            return this;
        }

        public ValueField.ValueFieldBuilder metaField(String metaField) {
            this.metaField = metaField;
            return this;
        }

        public ValueField.ValueFieldBuilder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public ValueField build() {
            return new ValueField(this.label, this.name, this.type, this.metaField, this.remark);
        }

        public String toString() {
            return "ValueField.ValueFieldBuilder(label=" + this.label + ", name=" + this.name + ", type=" + this.type + ", metaField=" + this.metaField + ", remark=" + this.remark + ")";
        }
    }
}
