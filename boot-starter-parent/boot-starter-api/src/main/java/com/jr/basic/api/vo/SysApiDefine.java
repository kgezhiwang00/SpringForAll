//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.vo;

import com.google.common.collect.ImmutableList;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Arrays;

public class SysApiDefine implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String module;
    private String remark;
    private Boolean isLog;
    private Boolean isGraphql;
    private Boolean isMutation;
    private String[] roles;
    private ImmutableList<SysApiParam> params;

    @ConstructorProperties({"name", "module", "remark", "isLog", "isGraphql", "isMutation", "roles", "params"})
    SysApiDefine(String name, String module, String remark, Boolean isLog, Boolean isGraphql, Boolean isMutation, String[] roles, ImmutableList<SysApiParam> params) {
        this.name = name;
        this.module = module;
        this.remark = remark;
        this.isLog = isLog;
        this.isGraphql = isGraphql;
        this.isMutation = isMutation;
        this.roles = roles;
        this.params = params;
    }

    public static SysApiDefine.SysApiDefineBuilder builder() {
        return new SysApiDefine.SysApiDefineBuilder();
    }

    public SysApiDefine.SysApiDefineBuilder toBuilder() {
        return (new SysApiDefine.SysApiDefineBuilder()).name(this.name).module(this.module).remark(this.remark).isLog(this.isLog).isGraphql(this.isGraphql).isMutation(this.isMutation).roles(this.roles).params(this.params);
    }

    public String getName() {
        return this.name;
    }

    public String getModule() {
        return this.module;
    }

    public String getRemark() {
        return this.remark;
    }

    public Boolean getIsLog() {
        return this.isLog;
    }

    public Boolean getIsGraphql() {
        return this.isGraphql;
    }

    public Boolean getIsMutation() {
        return this.isMutation;
    }

    public String[] getRoles() {
        return this.roles;
    }

    public ImmutableList<SysApiParam> getParams() {
        return this.params;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setIsLog(Boolean isLog) {
        this.isLog = isLog;
    }

    public void setIsGraphql(Boolean isGraphql) {
        this.isGraphql = isGraphql;
    }

    public void setIsMutation(Boolean isMutation) {
        this.isMutation = isMutation;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public void setParams(ImmutableList<SysApiParam> params) {
        this.params = params;
    }

    public String toString() {
        return "SysApiDefine(name=" + this.getName() + ", module=" + this.getModule() + ", remark=" + this.getRemark() + ", isLog=" + this.getIsLog() + ", isGraphql=" + this.getIsGraphql() + ", isMutation=" + this.getIsMutation() + ", roles=" + Arrays.deepToString(this.getRoles()) + ", params=" + this.getParams() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SysApiDefine)) {
            return false;
        } else {
            SysApiDefine other = (SysApiDefine)o;
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

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof SysApiDefine;
    }


    public static class SysApiDefineBuilder {
        private String name;
        private String module;
        private String remark;
        private Boolean isLog;
        private Boolean isGraphql;
        private Boolean isMutation;
        private String[] roles;
        private ImmutableList<SysApiParam> params;

        SysApiDefineBuilder() {
        }

        public SysApiDefine.SysApiDefineBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SysApiDefine.SysApiDefineBuilder module(String module) {
            this.module = module;
            return this;
        }

        public SysApiDefine.SysApiDefineBuilder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public SysApiDefine.SysApiDefineBuilder isLog(Boolean isLog) {
            this.isLog = isLog;
            return this;
        }

        public SysApiDefine.SysApiDefineBuilder isGraphql(Boolean isGraphql) {
            this.isGraphql = isGraphql;
            return this;
        }

        public SysApiDefine.SysApiDefineBuilder isMutation(Boolean isMutation) {
            this.isMutation = isMutation;
            return this;
        }

        public SysApiDefine.SysApiDefineBuilder roles(String[] roles) {
            this.roles = roles;
            return this;
        }

        public SysApiDefine.SysApiDefineBuilder params(ImmutableList<SysApiParam> params) {
            this.params = params;
            return this;
        }

        public SysApiDefine build() {
            return new SysApiDefine(this.name, this.module, this.remark, this.isLog, this.isGraphql, this.isMutation, this.roles, this.params);
        }

        public String toString() {
            return "SysApiDefine.SysApiDefineBuilder(name=" + this.name + ", module=" + this.module + ", remark=" + this.remark + ", isLog=" + this.isLog + ", isGraphql=" + this.isGraphql + ", isMutation=" + this.isMutation + ", roles=" + Arrays.deepToString(this.roles) + ", params=" + this.params + ")";
        }
    }
}
