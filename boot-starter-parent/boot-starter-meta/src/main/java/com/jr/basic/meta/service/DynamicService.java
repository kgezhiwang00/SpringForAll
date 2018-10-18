//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.service;

import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.query.Query;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DynamicService {
    String DYNAMIC_CACHE_NAME = "DYNAMIC_CACHE";

    <T extends Domain> Optional<List<T>> findAll(Class<T> var1);

    <T extends Domain> Optional<List<T>> findAll(Class<T> var1, Query<T> var2);

    <T extends Domain> Optional<List<Map<String, Object>>> findAggregate(Class<T> var1, Query<T> var2);

    <T extends Domain> Optional<List<T>> findAll(Class<T> var1, List<Long> var2);

    <T extends Domain> long count(Class<T> var1, Query<T> var2);

    <T extends Domain> Optional<T> get(Class<T> var1, Long var2);

    <T extends Domain> Optional<T> get(Class<T> var1, Query<T> var2);

    <T extends Domain> boolean exists(Class<T> var1, Long var2);

    <T extends Domain> T create(T var1);

    <T extends Domain> List<T> create(Iterable<T> var1);

    <T extends Domain> T update(T var1);

    <T extends Domain> void update(Iterable<T> var1);

    <T extends Domain> void delete(Class<T> var1, Long var2);

    <T extends Domain> void delete(T var1);

    <T extends Domain> void deleteInBatch(Iterable<T> var1);

    <T extends Domain> void deleteAll(Class<T> var1);

    <T extends Domain> void deleteAll(Class<T> var1, Query<T> var2);

    void flush();

    <T extends Domain> T patch(T var1);
}
