//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.dao.impl;

import com.google.common.base.Preconditions;
import com.jr.basic.meta.dao.DynamicDao;
import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.enums.OrderType;
import com.jr.basic.meta.exception.DataException;
import com.jr.basic.meta.query.Condition;
import com.jr.basic.meta.query.Page;
import com.jr.basic.meta.query.Query;
import com.jr.basic.meta.query.Select;
import com.jr.basic.meta.query.Condition.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

public class DynamicDaoImpl implements DynamicDao {
    private static final String DELETE_ALL_IN_BATCH = "delete from %s";
    private static final String FETCH_GRAPH = "javax.persistence.fetchgraph";
    @PersistenceContext
    private EntityManager em;
    @Autowired(
        required = false
    )
    private ConversionService conversionService;

    public DynamicDaoImpl() {
    }

    public <T extends Domain> List<T> findAll(Class<T> clazz) {
        return this.findAll(clazz, new Query());
    }

    public <T extends Domain> List<T> findAll(Class<T> clazz, Iterable<Long> ids) {
        Query<T> query = new Query();
        query.addCondition(Condition.in("id", ids));
        return this.findAll(clazz, query);
    }

    public <T extends Domain> T create(T domain) {
        this.em.persist(domain);
        return domain;
    }

    public <T extends Domain> List<T> create(Iterable<T> entities) {
        List<T> results = new ArrayList();
        Iterator iter = entities.iterator();

        while(iter.hasNext()) {
            T domain = (Domain)iter.next();
            this.em.persist(domain);
            results.add(domain);
        }

        return results;
    }

    public <T extends Domain> T update(T domain) {
        return (Domain)this.em.merge(domain);
    }

    public <T extends Domain> List<T> update(Iterable<T> entities) {
        List<T> results = new ArrayList();
        Iterator iter = entities.iterator();

        while(iter.hasNext()) {
            T domain = (Domain)iter.next();
            results.add(this.em.merge(domain));
        }

        return results;
    }

    public void flush() {
        this.em.flush();
    }

    public <T extends Domain> void deleteInBatch(Iterable<T> entities) {
        this.delete(entities);
    }

    public void deleteAllInBatch(String domainName) {
        this.em.createNativeQuery(String.format("delete from %s", domainName)).executeUpdate();
    }

    public <T extends Domain> T getOne(Class<T> clazz, Long id) {
        return (Domain)this.em.find(clazz, id);
    }

    public <T extends Domain> T getOne(Class<T> clazz, Query<T> query) {
        Page<T> page = new Page();
        page.setCurPage(Integer.valueOf(1)).setPageable(true).setMax(Integer.valueOf(10));
        query.setPage(page);
        List<T> domains = this.findAll(clazz, query);
        return domains != null && !domains.isEmpty() ? (Domain)domains.get(0) : null;
    }

    public <T extends Domain> void delete(Class<T> clazz, Long id) {
        this.em.remove(this.getOne(clazz, id));
    }

    public <T extends Domain> void delete(T domain) {
        this.em.remove(this.em.merge(domain));
    }

    public <T extends Domain> void delete(Iterable<T> domains) {
        Iterator var2 = domains.iterator();

        while(var2.hasNext()) {
            T d = (Domain)var2.next();
            this.delete(d);
        }

    }

    public <T extends Domain> boolean exists(Class<T> clazz, Long id) {
        return this.getOne(clazz, id) != null;
    }

