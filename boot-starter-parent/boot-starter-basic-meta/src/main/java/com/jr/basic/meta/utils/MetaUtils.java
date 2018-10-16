//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.jr.basic.meta.annotation.Meta;
import com.jr.basic.meta.annotation.Relation;
import com.jr.basic.meta.annotation.VField;
import com.jr.basic.meta.annotation.VO;
import com.jr.basic.meta.domain.EnumInfo;
import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.domain.MetaField;
import com.jr.basic.meta.domain.MetaRelation;
import com.jr.basic.meta.domain.MetaDomain.MetaDomainBuilder;
import com.jr.basic.meta.domain.MetaField.MetaFieldBuilder;
import com.jr.basic.meta.domain.MetaRelation.Type;
import com.jr.basic.meta.enums.UIType;
import com.jr.basic.meta.service.MetaNameStrategy;
import com.jr.basic.meta.service.impl.MetaNameStrategyImpl;
import com.jr.basic.meta.validation.Phone;
import com.jr.basic.meta.vo.ValueField;
import com.jr.basic.meta.vo.ValueObject;
import com.jr.basic.meta.vo.ValueObject.ValueObjectBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public final class MetaUtils {
    private static MetaNameStrategy nameStrategy = new MetaNameStrategyImpl();

    private MetaUtils() {
    }

    public static MetaDomain resolveDomain(Class<?> domainClass) {
        Meta meta = (Meta)domainClass.getAnnotation(Meta.class);
        String module = meta.module();
        String name = meta.name();
        String label = meta.label();
        String table = meta.table();
        String showField = meta.showField();
        MetaDomainBuilder builder = MetaDomain.builder().domainClass(domainClass).module(StringUtils.isBlank(module) ? nameStrategy.domainClassToModuleName(domainClass) : module).name(StringUtils.isBlank(name) ? nameStrategy.domainClassToName(domainClass) : name).label(StringUtils.isBlank(label) ? name : label).tableName(StringUtils.isBlank(table) ? nameStrategy.domainClassToTableName(domainClass) : table).showField(StringUtils.isBlank(showField) ? "id" : showField).fullName(domainClass.getName()).allowDynamic(meta.allowDynamic()).allowCreate(meta.allowCreate()).allowUpdate(meta.allowUpdate()).allowDelete(meta.allowDelete()).enable(meta.enable()).isCache(meta.isCache()).isLogicDelete(meta.isLogicDelete()).viewType(meta.viewType()).listRole(meta.listRole()).createRole(meta.createRole()).updateRole(meta.updateRole()).deleteRole(meta.deleteRole()).owner(meta.owner()).remark(meta.remark()).defaultOrder(meta.defaultOrder().length > 0 ? meta.defaultOrder()[0] : null).defaultOrderType(meta.defaultOrderType().length > 0 ? meta.defaultOrderType()[0] : null);

        Builder fieldsBuilder;
        for(fieldsBuilder = ImmutableList.builder(); domainClass != Object.class; domainClass = domainClass.getSuperclass()) {
            Field[] var9 = domainClass.getDeclaredFields();
            int var10 = var9.length;

            for(int var11 = 0; var11 < var10; ++var11) {
                Field field = var9[var11];
                com.jr.basic.meta.annotation.Field fieldAnno = (com.jr.basic.meta.annotation.Field)field.getAnnotation(com.jr.basic.meta.annotation.Field.class);
                if (fieldAnno != null) {
                    fieldsBuilder.add(resolveMetaField(domainClass, field, fieldAnno));
                }
            }
        }

        builder.fields(fieldsBuilder.build());
        return builder.build();
    }

    private static MetaField resolveMetaField(Class<?> domainClass, Field field, com.jr.basic.meta.annotation.Field fieldAnno) {
        String fname = StringUtils.isBlank(fieldAnno.name()) ? nameStrategy.fieldNameToName(field.getName()) : fieldAnno.name();
        String flabel = fieldAnno.label();
        String columnName = fieldAnno.columnName();
        MetaFieldBuilder fieldBuilder = MetaField.builder().isKey(fieldAnno.isKey()).name(fname).label(StringUtils.isBlank(flabel) ? fname : flabel).columnName(StringUtils.isBlank(columnName) ? nameStrategy.fieldNameToColumnName(field.getName()) : columnName).fieldClass(field.getType()).type(convertToType(field.getType())).isCollection(field.getType().isAssignableFrom(Collection.class)).queryType(fieldAnno.queryType()).isVirtual(fieldAnno.isVirtual()).isRequired(fieldAnno.isRequired()).isUnique(fieldAnno.isUnique()).defaultValue(fieldAnno.defaultValue()).uiType(fieldAnno.uiType().length == 0 ? convertToUiType(field.getType()) : fieldAnno.uiType()[0]).rowSpan(fieldAnno.rowSpan()).colSpan(fieldAnno.colSpan()).isQuery(fieldAnno.isQuery()).isSort(fieldAnno.isSort()).isRangeQuery(fieldAnno.isRangeQuery()).queryRole(fieldAnno.queryRole()).queryOrder(fieldAnno.order()).isList(fieldAnno.isList()).listOrder(fieldAnno.order()).listRole(fieldAnno.listRole()).isCreate(fieldAnno.isCreate()).createOrder(fieldAnno.order()).createRole(fieldAnno.createRole()).isUpdate(fieldAnno.isUpdate()).updateOrder(fieldAnno.order()).updateRole(fieldAnno.updateRole()).importNum(fieldAnno.importNum()).aggregates(fieldAnno.aggregates()).isGroup(fieldAnno.isGroup()).remark(fieldAnno.remark());
        resolveRelation(domainClass, field, fieldBuilder);
        resolveEnums(field, fieldBuilder);
        resolveValidation(field, fieldBuilder);
        return fieldBuilder.build();
    }

    private static void resolveRelation(Class<?> domainClass, Field field, MetaFieldBuilder builder) {
        Relation relation = (Relation)field.getAnnotation(Relation.class);
        if (relation != null) {
            MetaRelation metaRelation = MetaRelation.of(relation.type(), relation.domain(), relation.joinField(), relation.showField(), relation.remark());
            if (Type.MANY_TO_MANY.equals(relation.type())) {
                metaRelation.setTableName(String.format("MID_%s_%s", nameStrategy.domainClassToTableName(domainClass), nameStrategy.domainClassToTableName(metaRelation.getDomain())));
                builder.type(com.jr.basic.meta.enums.Type.DOMAIN);
                metaRelation.setDomainName(nameStrategy.domainClassToName(metaRelation.getDomain()));
                builder.isCollection(true);
            } else if (Type.ONE_TO_MANY.equals(relation.type())) {
                builder.type(com.jr.basic.meta.enums.Type.DOMAIN);
                metaRelation.setDomainName(nameStrategy.domainClassToName(metaRelation.getDomain()));
                builder.isCollection(true);
            } else if (Type.ONE_TO_ONE.equals(relation.type()) || Type.MANY_TO_ONE.equals(relation.type())) {
                if (!ClassUtils.isPrimitiveOrWrapper(field.getType())) {
                    builder.type(com.jr.basic.meta.enums.Type.DOMAIN);
                }

                metaRelation.setDomainName(nameStrategy.domainClassToName(metaRelation.getDomain()));
                builder.isCollection(false);
            }

            metaRelation.setJoinTableName(nameStrategy.domainClassToTableName(metaRelation.getDomain()));
            builder.relation(metaRelation);
        }

    }

    private static void resolveEnums(Field field, MetaFieldBuilder builder) {
        Class<?> clazz = field.getType();
        if (clazz.isEnum()) {
            try {
                Method name = clazz.getMethod("name");
                Method label = clazz.getMethod("label");
                Object[] enumObjects = clazz.getEnumConstants();
                List<EnumInfo> enums = new ArrayList();
                Object[] var7 = enumObjects;
                int var8 = enumObjects.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    Object e = var7[var9];
                    enums.add(new EnumInfo((String)name.invoke(e), (String)label.invoke(e)));
                }

                builder.enums(enums);
            } catch (Exception var11) {
                throw new RuntimeException(var11);
            }
        }

    }

    private static void resolveValidation(Field field, MetaFieldBuilder builder) {
        NotNull notNull = (NotNull)field.getAnnotation(NotNull.class);
        if (notNull != null) {
            builder.isRequired(true);
        }

        NotBlank notBlank = (NotBlank)field.getAnnotation(NotBlank.class);
        if (notBlank != null) {
            builder.isRequired(true);
        }

        Min min = (Min)field.getAnnotation(Min.class);
        Max max = (Max)field.getAnnotation(Max.class);
        if (min != null) {
            builder.min(min.value());
        }

        if (max != null) {
            builder.max(max.value());
        }

        Length len = (Length)field.getAnnotation(Length.class);
        if (len != null) {
            builder.min((long)len.min());
            builder.max((long)len.max());
        }

        Set<String> validator = new TreeSet();
        Email email = (Email)field.getAnnotation(Email.class);
        if (email != null) {
            validator.add("email");
        }

        Phone phone = (Phone)field.getAnnotation(Phone.class);
        if (phone != null) {
            validator.add("phone");
        }

        builder.validator(validator);
    }

    public static ValueObject resolveVo(Class<?> voClass) {
        VO vo = (VO)voClass.getAnnotation(VO.class);
        String name = vo.name();
        ValueObjectBuilder builder = ValueObject.builder().label(vo.label()).name(StringUtils.isBlank(name) ? nameStrategy.domainClassToName(voClass) : name).domainClass(vo.domain()).domainName(nameStrategy.domainClassToName(vo.domain())).isState(vo.isState()).remark(vo.remark());

        Builder fieldsBuilder;
        for(fieldsBuilder = ImmutableList.builder(); voClass != Object.class; voClass = voClass.getSuperclass()) {
            Field[] var5 = voClass.getDeclaredFields();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Field field = var5[var7];
                VField fieldAnno = (VField)field.getAnnotation(VField.class);
                if (fieldAnno != null) {
                    fieldsBuilder.add(resolveValueField(voClass, field, fieldAnno));
                }
            }
        }

        builder.fields(fieldsBuilder.build());
        return builder.build();
    }

    private static ValueField resolveValueField(Class<?> voClass, Field field, VField vfield) {
        String name = vfield.name();
        return ValueField.builder().label(vfield.label()).name(StringUtils.isBlank(name) ? field.getName() : name).metaField(vfield.metaField()).type(convertToType(field.getType())).remark(vfield.remark()).build();
    }

    private static com.jr.basic.meta.enums.Type convertToType(Class<?> javaType) {
        if (javaType.isEnum()) {
            return com.jr.basic.meta.enums.Type.ENUM;
        } else {
            String var1 = javaType.getName();
            byte var2 = -1;
            switch(var1.hashCode()) {
            case -2056817302:
                if (var1.equals("java.lang.Integer")) {
                    var2 = 1;
                }
                break;
            case -1935445726:
                if (var1.equals("[Ljava.lang.Integer;")) {
                    var2 = 11;
                }
                break;
            case -1246518012:
                if (var1.equals("java.time.LocalDate")) {
                    var2 = 5;
                }
                break;
            case -1246033885:
                if (var1.equals("java.time.LocalTime")) {
                    var2 = 8;
                }
                break;
            case -1179039247:
                if (var1.equals("java.time.LocalDateTime")) {
                    var2 = 6;
                }
                break;
            case -1062887262:
                if (var1.equals("java.time.Time")) {
                    var2 = 7;
                }
                break;
            case -527879800:
                if (var1.equals("java.lang.Float")) {
                    var2 = 3;
                }
                break;
            case 344809556:
                if (var1.equals("java.lang.Boolean")) {
                    var2 = 9;
                }
                break;
            case 392722245:
                if (var1.equals("[Ljava.lang.String;")) {
                    var2 = 10;
                }
                break;
            case 398795216:
                if (var1.equals("java.lang.Long")) {
                    var2 = 2;
                }
                break;
            case 761287205:
                if (var1.equals("java.lang.Double")) {
                    var2 = 4;
                }
                break;
            case 1195259493:
                if (var1.equals("java.lang.String")) {
                    var2 = 0;
                }
            }

            switch(var2) {
            case 0:
                return com.jr.basic.meta.enums.Type.STRING;
            case 1:
                return com.jr.basic.meta.enums.Type.INT;
            case 2:
                return com.jr.basic.meta.enums.Type.LONG;
            case 3:
                return com.jr.basic.meta.enums.Type.FLOAT;
            case 4:
                return com.jr.basic.meta.enums.Type.DOUBLE;
            case 5:
                return com.jr.basic.meta.enums.Type.DATE;
            case 6:
                return com.jr.basic.meta.enums.Type.DATETIME;
            case 7:
                return com.jr.basic.meta.enums.Type.TIME;
            case 8:
                return com.jr.basic.meta.enums.Type.TIME;
            case 9:
                return com.jr.basic.meta.enums.Type.BOOLEAN;
            case 10:
                return com.jr.basic.meta.enums.Type.STRINGARRAY;
            case 11:
                return com.jr.basic.meta.enums.Type.INTARRAY;
            default:
                return com.jr.basic.meta.enums.Type.STRING;
            }
        }
    }

    private static UIType convertToUiType(Class<?> javaType) {
        if (javaType.isEnum()) {
            return UIType.ENUM;
        } else {
            String var1 = javaType.getName();
            byte var2 = -1;
            switch(var1.hashCode()) {
            case -2056817302:
                if (var1.equals("java.lang.Integer")) {
                    var2 = 1;
                }
                break;
            case -1935445726:
                if (var1.equals("[Ljava.lang.Integer;")) {
                    var2 = 10;
                }
                break;
            case -1246518012:
                if (var1.equals("java.time.LocalDate")) {
                    var2 = 5;
                }
                break;
            case -1246033885:
                if (var1.equals("java.time.LocalTime")) {
                    var2 = 7;
                }
                break;
            case -1179039247:
                if (var1.equals("java.time.LocalDateTime")) {
                    var2 = 6;
                }
                break;
            case -527879800:
                if (var1.equals("java.lang.Float")) {
                    var2 = 3;
                }
                break;
            case 344809556:
                if (var1.equals("java.lang.Boolean")) {
                    var2 = 8;
                }
                break;
            case 392722245:
                if (var1.equals("[Ljava.lang.String;")) {
                    var2 = 9;
                }
                break;
            case 398795216:
                if (var1.equals("java.lang.Long")) {
                    var2 = 2;
                }
                break;
            case 761287205:
                if (var1.equals("java.lang.Double")) {
                    var2 = 4;
                }
                break;
            case 1195259493:
                if (var1.equals("java.lang.String")) {
                    var2 = 0;
                }
            }

            switch(var2) {
            case 0:
                return UIType.TEXT;
            case 1:
                return UIType.NUMBER;
            case 2:
                return UIType.NUMBER;
            case 3:
                return UIType.NUMBER;
            case 4:
                return UIType.NUMBER;
            case 5:
                return UIType.DATE;
            case 6:
                return UIType.DATETIME;
            case 7:
                return UIType.TIME;
            case 8:
                return UIType.BOOL;
            case 9:
                return UIType.SELECT;
            case 10:
                return UIType.SELECT;
            default:
                return UIType.TEXT;
            }
        }
    }
}
