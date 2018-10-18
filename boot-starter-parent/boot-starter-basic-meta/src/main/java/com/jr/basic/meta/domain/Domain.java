//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.domain;

import com.jr.basic.meta.annotation.Field;
import com.jr.basic.meta.enums.UIType;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class Domain implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String ID = "id";
    public static final String NAME = "domain";
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @Field(
        isKey = true,
        isList = false,
        isCreate = false,
        isUpdate = false,
        order = 1,
        label = "ID",
        uiType = {UIType.HIDDEN}
    )
    public Long id;
    @Field(
        isList = false,
        isCreate = false,
        isUpdate = false,
        label = "VERSION",
        uiType = {UIType.HIDDEN}
    )
    @Version
    protected Integer version;

    public Domain() {
    }

    public Long getId() {
        return this.id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Domain)) {
            return false;
        } else {
            Domain other = (Domain)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$version = this.getVersion();
                Object other$version = other.getVersion();
                if (this$version == null) {
                    if (other$version != null) {
                        return false;
                    }
                } else if (!this$version.equals(other$version)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Domain;
    }


    public String toString() {
        return "Domain(id=" + this.getId() + ", version=" + this.getVersion() + ")";
    }
}
