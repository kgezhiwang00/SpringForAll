//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.domain;

import com.jr.basic.meta.annotation.Field;
import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class AuditableDomain extends Domain {
    private static final long serialVersionUID = 1L;
    public static final String CREATED_DATE = "createdDate";
    public static final String CREATED_BY = "createdBy";
    public static final String LAST_UPDATED_DATE = "lastUpdatedDate";
    public static final String LAST_UPDATED_BY = "lastUpdatedBy";
    @CreatedBy
    @Field(
        label = "CreatedBy",
        isList = false,
        isCreate = false,
        isUpdate = false
    )
    private String createdBy;
    @CreatedDate
    @Field(
        label = "CreatedDate",
        isList = false,
        isCreate = false,
        isUpdate = false
    )
    private LocalDateTime createdDate;
    @LastModifiedBy
    @Field(
        label = "LastUpdatedBy",
        isList = false,
        isCreate = false,
        isUpdate = false
    )
    private String lastUpdatedBy;
    @LastModifiedDate
    @Field(
        label = "LastUpdatedDate",
        isList = false,
        isCreate = false,
        isUpdate = false
    )
    private LocalDateTime lastUpdatedDate;

    @PrePersist
    public void preCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdatedDate = LocalDateTime.now();
    }

    public AuditableDomain() {
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public LocalDateTime getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String toString() {
        return "AuditableDomain(createdBy=" + this.getCreatedBy() + ", createdDate=" + this.getCreatedDate() + ", lastUpdatedBy=" + this.getLastUpdatedBy() + ", lastUpdatedDate=" + this.getLastUpdatedDate() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AuditableDomain)) {
            return false;
        } else {
            AuditableDomain other = (AuditableDomain)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                label61: {
                    Object this$createdBy = this.getCreatedBy();
                    Object other$createdBy = other.getCreatedBy();
                    if (this$createdBy == null) {
                        if (other$createdBy == null) {
                            break label61;
                        }
                    } else if (this$createdBy.equals(other$createdBy)) {
                        break label61;
                    }

                    return false;
                }

                label54: {
                    Object this$createdDate = this.getCreatedDate();
                    Object other$createdDate = other.getCreatedDate();
                    if (this$createdDate == null) {
                        if (other$createdDate == null) {
                            break label54;
                        }
                    } else if (this$createdDate.equals(other$createdDate)) {
                        break label54;
                    }

                    return false;
                }

                Object this$lastUpdatedBy = this.getLastUpdatedBy();
                Object other$lastUpdatedBy = other.getLastUpdatedBy();
                if (this$lastUpdatedBy == null) {
                    if (other$lastUpdatedBy != null) {
                        return false;
                    }
                } else if (!this$lastUpdatedBy.equals(other$lastUpdatedBy)) {
                    return false;
                }

                Object this$lastUpdatedDate = this.getLastUpdatedDate();
                Object other$lastUpdatedDate = other.getLastUpdatedDate();
                if (this$lastUpdatedDate == null) {
                    if (other$lastUpdatedDate != null) {
                        return false;
                    }
                } else if (!this$lastUpdatedDate.equals(other$lastUpdatedDate)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof AuditableDomain;
    }


}
