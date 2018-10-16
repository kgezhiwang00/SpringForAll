//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.domain;

import com.jr.basic.meta.annotation.Field;
import com.jr.basic.meta.enums.UIType;
import java.beans.Transient;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public class KeyDomain extends AuditableDomain {
    private static final long serialVersionUID = 1L;
    public static final String IS_DELETE = "isDelete";
    @Field(
        label = "是否逻辑删除",
        isList = false,
        uiType = {UIType.HIDDEN},
        isCreate = false,
        isUpdate = false
    )
    private Boolean isDelete;

    @PrePersist
    public void preCreateForLogicDelete() {
        this.isDelete = false;
    }

    @Transient
    public void logicDelete() {
        this.isDelete = true;
    }

    public KeyDomain() {
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String toString() {
        return "KeyDomain(isDelete=" + this.getIsDelete() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof KeyDomain)) {
            return false;
        } else {
            KeyDomain other = (KeyDomain)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                Object this$isDelete = this.getIsDelete();
                Object other$isDelete = other.getIsDelete();
                if (this$isDelete == null) {
                    if (other$isDelete != null) {
                        return false;
                    }
                } else if (!this$isDelete.equals(other$isDelete)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof KeyDomain;
    }


}