    public <T extends Domain> long count(Class<T> clazz, Query<T> query) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<T> root = criteria.from(clazz);
        criteria.select(builder.count(root));
        criteria.where(builder.and(this.generateCondition(query, builder, root)));
        Long counts = (Long)this.em.createQuery(criteria).getSingleResult();
        return counts.longValue();
    }

    public <T extends Domain> List<Map<String, Object>> findAggregate(Class<T> clazz, Query<T> query) {
        Preconditions.checkArgument(query.getSelections().size() > 0, "findAggregate need lastest one selections ");
        if (query.getClazz() == null) {
            query.setClazz(clazz);
        }

        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
        Root<T> root = criteria.from(query.getClazz());
        String entityGraph = query.getEntityGraph();
        List<Selection<?>> expressions = new ArrayList();
        Iterator var8 = query.getSelections().iterator();

        while(var8.hasNext()) {
            Select selection = (Select)var8.next();
            String name = selection.getName();
            String alias = selection.alias();
            switch(selection.getAggregate()) {
            case min:
                expressions.add(builder.min(root.get(name)).alias(alias));
                break;
            case max:
                expressions.add(builder.max(root.get(name)).alias(alias));
                break;
            case sum:
                expressions.add(builder.sum(root.get(name)).alias(alias));
                break;
            case count:
                expressions.add(builder.count(root.get(name)).alias(alias));
                break;
            case avg:
                expressions.add(builder.avg(root.get(name)).alias(alias));
            }
        }

        var8 = query.getGroups().iterator();

        while(var8.hasNext()) {
            String group = (String)var8.next();
            expressions.add(root.get(group).alias(group));
        }

        Expression<?>[] expArray = new Expression[expressions.size()];
        criteria.multiselect((Selection[])expressions.toArray(expArray));
        criteria.where(builder.and(this.generateCondition(query, builder, root)));
        if (!query.getGroups().isEmpty()) {
            criteria.groupBy((List)query.getGroups().stream().map((g) -> {
                return root.get(g);
            }).collect(Collectors.toList()));
        }

        TypedQuery<Tuple> typedQuery = this.em.createQuery(criteria);
        Page<T> page = query.getPage();
        if (page.isPageable()) {
            typedQuery.setMaxResults(page.getMax().intValue());
            typedQuery.setFirstResult(page.getOffset());
        }

        List<Tuple> list = typedQuery.getResultList();
        return (List)list.stream().map((tuple) -> {
            Map<String, Object> data = new HashMap();
            tuple.getElements().forEach((e) -> {
                data.put(e.getAlias(), tuple.get(e));
            });
            return data;
        }).collect(Collectors.toList());
    }

    public <T extends Domain> List<T> findAll(Class<T> clazz, Query<T> query) {
        if (query.getClazz() == null) {
            query.setClazz(clazz);
        }

        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(query.getClazz());
        String entityGraph = query.getEntityGraph();
        criteria.select(root);
        criteria.where(builder.and(this.generateCondition(query, builder, root)));
        if (query.isOrder()) {
            Order[] orders = new Order[query.getOrders().size()];
            int i = 0;

            String field;
            OrderType order;
            for(Iterator var9 = query.getOrders().entrySet().iterator(); var9.hasNext(); orders[i++] = OrderType.desc.equals(order) ? builder.desc(root.get(field)) : builder.asc(root.get(field))) {
                Entry<String, OrderType> entry = (Entry)var9.next();
                field = (String)entry.getKey();
                order = (OrderType)entry.getValue();
            }

            criteria.orderBy(orders);
        }

        TypedQuery<T> typedQuery = this.em.createQuery(criteria);
        if (StringUtils.isNotBlank(entityGraph)) {
            typedQuery.setHint("javax.persistence.fetchgraph", this.em.getEntityGraph(entityGraph));
        }

        Page<T> page = query.getPage();
        if (page.isPageable()) {
            typedQuery.setMaxResults(page.getMax().intValue());
            typedQuery.setFirstResult(page.getOffset());
        }

        return typedQuery.getResultList();
    }

    public <T extends Domain> void deleteAll(Class<T> clazz, Query<T> query) {
        if (query.getClazz() == null) {
            query.setClazz(clazz);
        }

        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaDelete<T> criteria = builder.createCriteriaDelete(clazz);
        Root<T> root = criteria.from(query.getClazz());
        criteria.where(this.generateCondition(query, builder, root));
        this.em.createQuery(criteria).executeUpdate();
    }

    protected <T extends Domain> Predicate[] generateCondition(Query<T> query, CriteriaBuilder builder, Root<T> root) {
        Predicate[] predicates = new Predicate[query.getConds().size()];
        int i = 0;
        Iterator var6 = query.getConds().iterator();

        while(var6.hasNext()) {
            Condition condition = (Condition)var6.next();

            try {
                predicates[i++] = this.resolverCondition(root, builder, condition);
            } catch (DataException var9) {
                throw var9;
            }
        }

        return predicates;
    }

    protected <T extends Domain> Predicate resolverCondition(Root<T> root, CriteriaBuilder builder, Condition condition) {
        String field = condition.getField();
        Object value = condition.getValue();
        Type type = condition.getType();
        Expression<?> exp = root.get(field);
        Predicate predicate = null;
        if (value != null && !exp.getJavaType().isAssignableFrom(value.getClass()) && !type.equals(Type.in) && !type.equals(Type.notIn) && this.conversionService != null && this.conversionService.canConvert(exp.getJavaType(), value.getClass())) {
            value = this.conversionService.convert(value, exp.getJavaType());
        }

        LocalDate localDate;
        LocalDateTime localDate;
        switch(type) {
        case eq:
            if (value == null) {
                predicate = builder.isNull(exp);
            } else {
                predicate = builder.equal(exp, value);
            }
            break;
        case ne:
            if (value == null) {
                predicate = builder.isNotNull(exp);
            } else {
                predicate = builder.notEqual(exp, value);
            }
            break;
        case notNull:
            predicate = builder.isNotNull(exp);
            break;
        case isNull:
            predicate = builder.isNull(exp);
            break;
        case ge:
            predicate = builder.ge(exp, (Number)value);
            break;
        case le:
            predicate = builder.le(exp, (Number)value);
            break;
        case gt:
            predicate = builder.gt(exp, (Number)value);
            break;
        case lt:
            predicate = builder.lt(exp, (Number)value);
            break;
        case like:
            predicate = builder.like(exp, "%" + value + "%");
            break;
        case llike:
            predicate = builder.like(exp, value + "%");
            break;
        case rlike:
            predicate = builder.like(exp, "%" + value);
            break;
        case gth:
            if (value instanceof LocalDate) {
                localDate = (LocalDate)value;
                predicate = builder.greaterThan(exp, Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            } else if (value instanceof LocalDateTime) {
                localDate = (LocalDateTime)value;
                predicate = builder.greaterThan(exp, Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()));
            } else {
                predicate = builder.greaterThan(exp, (Date)value);
            }
            break;
        case geth:
            if (value instanceof LocalDate) {
                localDate = (LocalDate)value;
                predicate = builder.greaterThanOrEqualTo(exp, Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            } else if (value instanceof LocalDateTime) {
                localDate = (LocalDateTime)value;
                predicate = builder.greaterThanOrEqualTo(exp, Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()));
            } else {
                predicate = builder.greaterThanOrEqualTo(exp, (Date)value);
            }
            break;
        case lth:
            if (value instanceof LocalDate) {
                localDate = (LocalDate)value;
                predicate = builder.lessThan(exp, Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            } else if (value instanceof LocalDateTime) {
                localDate = (LocalDateTime)value;
                predicate = builder.lessThan(exp, Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()));
            } else {
                predicate = builder.lessThan(exp, (Date)value);
            }
            break;
        case leth:
            if (value instanceof LocalDate) {
                localDate = (LocalDate)value;
                predicate = builder.lessThanOrEqualTo(exp, Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            } else if (value instanceof LocalDateTime) {
                localDate = (LocalDateTime)value;
                predicate = builder.lessThanOrEqualTo(exp, Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()));
            } else {
                predicate = builder.lessThanOrEqualTo(exp, (Date)value);
            }
            break;
        case in:
            predicate = builder.in(exp).value((List)value);
            break;
        case notIn:
            predicate = builder.not(builder.in(exp).value((List)value));
            break;
        default:
            throw new DataException("can not resolve field condition for " + condition.getField());
        }

        switch(condition.getLogic()) {
        case or:
            return builder.or(new Predicate[]{(Predicate)predicate});
        default:
            return builder.and(new Predicate[]{(Predicate)predicate});
        }
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected EntityManager getEm() {
        return this.em;
    }
}
