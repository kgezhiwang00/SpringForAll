//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.domain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.ImmutableList.Builder;
import com.jr.basic.meta.enums.OrderType;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.hibernate.annotations.Type;

public class MetaDomain implements Serializable {
    public static final String CACHE_SUFFIX = "Cache";
    public static final String LIST_CACHE = "ListCache";
    public static final String NAME = "name";
    public static final String LABEL = "label";
    public static final String VIEW_TYPE = "viewType";
    public static final String REMARK = "remark";
    public static final String ID_FIELD = "idField";
    public static final String SHOW_FIELD = "showField";
    public static final String LIST_FIELD = "listField";
    public static final String QUERY_FIELD = "queryField";
    public static final String UPDATE_FIELD = "updateField";
    public static final String CREATE_FIELD = "createField";
    public static final String LOGIC_DELETE_FIELD = "isDelete";
    private static final long serialVersionUID = -2705627014380434395L;
    private Class<? extends Domain> domainClass;
    private String name;
    private String label;
    private String fullName;
    private String tableName;
    private String module;
    private String remark;
    private Boolean allowDynamic;
    private Boolean allowCreate;
    private Boolean allowUpdate;
    private Boolean allowDelete;
    private Boolean enable;
    private Date lastDataRefresh;
    private Boolean isCache;
    private Boolean isLogicDelete;
    private String showField;
    private String viewType;
    private String owner;
    @Type(
            type = "com.jr.core.domain.userType.ArrayStringUserType"
    )
    private String[] listRole;
    @Type(
            type = "com.jr.core.domain.userType.ArrayStringUserType"
    )
    private String[] createRole;
    @Type(
            type = "com.jr.core.domain.userType.ArrayStringUserType"
    )
    private String[] updateRole;
    @Type(
            type = "com.jr.core.domain.userType.ArrayStringUserType"
    )
    private String[] deleteRole;
    private ImmutableList<MetaField> fields;
    private String defaultOrder;
    private OrderType defaultOrderType;

    public MetaField getIdField() {
        UnmodifiableIterator var1 = this.fields.iterator();

        MetaField field;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            field = (MetaField)var1.next();
        } while(!field.getIsKey().booleanValue());

