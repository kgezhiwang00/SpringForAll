package com.jr.basic.meta.dao.impl;

import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.query.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 16:15
 * @Description:
 */
public class DynamicDaoImpl implements DynamicDao {

    @Autowired
    private EntityManager em;

    @Autowired(required = false)
    private ConversionService conversionService;

    @Override
    public <T extends Domain> List<T> findAll(Class<T> clazz) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.select(root);
        // 封装查询条件
        criteria.where(builder.and(generateCondition(new Query<>(),builder,root)));

        return findAll(new Query<>(),clazz);
    }

    @Override
    public <T extends Domain> List<T> findAll(Query<T> query, Class<T> clazz) {

        if (query.getClazz() == null){
            query.setClazz(clazz);
        }
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(query.getClazz());
        String entityGraph = query.getEntityGraph();
        criteria.select(root);
        // 封装查询条件
        criteria.where(builder.and(generateCondition(query,builder,root)));
        if(query.isOrder()){
            Order[] orders = new Order[query.getOrderTypeMap().size()];
            Iterator<Map.Entry<String,OrderType>> it = query.getOrderTypeMap().entrySet().iterator();
            int i = 0;
            while (it.hasNext()){
                Map.Entry<String,OrderType> entry = it.next();
                orders[i++] = OrderType.desc.equals(entry.getValue())?builder.desc(root.get(entry.getKey())):builder.asc(root.get(entry.getKey()));

            }
            criteria.orderBy(orders);
        }

        TypedQuery<T> typedQuery = em.createQuery(criteria);
        if(StringUtils.isNotBlank(entityGraph)){
            typedQuery.setHint("javax.persistence.fetchgraph",em.getEntityGraph(entityGraph));
        }

        Page<T> page = query.getPage();
        if (page.isPageable()){
            typedQuery.setMaxResults(page.getMax());
            typedQuery.setFirstResult(page.getOffset());
        }

        return typedQuery.getResultList();
    }

    @Override
    public <T extends Domain> T create(T domain) {
        em.persist(domain);
        return domain;
    }

    @Override
    public <T extends Domain> void delete(T domain) {
        em.remove(em.merge(domain));

    }

    @Override
    public <T extends Domain> T update(T domain) {
        return em.merge(domain);
    }

    /**
     * 封装查询条件
     * @param builder
     * @param root
     * @param <T>
     * @return
     */
    protected <T extends Domain>Predicate[] generateCondition(Query<T> query,CriteriaBuilder builder, Root<T> root){

        Predicate[] predicates = new Predicate[query.getConditionList().size()];
        int i = 0;
        // 循环所有的condition
        for(Condition condition:query.getConditionList()){
            predicates[i++] = resolverCondition(root,builder,condition);
        }
        return predicates;


    }

    /**
     * 根据查询条件组装成predicate
     * @param root
     * @param builder
     * @param condition
     * @param <T>
     * @return
     */
    private <T extends Domain> Predicate resolverCondition(Root<T> root, CriteriaBuilder builder, Condition condition) throws DataException {

        String field = condition.getField();
        Object value = condition.getValue();
        ConditionType type = condition.getType();

        //TODO 这个是什么意思？
        Expression<?> expression = root.get(field);

        Predicate predicate = null;

        // 转换value类型
        if(value != null){
            value = conversionService.convert(value,expression.getJavaType());
        }

        // 根据不同的type类型，创建不同的predicate

        switch (type){
            case eq:
                predicate = value == null?builder.isNull(expression):builder.equal(expression,value);
                break;
            case ne:
                predicate = value == null?builder.isNotNull(expression):builder.notEqual(expression, value);
                break;
            case like:
                break;
            case exists:
                break;


        }
        switch (condition.getLogic()){
            case or:
                return builder.or(new Predicate[]{predicate});
            default:
                return builder.and(new Predicate[]{predicate});
        }


    }
}
































