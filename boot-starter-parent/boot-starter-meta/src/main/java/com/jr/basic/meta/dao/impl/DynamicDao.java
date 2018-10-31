package com.jr.basic.meta.dao.impl;

import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.query.Query;

import java.util.List;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 15:12
 * @Description:
 */
public interface DynamicDao {

    <T extends Domain>List<T> findAll(Class<T> clazz);

    <T extends Domain>List<T> findAll(Query<T> query ,Class<T> clazz);

    <T extends Domain> T create(T domain);

    <T extends Domain> void delete(T domain);

    <T extends Domain> T update(T domain);
}
