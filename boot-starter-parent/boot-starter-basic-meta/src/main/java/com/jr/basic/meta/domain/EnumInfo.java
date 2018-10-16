//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.domain;

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class EnumInfo implements Serializable {
    private String key;
    private String label;

    public String getKey() {
        return this.key;
    }

    public String getLabel() {
        return this.label;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof EnumInfo)) {
            return false;
        } else {
            EnumInfo other = (EnumInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$key = this.getKey();
                Object other$key = other.getKey();
                if (this$key == null) {
                    if (other$key != null) {
                        return false;
                    }
                } else if (!this$key.equals(other$key)) {
                    return false;
                }

                Object this$label = this.getLabel();
                Object other$label = other.getLabel();
                if (this$label == null) {
                    if (other$label != null) {
                        return false;
                    }
                } else if (!this$label.equals(other$label)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof EnumInfo;
    }


    public String toString() {
        return "EnumInfo(key=" + this.getKey() + ", label=" + this.getLabel() + ")";
    }

    @ConstructorProperties({"key", "label"})
    public EnumInfo(String key, String label) {
        this.key = key;
        this.label = label;
    }
}
