//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.dao;

import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.query.Query;
import java.util.List;
import java.util.Map;

public interface DynamicDao {
    <T extends Domain> List<T> findAll(Class<T> var1);

    <T extends Domain> List<T> findAll(Class<T> var1, Iterable<Long> var2);

    <T extends Domain> List<Map<String, Object>> findAggregate(Class<T> var1, Query<T> var2);

    <T extends Domain> List<T> create(Iterable<T> var1);

    <T extends Domain> List<T> update(Iterable<T> var1);

    void flush();

    <T extends Domain> void deleteAll(Class<T> var1, Query<T> var2);

    <T extends Domain> void deleteInBatch(Iterable<T> var1);

    void deleteAllInBatch(String var1);

    <T extends Domain> T getOne(Class<T> var1, Long var2);

    <T extends Domain> long count(Class<T> var1, Query<T> var2);

    <T extends Domain> void delete(Class<T> var1, Long var2);

    <T extends Domain> void delete(T var1);

    <T extends Domain> void delete(Iterable<T> var1);

    <T extends Domain> boolean exists(Class<T> var1, Long var2);

    <T extends Domain> T create(T var1);

    <T extends Domain> List<T> findAll(Class<T> var1, Query<T> var2);

    <T extends Domain> T getOne(Class<T> var1, Query<T> var2);

    <T extends Domain> T update(T var1);
}
