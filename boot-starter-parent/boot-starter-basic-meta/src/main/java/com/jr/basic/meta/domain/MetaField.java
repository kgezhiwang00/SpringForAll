//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.domain;

import com.jr.basic.meta.enums.Aggregate;
import com.jr.basic.meta.enums.Type;
import com.jr.basic.meta.enums.UIType;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MetaField implements Serializable {
    private static final long serialVersionUID = -7579380075700752996L;
    public static final String UI_TYPE_TEXT = "text";
    public static final String UI_TYPE_SELECT = "select";
    public static final String IS_SORT = "isSort";
    public static final String IS_QUERY = "isQuery";
    public static final String QUERY_ORDER = "queryOrder";
    public static final String IS_LIST = "isList";
    public static final String LIST_ORDER = "listOrder";
    public static final String IS_CREATE = "isCreate";
    public static final String CREATE_ORDER = "createOrder";
    public static final String IS_UPDATE = "isUpdate";
    public static final String UPDATE_ORDER = "updateOrder";
    public static final String NAME = "name";
    public static final String LABEL = "label";
    public static final String REMARK = "remark";
    public static final String TYPE = "type";
    public static final String UI_TYPE = "uiType";
    public static final String IS_REQUIRED = "isRequired";
    public static final String IS_KEY = "isKey";
    public static final String MIN = "min";
    public static final String MAX = "max";
    public static final String ENUMS = "enums";
    public static final String ENUM_LABELS = "enumLabels";
    public static final String IMPORTABLE = "importNum";
    private Class<?> fieldClass;
    private Boolean isKey;
    private String name;
    private String label;
    private String columnName;
    private Type type;
    private Boolean isCode;
    private String codeValue;
    private MetaRelation relation;
    private String relationDomain;
    private String relationKey;
    private Set<String> validator;
    private String defaultValue;
    private Boolean isVirtual;
    private Boolean isRequired;
    private Boolean isUnique;
    private UIType uiType;
    private Integer colSpan;
    private Integer rowSpan;
    private Boolean isQuery;
    private String queryType;
    private Integer queryOrder;
    private Boolean isRangeQuery;
    private String[] queryRole;
    private Boolean isList;
    private Boolean isSort;
    private Integer listOrder;
    private String[] listRole;
    private Boolean isCreate;
    private Integer createOrder;
    private String[] createRole;
    private Boolean isUpdate;
    private Integer updateOrder;
    private String[] updateRole;
    private String remark;
    private Long min;
    private Long max;
    private List<EnumInfo> enums;
    private Boolean isCollection;
    private Integer importNum;
    private Aggregate[] aggregates;
    private Boolean isGroup;

    private static Boolean $default$isVirtual() {
        return false;
    }

    private static Boolean $default$isRequired() {
        return true;
    }

    private static Boolean $default$isUnique() {
        return false;
    }

    private static Boolean $default$isRangeQuery() {
        return false;
    }

    public static MetaField.MetaFieldBuilder builder() {
        return new MetaField.MetaFieldBuilder();
    }

    public MetaField.MetaFieldBuilder toBuilder() {
        return (new MetaField.MetaFieldBuilder()).fieldClass(this.fieldClass).isKey(this.isKey).name(this.name).label(this.label).columnName(this.columnName).type(this.type).isCode(this.isCode).codeValue(this.codeValue).relation(this.relation).relationDomain(this.relationDomain).relationKey(this.relationKey).validator(this.validator).defaultValue(this.defaultValue).isVirtual(this.isVirtual).isRequired(this.isRequired).isUnique(this.isUnique).uiType(this.uiType).colSpan(this.colSpan).rowSpan(this.rowSpan).isQuery(this.isQuery).queryType(this.queryType).queryOrder(this.queryOrder).isRangeQuery(this.isRangeQuery).queryRole(this.queryRole).isList(this.isList).isSort(this.isSort).listOrder(this.listOrder).listRole(this.listRole).isCreate(this.isCreate).createOrder(this.createOrder).createRole(this.createRole).isUpdate(this.isUpdate).updateOrder(this.updateOrder).updateRole(this.updateRole).remark(this.remark).min(this.min).max(this.max).enums(this.enums).isCollection(this.isCollection).importNum(this.importNum).aggregates(this.aggregates).isGroup(this.isGroup);
    }

    public Class<?> getFieldClass() {
        return this.fieldClass;
    }

    public Boolean getIsKey() {
        return this.isKey;
    }

    public String getName() {
        return this.name;
    }

    public String getLabel() {
        return this.label;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public Type getType() {
        return this.type;
    }

    public Boolean getIsCode() {
        return this.isCode;
    }

    public String getCodeValue() {
        return this.codeValue;
    }

    public MetaRelation getRelation() {
        return this.relation;
    }

    public String getRelationDomain() {
        return this.relationDomain;
    }

    public String getRelationKey() {
        return this.relationKey;
    }

    public Set<String> getValidator() {
        return this.validator;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public Boolean getIsVirtual() {
        return this.isVirtual;
    }

    public Boolean getIsRequired() {
        return this.isRequired;
    }

    public Boolean getIsUnique() {
        return this.isUnique;
    }

    public UIType getUiType() {
        return this.uiType;
    }

    public Integer getColSpan() {
        return this.colSpan;
    }

    public Integer getRowSpan() {
        return this.rowSpan;
    }

    public Boolean getIsQuery() {
        return this.isQuery;
    }

    public String getQueryType() {
        return this.queryType;
    }

    public Integer getQueryOrder() {
        return this.queryOrder;
    }

    public Boolean getIsRangeQuery() {
        return this.isRangeQuery;
    }

    public String[] getQueryRole() {
        return this.queryRole;
    }

    public Boolean getIsList() {
        return this.isList;
    }

    public Boolean getIsSort() {
        return this.isSort;
    }

    public Integer getListOrder() {
        return this.listOrder;
    }

    public String[] getListRole() {
        return this.listRole;
    }

    public Boolean getIsCreate() {
        return this.isCreate;
    }

    public Integer getCreateOrder() {
        return this.createOrder;
    }

    public String[] getCreateRole() {
        return this.createRole;
    }

    public Boolean getIsUpdate() {
        return this.isUpdate;
    }

    public Integer getUpdateOrder() {
        return this.updateOrder;
    }

    public String[] getUpdateRole() {
        return this.updateRole;
    }

    public String getRemark() {
        return this.remark;
    }

    public Long getMin() {
        return this.min;
    }

    public Long getMax() {
        return this.max;
    }

    public List<EnumInfo> getEnums() {
        return this.enums;
    }

    public Boolean getIsCollection() {
        return this.isCollection;
    }

    public Integer getImportNum() {
        return this.importNum;
    }

    public Aggregate[] getAggregates() {
        return this.aggregates;
    }

    public Boolean getIsGroup() {
        return this.isGroup;
    }

    public void setFieldClass(Class<?> fieldClass) {
        this.fieldClass = fieldClass;
    }

    public void setIsKey(Boolean isKey) {
        this.isKey = isKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setIsCode(Boolean isCode) {
        this.isCode = isCode;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public void setRelation(MetaRelation relation) {
        this.relation = relation;
    }

    public void setRelationDomain(String relationDomain) {
        this.relationDomain = relationDomain;
    }

    public void setRelationKey(String relationKey) {
        this.relationKey = relationKey;
    }

    public void setValidator(Set<String> validator) {
        this.validator = validator;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setIsVirtual(Boolean isVirtual) {
        this.isVirtual = isVirtual;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public void setIsUnique(Boolean isUnique) {
        this.isUnique = isUnique;
    }

    public void setUiType(UIType uiType) {
        this.uiType = uiType;
    }

    public void setColSpan(Integer colSpan) {
        this.colSpan = colSpan;
    }

    public void setRowSpan(Integer rowSpan) {
        this.rowSpan = rowSpan;
    }

    public void setIsQuery(Boolean isQuery) {
        this.isQuery = isQuery;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public void setQueryOrder(Integer queryOrder) {
        this.queryOrder = queryOrder;
    }

    public void setIsRangeQuery(Boolean isRangeQuery) {
        this.isRangeQuery = isRangeQuery;
    }

    public void setQueryRole(String[] queryRole) {
        this.queryRole = queryRole;
    }

    public void setIsList(Boolean isList) {
        this.isList = isList;
    }

    public void setIsSort(Boolean isSort) {
        this.isSort = isSort;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }

    public void setListRole(String[] listRole) {
        this.listRole = listRole;
    }

    public void setIsCreate(Boolean isCreate) {
        this.isCreate = isCreate;
    }

    public void setCreateOrder(Integer createOrder) {
        this.createOrder = createOrder;
    }

    public void setCreateRole(String[] createRole) {
        this.createRole = createRole;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setUpdateOrder(Integer updateOrder) {
        this.updateOrder = updateOrder;
    }

    public void setUpdateRole(String[] updateRole) {
        this.updateRole = updateRole;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public void setEnums(List<EnumInfo> enums) {
        this.enums = enums;
    }

    public void setIsCollection(Boolean isCollection) {
        this.isCollection = isCollection;
    }

    public void setImportNum(Integer importNum) {
        this.importNum = importNum;
    }

    public void setAggregates(Aggregate[] aggregates) {
        this.aggregates = aggregates;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MetaField)) {
            return false;
        } else {
            MetaField other = (MetaField)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label475: {
                    Object this$fieldClass = this.getFieldClass();
                    Object other$fieldClass = other.getFieldClass();
                    if (this$fieldClass == null) {
                        if (other$fieldClass == null) {
                            break label475;
                        }
                    } else if (this$fieldClass.equals(other$fieldClass)) {
                        break label475;
                    }

                    return false;
                }

                Object this$isKey = this.getIsKey();
                Object other$isKey = other.getIsKey();
                if (this$isKey == null) {
                    if (other$isKey != null) {
                        return false;
                    }
                } else if (!this$isKey.equals(other$isKey)) {
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

                label454: {
                    Object this$label = this.getLabel();
                    Object other$label = other.getLabel();
                    if (this$label == null) {
                        if (other$label == null) {
                            break label454;
                        }
                    } else if (this$label.equals(other$label)) {
                        break label454;
                    }

                    return false;
                }

                label447: {
                    Object this$columnName = this.getColumnName();
                    Object other$columnName = other.getColumnName();
                    if (this$columnName == null) {
                        if (other$columnName == null) {
                            break label447;
                        }
                    } else if (this$columnName.equals(other$columnName)) {
                        break label447;
                    }

                    return false;
                }

                label440: {
                    Object this$type = this.getType();
                    Object other$type = other.getType();
                    if (this$type == null) {
                        if (other$type == null) {
                            break label440;
                        }
                    } else if (this$type.equals(other$type)) {
                        break label440;
                    }

                    return false;
                }

                Object this$isCode = this.getIsCode();
                Object other$isCode = other.getIsCode();
                if (this$isCode == null) {
                    if (other$isCode != null) {
                        return false;
                    }
                } else if (!this$isCode.equals(other$isCode)) {
                    return false;
                }

                label426: {
                    Object this$codeValue = this.getCodeValue();
                    Object other$codeValue = other.getCodeValue();
                    if (this$codeValue == null) {
                        if (other$codeValue == null) {
                            break label426;
                        }
                    } else if (this$codeValue.equals(other$codeValue)) {
                        break label426;
                    }

                    return false;
                }

                Object this$relation = this.getRelation();
                Object other$relation = other.getRelation();
                if (this$relation == null) {
                    if (other$relation != null) {
                        return false;
                    }
                } else if (!this$relation.equals(other$relation)) {
                    return false;
                }

                label412: {
                    Object this$relationDomain = this.getRelationDomain();
                    Object other$relationDomain = other.getRelationDomain();
                    if (this$relationDomain == null) {
                        if (other$relationDomain == null) {
                            break label412;
                        }
                    } else if (this$relationDomain.equals(other$relationDomain)) {
                        break label412;
                    }

                    return false;
                }

                Object this$relationKey = this.getRelationKey();
                Object other$relationKey = other.getRelationKey();
                if (this$relationKey == null) {
                    if (other$relationKey != null) {
                        return false;
                    }
                } else if (!this$relationKey.equals(other$relationKey)) {
                    return false;
                }

                Object this$validator = this.getValidator();
                Object other$validator = other.getValidator();
                if (this$validator == null) {
                    if (other$validator != null) {
                        return false;
                    }
                } else if (!this$validator.equals(other$validator)) {
                    return false;
                }

                label391: {
                    Object this$defaultValue = this.getDefaultValue();
                    Object other$defaultValue = other.getDefaultValue();
                    if (this$defaultValue == null) {
                        if (other$defaultValue == null) {
                            break label391;
                        }
                    } else if (this$defaultValue.equals(other$defaultValue)) {
                        break label391;
                    }

                    return false;
                }

                label384: {
                    Object this$isVirtual = this.getIsVirtual();
                    Object other$isVirtual = other.getIsVirtual();
                    if (this$isVirtual == null) {
                        if (other$isVirtual == null) {
                            break label384;
                        }
                    } else if (this$isVirtual.equals(other$isVirtual)) {
                        break label384;
                    }

                    return false;
                }

                Object this$isRequired = this.getIsRequired();
                Object other$isRequired = other.getIsRequired();
                if (this$isRequired == null) {
                    if (other$isRequired != null) {
                        return false;
                    }
                } else if (!this$isRequired.equals(other$isRequired)) {
                    return false;
                }

                Object this$isUnique = this.getIsUnique();
                Object other$isUnique = other.getIsUnique();
                if (this$isUnique == null) {
                    if (other$isUnique != null) {
                        return false;
                    }
                } else if (!this$isUnique.equals(other$isUnique)) {
                    return false;
                }

                label363: {
                    Object this$uiType = this.getUiType();
                    Object other$uiType = other.getUiType();
                    if (this$uiType == null) {
                        if (other$uiType == null) {
                            break label363;
                        }
                    } else if (this$uiType.equals(other$uiType)) {
                        break label363;
                    }

                    return false;
                }

                Object this$colSpan = this.getColSpan();
                Object other$colSpan = other.getColSpan();
                if (this$colSpan == null) {
                    if (other$colSpan != null) {
                        return false;
                    }
                } else if (!this$colSpan.equals(other$colSpan)) {
                    return false;
                }

                Object this$rowSpan = this.getRowSpan();
                Object other$rowSpan = other.getRowSpan();
                if (this$rowSpan == null) {
                    if (other$rowSpan != null) {
                        return false;
                    }
                } else if (!this$rowSpan.equals(other$rowSpan)) {
                    return false;
                }

                label342: {
                    Object this$isQuery = this.getIsQuery();
                    Object other$isQuery = other.getIsQuery();
                    if (this$isQuery == null) {
                        if (other$isQuery == null) {
                            break label342;
                        }
                    } else if (this$isQuery.equals(other$isQuery)) {
                        break label342;
                    }

                    return false;
                }

                label335: {
                    Object this$queryType = this.getQueryType();
                    Object other$queryType = other.getQueryType();
                    if (this$queryType == null) {
                        if (other$queryType == null) {
                            break label335;
                        }
                    } else if (this$queryType.equals(other$queryType)) {
                        break label335;
                    }

                    return false;
                }

                label328: {
                    Object this$queryOrder = this.getQueryOrder();
                    Object other$queryOrder = other.getQueryOrder();
                    if (this$queryOrder == null) {
                        if (other$queryOrder == null) {
                            break label328;
                        }
                    } else if (this$queryOrder.equals(other$queryOrder)) {
                        break label328;
                    }

                    return false;
                }

                Object this$isRangeQuery = this.getIsRangeQuery();
                Object other$isRangeQuery = other.getIsRangeQuery();
                if (this$isRangeQuery == null) {
                    if (other$isRangeQuery != null) {
                        return false;
                    }
                } else if (!this$isRangeQuery.equals(other$isRangeQuery)) {
                    return false;
                }

                if (!Arrays.deepEquals(this.getQueryRole(), other.getQueryRole())) {
                    return false;
                } else {
                    Object this$isList = this.getIsList();
                    Object other$isList = other.getIsList();
                    if (this$isList == null) {
                        if (other$isList != null) {
                            return false;
                        }
                    } else if (!this$isList.equals(other$isList)) {
                        return false;
                    }

                    Object this$isSort = this.getIsSort();
                    Object other$isSort = other.getIsSort();
                    if (this$isSort == null) {
                        if (other$isSort != null) {
                            return false;
                        }
                    } else if (!this$isSort.equals(other$isSort)) {
                        return false;
                    }

                    label299: {
                        Object this$listOrder = this.getListOrder();
                        Object other$listOrder = other.getListOrder();
                        if (this$listOrder == null) {
                            if (other$listOrder == null) {
                                break label299;
                            }
                        } else if (this$listOrder.equals(other$listOrder)) {
                            break label299;
                        }

                        return false;
                    }

                    if (!Arrays.deepEquals(this.getListRole(), other.getListRole())) {
                        return false;
                    } else {
                        Object this$isCreate = this.getIsCreate();
                        Object other$isCreate = other.getIsCreate();
                        if (this$isCreate == null) {
                            if (other$isCreate != null) {
                                return false;
                            }
                        } else if (!this$isCreate.equals(other$isCreate)) {
                            return false;
                        }

                        label284: {
                            Object this$createOrder = this.getCreateOrder();
                            Object other$createOrder = other.getCreateOrder();
                            if (this$createOrder == null) {
                                if (other$createOrder == null) {
                                    break label284;
                                }
                            } else if (this$createOrder.equals(other$createOrder)) {
                                break label284;
                            }

                            return false;
                        }

                        if (!Arrays.deepEquals(this.getCreateRole(), other.getCreateRole())) {
                            return false;
                        } else {
                            Object this$isUpdate = this.getIsUpdate();
                            Object other$isUpdate = other.getIsUpdate();
                            if (this$isUpdate == null) {
                                if (other$isUpdate != null) {
                                    return false;
                                }
                            } else if (!this$isUpdate.equals(other$isUpdate)) {
                                return false;
                            }

                            Object this$updateOrder = this.getUpdateOrder();
                            Object other$updateOrder = other.getUpdateOrder();
                            if (this$updateOrder == null) {
                                if (other$updateOrder != null) {
                                    return false;
                                }
                            } else if (!this$updateOrder.equals(other$updateOrder)) {
                                return false;
                            }

                            if (!Arrays.deepEquals(this.getUpdateRole(), other.getUpdateRole())) {
                                return false;
                            } else {
                                Object this$remark = this.getRemark();
                                Object other$remark = other.getRemark();
                                if (this$remark == null) {
                                    if (other$remark != null) {
                                        return false;
                                    }
                                } else if (!this$remark.equals(other$remark)) {
                                    return false;
                                }

                                label254: {
                                    Object this$min = this.getMin();
                                    Object other$min = other.getMin();
                                    if (this$min == null) {
                                        if (other$min == null) {
                                            break label254;
                                        }
                                    } else if (this$min.equals(other$min)) {
                                        break label254;
                                    }

                                    return false;
                                }

                                label247: {
                                    Object this$max = this.getMax();
                                    Object other$max = other.getMax();
                                    if (this$max == null) {
                                        if (other$max == null) {
                                            break label247;
                                        }
                                    } else if (this$max.equals(other$max)) {
                                        break label247;
                                    }

                                    return false;
                                }

                                Object this$enums = this.getEnums();
                                Object other$enums = other.getEnums();
                                if (this$enums == null) {
                                    if (other$enums != null) {
                                        return false;
                                    }
                                } else if (!this$enums.equals(other$enums)) {
                                    return false;
                                }

                                label233: {
                                    Object this$isCollection = this.getIsCollection();
                                    Object other$isCollection = other.getIsCollection();
                                    if (this$isCollection == null) {
                                        if (other$isCollection == null) {
                                            break label233;
                                        }
                                    } else if (this$isCollection.equals(other$isCollection)) {
                                        break label233;
                                    }

                                    return false;
                                }

                                Object this$importNum = this.getImportNum();
                                Object other$importNum = other.getImportNum();
                                if (this$importNum == null) {
                                    if (other$importNum != null) {
                                        return false;
                                    }
                                } else if (!this$importNum.equals(other$importNum)) {
                                    return false;
                                }

                                if (!Arrays.deepEquals(this.getAggregates(), other.getAggregates())) {
                                    return false;
                                } else {
                                    Object this$isGroup = this.getIsGroup();
                                    Object other$isGroup = other.getIsGroup();
                                    if (this$isGroup == null) {
                                        if (other$isGroup != null) {
                                            return false;
                                        }
                                    } else if (!this$isGroup.equals(other$isGroup)) {
                                        return false;
                                    }

                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof MetaField;
    }



    public String toString() {
        return "MetaField(fieldClass=" + this.getFieldClass() + ", isKey=" + this.getIsKey() + ", name=" + this.getName() + ", label=" + this.getLabel() + ", columnName=" + this.getColumnName() + ", type=" + this.getType() + ", isCode=" + this.getIsCode() + ", codeValue=" + this.getCodeValue() + ", relation=" + this.getRelation() + ", relationDomain=" + this.getRelationDomain() + ", relationKey=" + this.getRelationKey() + ", validator=" + this.getValidator() + ", defaultValue=" + this.getDefaultValue() + ", isVirtual=" + this.getIsVirtual() + ", isRequired=" + this.getIsRequired() + ", isUnique=" + this.getIsUnique() + ", uiType=" + this.getUiType() + ", colSpan=" + this.getColSpan() + ", rowSpan=" + this.getRowSpan() + ", isQuery=" + this.getIsQuery() + ", queryType=" + this.getQueryType() + ", queryOrder=" + this.getQueryOrder() + ", isRangeQuery=" + this.getIsRangeQuery() + ", queryRole=" + Arrays.deepToString(this.getQueryRole()) + ", isList=" + this.getIsList() + ", isSort=" + this.getIsSort() + ", listOrder=" + this.getListOrder() + ", listRole=" + Arrays.deepToString(this.getListRole()) + ", isCreate=" + this.getIsCreate() + ", createOrder=" + this.getCreateOrder() + ", createRole=" + Arrays.deepToString(this.getCreateRole()) + ", isUpdate=" + this.getIsUpdate() + ", updateOrder=" + this.getUpdateOrder() + ", updateRole=" + Arrays.deepToString(this.getUpdateRole()) + ", remark=" + this.getRemark() + ", min=" + this.getMin() + ", max=" + this.getMax() + ", enums=" + this.getEnums() + ", isCollection=" + this.getIsCollection() + ", importNum=" + this.getImportNum() + ", aggregates=" + Arrays.deepToString(this.getAggregates()) + ", isGroup=" + this.getIsGroup() + ")";
    }

    public MetaField() {
    }

    @ConstructorProperties({"fieldClass", "isKey", "name", "label", "columnName", "type", "isCode", "codeValue", "relation", "relationDomain", "relationKey", "validator", "defaultValue", "isVirtual", "isRequired", "isUnique", "uiType", "colSpan", "rowSpan", "isQuery", "queryType", "queryOrder", "isRangeQuery", "queryRole", "isList", "isSort", "listOrder", "listRole", "isCreate", "createOrder", "createRole", "isUpdate", "updateOrder", "updateRole", "remark", "min", "max", "enums", "isCollection", "importNum", "aggregates", "isGroup"})
    private MetaField(Class<?> fieldClass, Boolean isKey, String name, String label, String columnName, Type type, Boolean isCode, String codeValue, MetaRelation relation, String relationDomain, String relationKey, Set<String> validator, String defaultValue, Boolean isVirtual, Boolean isRequired, Boolean isUnique, UIType uiType, Integer colSpan, Integer rowSpan, Boolean isQuery, String queryType, Integer queryOrder, Boolean isRangeQuery, String[] queryRole, Boolean isList, Boolean isSort, Integer listOrder, String[] listRole, Boolean isCreate, Integer createOrder, String[] createRole, Boolean isUpdate, Integer updateOrder, String[] updateRole, String remark, Long min, Long max, List<EnumInfo> enums, Boolean isCollection, Integer importNum, Aggregate[] aggregates, Boolean isGroup) {
        this.fieldClass = fieldClass;
        this.isKey = isKey;
        this.name = name;
        this.label = label;
        this.columnName = columnName;
        this.type = type;
        this.isCode = isCode;
        this.codeValue = codeValue;
        this.relation = relation;
        this.relationDomain = relationDomain;
        this.relationKey = relationKey;
        this.validator = validator;
        this.defaultValue = defaultValue;
        this.isVirtual = isVirtual;
        this.isRequired = isRequired;
        this.isUnique = isUnique;
        this.uiType = uiType;
        this.colSpan = colSpan;
        this.rowSpan = rowSpan;
        this.isQuery = isQuery;
        this.queryType = queryType;
        this.queryOrder = queryOrder;
        this.isRangeQuery = isRangeQuery;
        this.queryRole = queryRole;
        this.isList = isList;
        this.isSort = isSort;
        this.listOrder = listOrder;
        this.listRole = listRole;
        this.isCreate = isCreate;
        this.createOrder = createOrder;
        this.createRole = createRole;
        this.isUpdate = isUpdate;
        this.updateOrder = updateOrder;
        this.updateRole = updateRole;
        this.remark = remark;
        this.min = min;
        this.max = max;
        this.enums = enums;
        this.isCollection = isCollection;
        this.importNum = importNum;
        this.aggregates = aggregates;
        this.isGroup = isGroup;
    }

    public static MetaField of(Class<?> fieldClass, Boolean isKey, String name, String label, String columnName, Type type, Boolean isCode, String codeValue, MetaRelation relation, String relationDomain, String relationKey, Set<String> validator, String defaultValue, Boolean isVirtual, Boolean isRequired, Boolean isUnique, UIType uiType, Integer colSpan, Integer rowSpan, Boolean isQuery, String queryType, Integer queryOrder, Boolean isRangeQuery, String[] queryRole, Boolean isList, Boolean isSort, Integer listOrder, String[] listRole, Boolean isCreate, Integer createOrder, String[] createRole, Boolean isUpdate, Integer updateOrder, String[] updateRole, String remark, Long min, Long max, List<EnumInfo> enums, Boolean isCollection, Integer importNum, Aggregate[] aggregates, Boolean isGroup) {
        return new MetaField(fieldClass, isKey, name, label, columnName, type, isCode, codeValue, relation, relationDomain, relationKey, validator, defaultValue, isVirtual, isRequired, isUnique, uiType, colSpan, rowSpan, isQuery, queryType, queryOrder, isRangeQuery, queryRole, isList, isSort, listOrder, listRole, isCreate, createOrder, createRole, isUpdate, updateOrder, updateRole, remark, min, max, enums, isCollection, importNum, aggregates, isGroup);
    }

    public static class MetaFieldBuilder {
        private Class<?> fieldClass;
        private Boolean isKey;
        private String name;
        private String label;
        private String columnName;
        private Type type;
        private Boolean isCode;
        private String codeValue;
        private MetaRelation relation;
        private String relationDomain;
        private String relationKey;
        private Set<String> validator;
        private String defaultValue;
        private boolean isVirtual$set;
        private Boolean isVirtual;
        private boolean isRequired$set;
        private Boolean isRequired;
        private boolean isUnique$set;
        private Boolean isUnique;
        private UIType uiType;
        private Integer colSpan;
        private Integer rowSpan;
        private Boolean isQuery;
        private String queryType;
        private Integer queryOrder;
        private boolean isRangeQuery$set;
        private Boolean isRangeQuery;
        private String[] queryRole;
        private Boolean isList;
        private Boolean isSort;
        private Integer listOrder;
        private String[] listRole;
        private Boolean isCreate;
        private Integer createOrder;
        private String[] createRole;
        private Boolean isUpdate;
        private Integer updateOrder;
        private String[] updateRole;
        private String remark;
        private Long min;
        private Long max;
        private List<EnumInfo> enums;
        private Boolean isCollection;
        private Integer importNum;
        private Aggregate[] aggregates;
        private Boolean isGroup;

        MetaFieldBuilder() {
        }

        public MetaField.MetaFieldBuilder fieldClass(Class<?> fieldClass) {
            this.fieldClass = fieldClass;
            return this;
        }

        public MetaField.MetaFieldBuilder isKey(Boolean isKey) {
            this.isKey = isKey;
            return this;
        }

        public MetaField.MetaFieldBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MetaField.MetaFieldBuilder label(String label) {
            this.label = label;
            return this;
        }

        public MetaField.MetaFieldBuilder columnName(String columnName) {
            this.columnName = columnName;
            return this;
        }

        public MetaField.MetaFieldBuilder type(Type type) {
            this.type = type;
            return this;
        }

        public MetaField.MetaFieldBuilder isCode(Boolean isCode) {
            this.isCode = isCode;
            return this;
        }

        public MetaField.MetaFieldBuilder codeValue(String codeValue) {
            this.codeValue = codeValue;
            return this;
        }

        public MetaField.MetaFieldBuilder relation(MetaRelation relation) {
            this.relation = relation;
            return this;
        }

        public MetaField.MetaFieldBuilder relationDomain(String relationDomain) {
            this.relationDomain = relationDomain;
            return this;
        }

        public MetaField.MetaFieldBuilder relationKey(String relationKey) {
            this.relationKey = relationKey;
            return this;
        }

        public MetaField.MetaFieldBuilder validator(Set<String> validator) {
            this.validator = validator;
            return this;
        }

        public MetaField.MetaFieldBuilder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public MetaField.MetaFieldBuilder isVirtual(Boolean isVirtual) {
            this.isVirtual = isVirtual;
            this.isVirtual$set = true;
            return this;
        }

        public MetaField.MetaFieldBuilder isRequired(Boolean isRequired) {
            this.isRequired = isRequired;
            this.isRequired$set = true;
            return this;
        }

        public MetaField.MetaFieldBuilder isUnique(Boolean isUnique) {
            this.isUnique = isUnique;
            this.isUnique$set = true;
            return this;
        }

        public MetaField.MetaFieldBuilder uiType(UIType uiType) {
            this.uiType = uiType;
            return this;
        }

        public MetaField.MetaFieldBuilder colSpan(Integer colSpan) {
            this.colSpan = colSpan;
            return this;
        }

        public MetaField.MetaFieldBuilder rowSpan(Integer rowSpan) {
            this.rowSpan = rowSpan;
            return this;
        }

        public MetaField.MetaFieldBuilder isQuery(Boolean isQuery) {
            this.isQuery = isQuery;
            return this;
        }

        public MetaField.MetaFieldBuilder queryType(String queryType) {
            this.queryType = queryType;
            return this;
        }

        public MetaField.MetaFieldBuilder queryOrder(Integer queryOrder) {
            this.queryOrder = queryOrder;
            return this;
        }

        public MetaField.MetaFieldBuilder isRangeQuery(Boolean isRangeQuery) {
            this.isRangeQuery = isRangeQuery;
            this.isRangeQuery$set = true;
            return this;
        }

        public MetaField.MetaFieldBuilder queryRole(String[] queryRole) {
            this.queryRole = queryRole;
            return this;
        }

        public MetaField.MetaFieldBuilder isList(Boolean isList) {
            this.isList = isList;
            return this;
        }

        public MetaField.MetaFieldBuilder isSort(Boolean isSort) {
            this.isSort = isSort;
            return this;
        }

        public MetaField.MetaFieldBuilder listOrder(Integer listOrder) {
            this.listOrder = listOrder;
            return this;
        }

        public MetaField.MetaFieldBuilder listRole(String[] listRole) {
            this.listRole = listRole;
            return this;
        }

        public MetaField.MetaFieldBuilder isCreate(Boolean isCreate) {
            this.isCreate = isCreate;
            return this;
        }

        public MetaField.MetaFieldBuilder createOrder(Integer createOrder) {
            this.createOrder = createOrder;
            return this;
        }

        public MetaField.MetaFieldBuilder createRole(String[] createRole) {
            this.createRole = createRole;
            return this;
        }

        public MetaField.MetaFieldBuilder isUpdate(Boolean isUpdate) {
            this.isUpdate = isUpdate;
            return this;
        }

        public MetaField.MetaFieldBuilder updateOrder(Integer updateOrder) {
            this.updateOrder = updateOrder;
            return this;
        }

        public MetaField.MetaFieldBuilder updateRole(String[] updateRole) {
            this.updateRole = updateRole;
            return this;
        }

        public MetaField.MetaFieldBuilder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public MetaField.MetaFieldBuilder min(Long min) {
            this.min = min;
            return this;
        }

        public MetaField.MetaFieldBuilder max(Long max) {
            this.max = max;
            return this;
        }

        public MetaField.MetaFieldBuilder enums(List<EnumInfo> enums) {
            this.enums = enums;
            return this;
        }

        public MetaField.MetaFieldBuilder isCollection(Boolean isCollection) {
            this.isCollection = isCollection;
            return this;
        }

        public MetaField.MetaFieldBuilder importNum(Integer importNum) {
            this.importNum = importNum;
            return this;
        }

        public MetaField.MetaFieldBuilder aggregates(Aggregate[] aggregates) {
            this.aggregates = aggregates;
            return this;
        }

        public MetaField.MetaFieldBuilder isGroup(Boolean isGroup) {
            this.isGroup = isGroup;
            return this;
        }

        public MetaField build() {
            return new MetaField(this.fieldClass, this.isKey, this.name, this.label, this.columnName, this.type, this.isCode, this.codeValue, this.relation, this.relationDomain, this.relationKey, this.validator, this.defaultValue, this.isVirtual$set ? this.isVirtual : MetaField.$default$isVirtual(), this.isRequired$set ? this.isRequired : MetaField.$default$isRequired(), this.isUnique$set ? this.isUnique : MetaField.$default$isUnique(), this.uiType, this.colSpan, this.rowSpan, this.isQuery, this.queryType, this.queryOrder, this.isRangeQuery$set ? this.isRangeQuery : MetaField.$default$isRangeQuery(), this.queryRole, this.isList, this.isSort, this.listOrder, this.listRole, this.isCreate, this.createOrder, this.createRole, this.isUpdate, this.updateOrder, this.updateRole, this.remark, this.min, this.max, this.enums, this.isCollection, this.importNum, this.aggregates, this.isGroup);
        }

        public String toString() {
            return "MetaField.MetaFieldBuilder(fieldClass=" + this.fieldClass + ", isKey=" + this.isKey + ", name=" + this.name + ", label=" + this.label + ", columnName=" + this.columnName + ", type=" + this.type + ", isCode=" + this.isCode + ", codeValue=" + this.codeValue + ", relation=" + this.relation + ", relationDomain=" + this.relationDomain + ", relationKey=" + this.relationKey + ", validator=" + this.validator + ", defaultValue=" + this.defaultValue + ", isVirtual=" + this.isVirtual + ", isRequired=" + this.isRequired + ", isUnique=" + this.isUnique + ", uiType=" + this.uiType + ", colSpan=" + this.colSpan + ", rowSpan=" + this.rowSpan + ", isQuery=" + this.isQuery + ", queryType=" + this.queryType + ", queryOrder=" + this.queryOrder + ", isRangeQuery=" + this.isRangeQuery + ", queryRole=" + Arrays.deepToString(this.queryRole) + ", isList=" + this.isList + ", isSort=" + this.isSort + ", listOrder=" + this.listOrder + ", listRole=" + Arrays.deepToString(this.listRole) + ", isCreate=" + this.isCreate + ", createOrder=" + this.createOrder + ", createRole=" + Arrays.deepToString(this.createRole) + ", isUpdate=" + this.isUpdate + ", updateOrder=" + this.updateOrder + ", updateRole=" + Arrays.deepToString(this.updateRole) + ", remark=" + this.remark + ", min=" + this.min + ", max=" + this.max + ", enums=" + this.enums + ", isCollection=" + this.isCollection + ", importNum=" + this.importNum + ", aggregates=" + Arrays.deepToString(this.aggregates) + ", isGroup=" + this.isGroup + ")";
        }
    }
}