        return field;
    }

    public Boolean hasAggregate() {
        return this.getAggregateField().size() > 0;
    }

    public List<MetaField> getAggregateField() {
        return (List)this.getFields().stream().filter((f) -> {
            return f.getAggregates() != null && f.getAggregates().length > 0;
        }).collect(Collectors.toList());
    }

    public List<MetaField> getGroupField() {
        return (List)this.getFields().stream().filter(MetaField::getIsGroup).collect(Collectors.toList());
    }

    public Boolean hasQueryField() {
        return this.getQueryField().size() > 0;
    }

    public Boolean importable() {
        return this.getImportField().size() > 0;
    }

    public List<MetaField> getQueryField() {
        return (List)this.getFields().stream().filter((f) -> {
            return f.getIsQuery().booleanValue();
        }).sorted(Comparator.comparing(MetaField::getQueryOrder)).collect(Collectors.toList());
    }

    public List<MetaField> getListField() {
        return (List)this.getFields().stream().filter((f) -> {
            return f.getIsList().booleanValue();
        }).sorted(Comparator.comparing(MetaField::getListOrder)).collect(Collectors.toList());
    }

    public List<MetaField> getImportField() {
        return (List)this.getFields().stream().filter((f) -> {
            return f.getImportNum().intValue() > 0;
        }).sorted(Comparator.comparing(MetaField::getImportNum)).collect(Collectors.toList());
    }

    public List<MetaField> getCreateField() {
        return (List)this.getFields().stream().filter((f) -> {
            return !f.getIsVirtual().booleanValue() && f.getIsCreate().booleanValue();
        }).sorted(Comparator.comparing(MetaField::getCreateOrder)).collect(Collectors.toList());
    }

    public List<MetaField> getUpdateField() {
        return (List)this.getFields().stream().filter((f) -> {
            return !f.getIsVirtual().booleanValue() && f.getIsUpdate().booleanValue();
        }).sorted(Comparator.comparing(MetaField::getUpdateOrder)).collect(Collectors.toList());
    }

    public <T extends Domain> Class<T> getDomainClass() {
        return (Class<T>) this.domainClass;
    }

    public MetaField getField(String name) {
        UnmodifiableIterator var2 = this.fields.iterator();

        MetaField f;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            f = (MetaField)var2.next();
        } while(!f.getName().equals(name));

        return f;
    }

    public List<MetaRelation> getRelations() {
        Builder builder = ImmutableList.builder();
        UnmodifiableIterator var2 = this.fields.iterator();

        while(var2.hasNext()) {
            MetaField field = (MetaField)var2.next();
            if (field.getRelation() != null) {
                builder.add(field.getRelation().clone());
            }
        }

        return builder.build();
    }

    public String getCacheName() {
        return this.getName() + "Cache";
    }

    public String getListCacheName() {
        return this.getName() + "_" + "ListCache";
    }

    public static MetaDomain.MetaDomainBuilder builder() {
        return new MetaDomain.MetaDomainBuilder();
    }

    public MetaDomain.MetaDomainBuilder toBuilder() {
        return (new MetaDomain.MetaDomainBuilder()).domainClass(this.domainClass).name(this.name).label(this.label).fullName(this.fullName).tableName(this.tableName).module(this.module).remark(this.remark).allowDynamic(this.allowDynamic).allowCreate(this.allowCreate).allowUpdate(this.allowUpdate).allowDelete(this.allowDelete).enable(this.enable).lastDataRefresh(this.lastDataRefresh).isCache(this.isCache).isLogicDelete(this.isLogicDelete).showField(this.showField).viewType(this.viewType).owner(this.owner).listRole(this.listRole).createRole(this.createRole).updateRole(this.updateRole).deleteRole(this.deleteRole).fields(this.fields).defaultOrder(this.defaultOrder).defaultOrderType(this.defaultOrderType);
    }

    public String getName() {
        return this.name;
    }

    public String getLabel() {
        return this.label;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getModule() {
        return this.module;
    }

    public String getRemark() {
        return this.remark;
    }

    public Boolean getAllowDynamic() {
        return this.allowDynamic;
    }

    public Boolean getAllowCreate() {
        return this.allowCreate;
    }

    public Boolean getAllowUpdate() {
        return this.allowUpdate;
    }

    public Boolean getAllowDelete() {
        return this.allowDelete;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public Date getLastDataRefresh() {
        return this.lastDataRefresh;
    }

    public Boolean getIsCache() {
        return this.isCache;
    }

    public Boolean getIsLogicDelete() {
        return this.isLogicDelete;
    }

    public String getShowField() {
        return this.showField;
    }

    public String getViewType() {
        return this.viewType;
    }

    public String getOwner() {
        return this.owner;
    }

    public String[] getListRole() {
        return this.listRole;
    }

    public String[] getCreateRole() {
        return this.createRole;
    }

    public String[] getUpdateRole() {
        return this.updateRole;
    }

    public String[] getDeleteRole() {
        return this.deleteRole;
    }

    public ImmutableList<MetaField> getFields() {
        return this.fields;
    }

    public String getDefaultOrder() {
        return this.defaultOrder;
    }

    public OrderType getDefaultOrderType() {
        return this.defaultOrderType;
    }

    public void setDomainClass(Class<? extends Domain> domainClass) {
        this.domainClass = domainClass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setAllowDynamic(Boolean allowDynamic) {
        this.allowDynamic = allowDynamic;
    }

    public void setAllowCreate(Boolean allowCreate) {
        this.allowCreate = allowCreate;
    }

    public void setAllowUpdate(Boolean allowUpdate) {
        this.allowUpdate = allowUpdate;
    }

    public void setAllowDelete(Boolean allowDelete) {
        this.allowDelete = allowDelete;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setLastDataRefresh(Date lastDataRefresh) {
        this.lastDataRefresh = lastDataRefresh;
    }

    public void setIsCache(Boolean isCache) {
        this.isCache = isCache;
    }

    public void setIsLogicDelete(Boolean isLogicDelete) {
        this.isLogicDelete = isLogicDelete;
    }

    public void setShowField(String showField) {
        this.showField = showField;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setListRole(String[] listRole) {
        this.listRole = listRole;
    }

    public void setCreateRole(String[] createRole) {
        this.createRole = createRole;
    }

    public void setUpdateRole(String[] updateRole) {
        this.updateRole = updateRole;
    }

    public void setDeleteRole(String[] deleteRole) {
        this.deleteRole = deleteRole;
    }

    public void setFields(ImmutableList<MetaField> fields) {
        this.fields = fields;
    }

    public void setDefaultOrder(String defaultOrder) {
        this.defaultOrder = defaultOrder;
    }

    public void setDefaultOrderType(OrderType defaultOrderType) {
        this.defaultOrderType = defaultOrderType;
    }

    public String toString() {
        return "MetaDomain(domainClass=" + this.getDomainClass() + ", name=" + this.getName() + ", label=" + this.getLabel() + ", fullName=" + this.getFullName() + ", tableName=" + this.getTableName() + ", module=" + this.getModule() + ", remark=" + this.getRemark() + ", allowDynamic=" + this.getAllowDynamic() + ", allowCreate=" + this.getAllowCreate() + ", allowUpdate=" + this.getAllowUpdate() + ", allowDelete=" + this.getAllowDelete() + ", enable=" + this.getEnable() + ", lastDataRefresh=" + this.getLastDataRefresh() + ", isCache=" + this.getIsCache() + ", isLogicDelete=" + this.getIsLogicDelete() + ", showField=" + this.getShowField() + ", viewType=" + this.getViewType() + ", owner=" + this.getOwner() + ", listRole=" + Arrays.deepToString(this.getListRole()) + ", createRole=" + Arrays.deepToString(this.getCreateRole()) + ", updateRole=" + Arrays.deepToString(this.getUpdateRole()) + ", deleteRole=" + Arrays.deepToString(this.getDeleteRole()) + ", fields=" + this.getFields() + ", defaultOrder=" + this.getDefaultOrder() + ", defaultOrderType=" + this.getDefaultOrderType() + ")";
    }

    @ConstructorProperties({"domainClass", "name", "label", "fullName", "tableName", "module", "remark", "allowDynamic", "allowCreate", "allowUpdate", "allowDelete", "enable", "lastDataRefresh", "isCache", "isLogicDelete", "showField", "viewType", "owner", "listRole", "createRole", "updateRole", "deleteRole", "fields", "defaultOrder", "defaultOrderType"})
    private MetaDomain(Class<? extends Domain> domainClass, String name, String label, String fullName, String tableName, String module, String remark, Boolean allowDynamic, Boolean allowCreate, Boolean allowUpdate, Boolean allowDelete, Boolean enable, Date lastDataRefresh, Boolean isCache, Boolean isLogicDelete, String showField, String viewType, String owner, String[] listRole, String[] createRole, String[] updateRole, String[] deleteRole, ImmutableList<MetaField> fields, String defaultOrder, OrderType defaultOrderType) {
        this.domainClass = domainClass;
        this.name = name;
        this.label = label;
        this.fullName = fullName;
        this.tableName = tableName;
        this.module = module;
        this.remark = remark;
        this.allowDynamic = allowDynamic;
        this.allowCreate = allowCreate;
        this.allowUpdate = allowUpdate;
        this.allowDelete = allowDelete;
        this.enable = enable;
        this.lastDataRefresh = lastDataRefresh;
        this.isCache = isCache;
        this.isLogicDelete = isLogicDelete;
        this.showField = showField;
        this.viewType = viewType;
        this.owner = owner;
        this.listRole = listRole;
        this.createRole = createRole;
        this.updateRole = updateRole;
        this.deleteRole = deleteRole;
        this.fields = fields;
        this.defaultOrder = defaultOrder;
        this.defaultOrderType = defaultOrderType;
    }

    public static MetaDomain of(Class<? extends Domain> domainClass, String name, String label, String fullName, String tableName, String module, String remark, Boolean allowDynamic, Boolean allowCreate, Boolean allowUpdate, Boolean allowDelete, Boolean enable, Date lastDataRefresh, Boolean isCache, Boolean isLogicDelete, String showField, String viewType, String owner, String[] listRole, String[] createRole, String[] updateRole, String[] deleteRole, ImmutableList<MetaField> fields, String defaultOrder, OrderType defaultOrderType) {
        return new MetaDomain(domainClass, name, label, fullName, tableName, module, remark, allowDynamic, allowCreate, allowUpdate, allowDelete, enable, lastDataRefresh, isCache, isLogicDelete, showField, viewType, owner, listRole, createRole, updateRole, deleteRole, fields, defaultOrder, defaultOrderType);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MetaDomain)) {
            return false;
        } else {
            MetaDomain other = (MetaDomain)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$fullName = this.getFullName();
                Object other$fullName = other.getFullName();
                if (this$fullName == null) {
                    if (other$fullName != null) {
                        return false;
                    }
                } else if (!this$fullName.equals(other$fullName)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof MetaDomain;
    }


    public static class MetaDomainBuilder {
        private Class<? extends Domain> domainClass;
        private String name;
        private String label;
        private String fullName;
        private String tableName;
        private String module;
        private String remark;
        private Boolean allowDynamic;
        private Boolean allowCreate;
        private Boolean allowUpdate;
        private Boolean allowDelete;
        private Boolean enable;
        private Date lastDataRefresh;
        private Boolean isCache;
        private Boolean isLogicDelete;
        private String showField;
        private String viewType;
        private String owner;
        private String[] listRole;
        private String[] createRole;
        private String[] updateRole;
        private String[] deleteRole;
        private ImmutableList<MetaField> fields;
        private String defaultOrder;
        private OrderType defaultOrderType;

        MetaDomainBuilder() {
        }

        public MetaDomain.MetaDomainBuilder domainClass(Class<? extends Domain> domainClass) {
            this.domainClass = domainClass;
            return this;
        }

        public MetaDomain.MetaDomainBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MetaDomain.MetaDomainBuilder label(String label) {
            this.label = label;
            return this;
        }

        public MetaDomain.MetaDomainBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public MetaDomain.MetaDomainBuilder tableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public MetaDomain.MetaDomainBuilder module(String module) {
            this.module = module;
            return this;
        }

        public MetaDomain.MetaDomainBuilder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public MetaDomain.MetaDomainBuilder allowDynamic(Boolean allowDynamic) {
            this.allowDynamic = allowDynamic;
            return this;
        }

        public MetaDomain.MetaDomainBuilder allowCreate(Boolean allowCreate) {
            this.allowCreate = allowCreate;
            return this;
        }

        public MetaDomain.MetaDomainBuilder allowUpdate(Boolean allowUpdate) {
            this.allowUpdate = allowUpdate;
            return this;
        }

        public MetaDomain.MetaDomainBuilder allowDelete(Boolean allowDelete) {
            this.allowDelete = allowDelete;
            return this;
        }

        public MetaDomain.MetaDomainBuilder enable(Boolean enable) {
            this.enable = enable;
            return this;
        }

        public MetaDomain.MetaDomainBuilder lastDataRefresh(Date lastDataRefresh) {
            this.lastDataRefresh = lastDataRefresh;
            return this;
        }

        public MetaDomain.MetaDomainBuilder isCache(Boolean isCache) {
            this.isCache = isCache;
            return this;
        }

        public MetaDomain.MetaDomainBuilder isLogicDelete(Boolean isLogicDelete) {
            this.isLogicDelete = isLogicDelete;
            return this;
        }

        public MetaDomain.MetaDomainBuilder showField(String showField) {
            this.showField = showField;
            return this;
        }

        public MetaDomain.MetaDomainBuilder viewType(String viewType) {
            this.viewType = viewType;
            return this;
        }

        public MetaDomain.MetaDomainBuilder owner(String owner) {
            this.owner = owner;
            return this;
        }

        public MetaDomain.MetaDomainBuilder listRole(String[] listRole) {
            this.listRole = listRole;
            return this;
        }

        public MetaDomain.MetaDomainBuilder createRole(String[] createRole) {
            this.createRole = createRole;
            return this;
        }

        public MetaDomain.MetaDomainBuilder updateRole(String[] updateRole) {
            this.updateRole = updateRole;
            return this;
        }

        public MetaDomain.MetaDomainBuilder deleteRole(String[] deleteRole) {
            this.deleteRole = deleteRole;
            return this;
        }

        public MetaDomain.MetaDomainBuilder fields(ImmutableList<MetaField> fields) {
            this.fields = fields;
            return this;
        }

        public MetaDomain.MetaDomainBuilder defaultOrder(String defaultOrder) {
            this.defaultOrder = defaultOrder;
            return this;
        }

        public MetaDomain.MetaDomainBuilder defaultOrderType(OrderType defaultOrderType) {
            this.defaultOrderType = defaultOrderType;
            return this;
        }

        public MetaDomain build() {
            return new MetaDomain(this.domainClass, this.name, this.label, this.fullName, this.tableName, this.module, this.remark, this.allowDynamic, this.allowCreate, this.allowUpdate, this.allowDelete, this.enable, this.lastDataRefresh, this.isCache, this.isLogicDelete, this.showField, this.viewType, this.owner, this.listRole, this.createRole, this.updateRole, this.deleteRole, this.fields, this.defaultOrder, this.defaultOrderType);
        }

        public String toString() {
            return "MetaDomain.MetaDomainBuilder(domainClass=" + this.domainClass + ", name=" + this.name + ", label=" + this.label + ", fullName=" + this.fullName + ", tableName=" + this.tableName + ", module=" + this.module + ", remark=" + this.remark + ", allowDynamic=" + this.allowDynamic + ", allowCreate=" + this.allowCreate + ", allowUpdate=" + this.allowUpdate + ", allowDelete=" + this.allowDelete + ", enable=" + this.enable + ", lastDataRefresh=" + this.lastDataRefresh + ", isCache=" + this.isCache + ", isLogicDelete=" + this.isLogicDelete + ", showField=" + this.showField + ", viewType=" + this.viewType + ", owner=" + this.owner + ", listRole=" + Arrays.deepToString(this.listRole) + ", createRole=" + Arrays.deepToString(this.createRole) + ", updateRole=" + Arrays.deepToString(this.updateRole) + ", deleteRole=" + Arrays.deepToString(this.deleteRole) + ", fields=" + this.fields + ", defaultOrder=" + this.defaultOrder + ", defaultOrderType=" + this.defaultOrderType + ")";
        }
    }

    public static enum Status {
        enable("可用"),
        disable("禁用");

        String text;

        private Status(String text) {
            this.text = text;
        }
    }
}
