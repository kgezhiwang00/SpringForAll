//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.query;

import org.springframework.util.Assert;

public class Condition {
    private String field;
    private Condition.Type type;
    private Condition.Logic logic;
    private Object value;
    private boolean group;

    public Condition(String field, Object value) {
        this(field, Condition.Type.eq, value, Condition.Logic.and);
    }

    public Condition(String field, Condition.Type type, Object value) {
        this(field, type, value, Condition.Logic.and);
    }

    public Condition(String field, Condition.Type type, Object value, Condition.Logic logic) {
        this.type = Condition.Type.eq;
        this.logic = Condition.Logic.and;
        this.field = field;
        this.type = type;
        this.value = value;
        this.logic = logic;
    }

    public Condition(Condition... conditions) {
        this.type = Condition.Type.eq;
        this.logic = Condition.Logic.and;
        Assert.isTrue(conditions.length > 1, "the condition group must latest two");
        this.logic = Condition.Logic.and;
        this.group = true;
        this.value = conditions;
    }

    public Condition(Condition.Logic logic, Condition... conditions) {
        this.type = Condition.Type.eq;
        this.logic = Condition.Logic.and;
        Assert.isTrue(conditions.length > 1, "the condition group must latest two");
        this.logic = logic;
        this.value = conditions;
    }

    public static Condition eq(String field, Object value) {
        return new Condition(field, Condition.Type.eq, value);
    }

    public static Condition ne(String field, Object value) {
        return new Condition(field, Condition.Type.ne, value);
    }

    public static Condition ge(String field, Object value) {
        return new Condition(field, Condition.Type.ge, value);
    }

    public static Condition geth(String field, Object value) {
        return new Condition(field, Condition.Type.geth, value);
    }

    public static Condition gt(String field, Object value) {
        return new Condition(field, Condition.Type.gt, value);
    }

    public static Condition gth(String field, Object value) {
        return new Condition(field, Condition.Type.gth, value);
    }

    public static Condition le(String field, Object value) {
        return new Condition(field, Condition.Type.le, value);
    }

    public static Condition leth(String field, Object value) {
        return new Condition(field, Condition.Type.leth, value);
    }

    public static Condition lt(String field, Object value) {
        return new Condition(field, Condition.Type.lt, value);
    }

    public static Condition lth(String field, Object value) {
        return new Condition(field, Condition.Type.lth, value);
    }

    public static Condition like(String field, Object value) {
        return new Condition(field, Condition.Type.like, value);
    }

    public static Condition llike(String field, Object value) {
        return new Condition(field, Condition.Type.llike, value);
    }

    public static Condition rlike(String field, Object value) {
        return new Condition(field, Condition.Type.rlike, value);
    }

    public static Condition in(String field, Object value) {
        return new Condition(field, Condition.Type.in, value);
    }

    public static Condition noIn(String field, Object value) {
        return new Condition(field, Condition.Type.notIn, value);
    }

    public static Condition isNull(String field) {
        return new Condition(field, Condition.Type.isNull, (Object)null);
    }

    public static Condition isNotNull(String field) {
        return new Condition(field, Condition.Type.notNull, (Object)null);
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Condition.Type getType() {
        return this.type;
    }

    public void setType(Condition.Type type) {
        this.type = type;
    }

    public Condition.Logic getLogic() {
        return this.logic;
    }

    public void setLogic(Condition.Logic logic) {
        this.logic = logic;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isGroup() {
        return this.group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    public static enum Logic {
        and,
        or;

        private Logic() {
        }
    }

    public static enum Type {
        eq,
        ne,
        ge,
        le,
        gth,
        geth,
        lth,
        leth,
        gt,
        lt,
        in,
        notIn,
        like,
        llike,
        rlike,
        isNull,
        notNull,
        isBlank,
        notBlank,
        exists;

        private Type() {
        }
    }
}
