//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.domain;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import lombok.NonNull;

public class MetaRelation implements Serializable, Cloneable {
    private String tableName;
    private String joinTableName;
    @NonNull
    private MetaRelation.Type type;
    @NonNull
    private Class<? extends Domain> domain;
    @NonNull
    private String joinField;
    @NonNull
    private String showField;
    @NonNull
    private String remark;
    private String domainName;

    protected MetaRelation clone() {
        return of(this.tableName, this.joinTableName, this.type, this.domain, this.joinField, this.showField, this.remark, this.domainName);
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getJoinTableName() {
        return this.joinTableName;
    }

    @NonNull
    public MetaRelation.Type getType() {
        return this.type;
    }

    @NonNull
    public Class<? extends Domain> getDomain() {
        return this.domain;
    }

    @NonNull
    public String getJoinField() {
        return this.joinField;
    }

    @NonNull
    public String getShowField() {
        return this.showField;
    }

    @NonNull
    public String getRemark() {
        return this.remark;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setJoinTableName(String joinTableName) {
        this.joinTableName = joinTableName;
    }

    public void setType(@NonNull MetaRelation.Type type) {
        if (type == null) {
            throw new NullPointerException("type");
        } else {
            this.type = type;
        }
    }

    public void setDomain(@NonNull Class<? extends Domain> domain) {
        if (domain == null) {
            throw new NullPointerException("domain");
        } else {
            this.domain = domain;
        }
    }

    public void setJoinField(@NonNull String joinField) {
        if (joinField == null) {
            throw new NullPointerException("joinField");
        } else {
            this.joinField = joinField;
        }
    }

    public void setShowField(@NonNull String showField) {
        if (showField == null) {
            throw new NullPointerException("showField");
        } else {
            this.showField = showField;
        }
    }

    public void setRemark(@NonNull String remark) {
        if (remark == null) {
            throw new NullPointerException("remark");
        } else {
            this.remark = remark;
        }
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MetaRelation)) {
            return false;
        } else {
            MetaRelation other = (MetaRelation)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label107: {
                    Object this$tableName = this.getTableName();
                    Object other$tableName = other.getTableName();
                    if (this$tableName == null) {
                        if (other$tableName == null) {
                            break label107;
                        }
                    } else if (this$tableName.equals(other$tableName)) {
                        break label107;
                    }

                    return false;
                }

                Object this$joinTableName = this.getJoinTableName();
                Object other$joinTableName = other.getJoinTableName();
                if (this$joinTableName == null) {
                    if (other$joinTableName != null) {
                        return false;
                    }
                } else if (!this$joinTableName.equals(other$joinTableName)) {
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

                label86: {
                    Object this$domain = this.getDomain();
                    Object other$domain = other.getDomain();
                    if (this$domain == null) {
                        if (other$domain == null) {
                            break label86;
                        }
                    } else if (this$domain.equals(other$domain)) {
                        break label86;
                    }

                    return false;
                }

                label79: {
                    Object this$joinField = this.getJoinField();
                    Object other$joinField = other.getJoinField();
                    if (this$joinField == null) {
                        if (other$joinField == null) {
                            break label79;
                        }
                    } else if (this$joinField.equals(other$joinField)) {
                        break label79;
                    }

                    return false;
                }

                label72: {
                    Object this$showField = this.getShowField();
                    Object other$showField = other.getShowField();
                    if (this$showField == null) {
                        if (other$showField == null) {
                            break label72;
                        }
                    } else if (this$showField.equals(other$showField)) {
                        break label72;
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

                Object this$domainName = this.getDomainName();
                Object other$domainName = other.getDomainName();
                if (this$domainName == null) {
                    if (other$domainName != null) {
                        return false;
                    }
                } else if (!this$domainName.equals(other$domainName)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof MetaRelation;
    }



    public String toString() {
        return "MetaRelation(tableName=" + this.getTableName() + ", joinTableName=" + this.getJoinTableName() + ", type=" + this.getType() + ", domain=" + this.getDomain() + ", joinField=" + this.getJoinField() + ", showField=" + this.getShowField() + ", remark=" + this.getRemark() + ", domainName=" + this.getDomainName() + ")";
    }

    @ConstructorProperties({"tableName", "joinTableName", "type", "domain", "joinField", "showField", "remark", "domainName"})
    private MetaRelation(String tableName, String joinTableName, @NonNull MetaRelation.Type type, @NonNull Class<? extends Domain> domain, @NonNull String joinField, @NonNull String showField, @NonNull String remark, String domainName) {
        if (type == null) {
            throw new NullPointerException("type");
        } else if (domain == null) {
            throw new NullPointerException("domain");
        } else if (joinField == null) {
            throw new NullPointerException("joinField");
        } else if (showField == null) {
            throw new NullPointerException("showField");
        } else if (remark == null) {
            throw new NullPointerException("remark");
        } else {
            this.tableName = tableName;
            this.joinTableName = joinTableName;
            this.type = type;
            this.domain = domain;
            this.joinField = joinField;
            this.showField = showField;
            this.remark = remark;
            this.domainName = domainName;
        }
    }

    public static MetaRelation of(String tableName, String joinTableName, @NonNull MetaRelation.Type type, @NonNull Class<? extends Domain> domain, @NonNull String joinField, @NonNull String showField, @NonNull String remark, String domainName) {
        return new MetaRelation(tableName, joinTableName, type, domain, joinField, showField, remark, domainName);
    }

    @ConstructorProperties({"type", "domain", "joinField", "showField", "remark"})
    private MetaRelation(@NonNull MetaRelation.Type type, @NonNull Class<? extends Domain> domain, @NonNull String joinField, @NonNull String showField, @NonNull String remark) {
        if (type == null) {
            throw new NullPointerException("type");
        } else if (domain == null) {
            throw new NullPointerException("domain");
        } else if (joinField == null) {
            throw new NullPointerException("joinField");
        } else if (showField == null) {
            throw new NullPointerException("showField");
        } else if (remark == null) {
            throw new NullPointerException("remark");
        } else {
            this.type = type;
            this.domain = domain;
            this.joinField = joinField;
            this.showField = showField;
            this.remark = remark;
        }
    }

    public static MetaRelation of(@NonNull MetaRelation.Type type, @NonNull Class<? extends Domain> domain, @NonNull String joinField, @NonNull String showField, @NonNull String remark) {
        return new MetaRelation(type, domain, joinField, showField, remark);
    }

    public static enum Type {
        ONE_TO_MANY("一对多"),
        MANY_TO_MANY("多对多"),
        ONE_TO_ONE("一对一"),
        MANY_TO_ONE("多对一");

        String label;

        private Type(String label) {
            this.label = label;
        }

        String label() {
            return this.label;
        }
    }
}
