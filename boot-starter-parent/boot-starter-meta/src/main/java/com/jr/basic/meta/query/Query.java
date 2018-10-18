//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.query;

import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.enums.OrderType;
import com.sun.org.apache.bcel.internal.generic.Select;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Query<T extends Domain> {
    public static final String CONDITION_SPLIT = ":";
    private Class<T> clazz;
    private List<Select> selections = new ArrayList();
    private List<Condition> conds = new ArrayList();
    private Page<T> page = new Page();
    private Map<String, OrderType> orders = new LinkedHashMap(3);
    private String entityGraph;
    private Set<String> groups = new TreeSet();
    private boolean nullable = false;

    public Query() {
    }

    public Query(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Query<T> addCondition(Condition condition) {
        if (this.nullable || condition.getValue() != null) {
            this.conds.add(condition);
        }

        return this;
    }

    public Query<T> addCondition(Condition... conditions) {
        Condition[] var2 = conditions;
        int var3 = conditions.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Condition cond = var2[var4];
            this.addCondition(cond);
        }

        return this;
    }

    public boolean isOrder() {
        return this.orders.size() > 0;
    }

    public Query<T> addOrder(String field, OrderType order) {
        this.orders.put(field, order);
        return this;
    }

    public Query<T> addOrder(String value) {
        String type = value.substring(0, 1);
        String field = value.substring(1);
        this.addOrder(field, type.equals("+") ? OrderType.asc : OrderType.desc);
        return this;
    }

    public Query<T> eq(String field, Object value) {
        this.addCondition(Condition.eq(field, value));
        return this;
    }

    public Query<T> ne(String field, Object value) {
        this.addCondition(Condition.ne(field, value));
        return this;
    }

    public Query<T> ge(String field, Object value) {
        this.addCondition(Condition.ge(field, value));
        return this;
    }

    public Query<T> gth(String field, Object value) {
        this.addCondition(Condition.gth(field, value));
        return this;
    }

    public Query<T> geth(String field, Object value) {
        this.addCondition(Condition.geth(field, value));
        return this;
    }

    public Query<T> lth(String field, Object value) {
        this.addCondition(Condition.lth(field, value));
        return this;
    }

    public Query<T> leth(String field, Object value) {
        this.addCondition(Condition.leth(field, value));
        return this;
    }

    public Query<T> gt(String field, Object value) {
        this.addCondition(Condition.gt(field, value));
        return this;
    }

    public Query<T> le(String field, Object value) {
        this.addCondition(Condition.le(field, value));
        return this;
    }

    public Query<T> lt(String field, Object value) {
        this.addCondition(Condition.lt(field, value));
        return this;
    }

    public Query<T> like(String field, Object value) {
        this.addCondition(Condition.like(field, value));
        return this;
    }

    public Query<T> llike(String field, Object value) {
        this.addCondition(Condition.llike(field, value));
        return this;
    }

    public Query<T> rlike(String field, Object value) {
        this.addCondition(Condition.rlike(field, value));
        return this;
    }

    public Query<T> in(String field, Object value) {
        this.addCondition(Condition.in(field, value));
        return this;
    }

    public Query<T> noIn(String field, Object... value) {
        this.addCondition(Condition.noIn(field, value));
        return this;
    }

    public Query<T> isNull(String field) {
        this.addCondition(Condition.isNull(field));
        return this;
    }

    public Query<T> isNotNull(String field) {
        this.addCondition(Condition.isNotNull(field));
        return this;
    }

    public Query<T> group(Condition... conditions) {
        this.addCondition(new Condition(conditions));
        return this;
    }

    public Query<T> group(String field) {
        this.groups.add(field);
        return this;
    }

    public Query<T> entityGraph(String entityGraph) {
        this.entityGraph = entityGraph;
        return this;
    }

    public String getEntityGraph() {
        return this.entityGraph;
    }

    public Class<T> getClazz() {
        return this.clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<Condition> getConds() {
        return this.conds;
    }

    public void setConds(List<Condition> conds) {
        this.conds = conds;
    }

    public Page<T> getPage() {
        return this.page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public Map<String, OrderType> getOrders() {
        return this.orders;
    }

    public void setOrders(Map<String, OrderType> orders) {
        this.orders = orders;
    }

    public boolean hasCondition() {
        return this.conds.size() > 0;
    }

    public List<Select> getSelections() {
        return this.selections;
    }

    public Query<T> select(Select select) {
        this.selections.add(select);
        return this;
    }

    public void setSelections(List<Select> selections) {
        this.selections = selections;
    }

    public Set<String> getGroups() {
        return this.groups;
    }
}
