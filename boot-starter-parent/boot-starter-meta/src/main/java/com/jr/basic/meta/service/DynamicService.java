package com.jr.basic.meta.service;

import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 14:54
 * @Description: 动态service接口
 */
public interface DynamicService {

    /**
     * 创建实体
     * @param t
     * @param <T>
     * @return
     */
    <T extends Domain> T create(T t);

    /**
     * 查询所有数据
     * @param clazz
     * @param <T>
     * @return
     */
    <T extends Domain> Optional<List<T>> findAll(Class<T> clazz);


    /**
     * 根据查询条件获取所有数据
     * @param clazz
     * @param query
     * @param <T>
     * @return
     */
    <T extends Domain> Optional<List<T>> findAll(Class<T> clazz, Query<T> query);

    /**
     * 根据主键获取返回值
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    <T extends Domain> Optional<T> get(Class<T> clazz,Long id);

    /**
     * 根据主键删除说
     * @param clazz
     * @param id
     * @param <T>
     */
    <T extends Domain> void delete(Class<T> clazz,Long id);

    /**
     * 删除domain
     * @param <T>
     */
    <T extends Domain> void delete(T domain);

    /**
     * 更新domain
     * @param domain
     * @param <T>
     * @return
     */
    <T extends Domain> T update(T domain);



}
