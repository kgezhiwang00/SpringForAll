//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jr.basic.meta.dao.DynamicDao;
import com.jr.basic.meta.domain.AuditableDomain;
import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.domain.KeyDomain;
import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.enums.OrderType;
import com.jr.basic.meta.event.DomainEvent;
import com.jr.basic.meta.event.DomainEvent.Type;
import com.jr.basic.meta.exception.NoAuthException;
import com.jr.basic.meta.exception.PermissionException;
import com.jr.basic.meta.exception.ServiceException;
import com.jr.basic.meta.query.Condition;
import com.jr.basic.meta.query.Query;
import com.jr.basic.meta.query.predicate.EqualsPredicate;
import com.jr.basic.meta.query.predicate.NotEqualsPredicate;
import com.jr.basic.meta.service.AuthenticateService;
import com.jr.basic.meta.service.DynamicService;
import com.jr.basic.meta.service.MetaService;
import com.jr.basic.meta.validation.Unique;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(
        rollbackFor = {ServiceException.class}
)
public class DynamicServiceImpl implements DynamicService {
    private static final Logger log = LoggerFactory.getLogger(DynamicServiceImpl.class);
    @Autowired
    private DynamicDao dynamicDao;
    @Autowired
    private MetaService metaService;
    @Autowired(
            required = false
    )
    private CacheManager cacheManager;
    @Autowired(
            required = false
    )
    private AuthenticateService authenticateService;
    @Autowired(
            required = false
    )
    private ApplicationEventMulticaster multicaster;

    public DynamicServiceImpl() {
    }

    public <T extends Domain> Optional<List<T>> findAll(Class<T> clazz) {
        MetaDomain metaDomain = this.metaService.getByFullName(clazz.getName());
        if (this.isCache(metaDomain)) {
            Cache cache = this.cacheManager.getCache(metaDomain.getCacheName());
            ValueWrapper valueWrapper = cache.get(metaDomain.getListCacheName());
            if (valueWrapper == null) {
                List<T> datas = ImmutableList.copyOf(this.dynamicDao.findAll(clazz));
                cache.put(metaDomain.getListCacheName(), datas);
                return Optional.ofNullable(datas);
            } else {
                return Optional.of((List)valueWrapper.get());
            }
        } else {
            return Optional.ofNullable(this.dynamicDao.findAll(clazz));
        }
    }



    public <T extends Domain> Optional<List<T>> findAll(Class<T> clazz, Query<T> query) {
        MetaDomain metaDomain = this.metaService.getByFullName(clazz.getName());
        if (query.getOrders().isEmpty() && StringUtils.isNotBlank(metaDomain.getDefaultOrder())) {
            query.addOrder(metaDomain.getDefaultOrder(), metaDomain.getDefaultOrderType());
        }

        this.checkQueryPermission(metaDomain, query);
        if (this.isCache(metaDomain)) {
            Optional<List<T>> op = this.findAll(clazz);
            return op.isPresent() ? Optional.of(this.filterByCondition((List)op.get(), query)) : op;
        } else {
            return Optional.ofNullable(this.dynamicDao.findAll(clazz, query));
        }
    }

    public <T extends Domain> Optional<List<Map<String, Object>>> findAggregate(Class<T> t, Query<T> query) {
        return Optional.ofNullable(this.dynamicDao.findAggregate(t, query));
    }

    public <T extends Domain> long count(Class<T> clazz, Query<T> query) {
        MetaDomain metaDomain = this.metaService.getByFullName(clazz.getName());
        return this.isCache(metaDomain) ? ((Long)this.findAll(clazz, query).map((list) -> {
            return (new Integer(list.size())).longValue();
        }).orElse(0L)).longValue() : this.dynamicDao.count(clazz, query);
    }

    public <T extends Domain> Optional<List<T>> findAll(Class<T> clazz, List<Long> ids) {
        MetaDomain metaDomain = this.metaService.getByFullName(clazz.getName());
        return this.isCache(metaDomain) ? this.findAll(clazz).map((list) -> {
            Stream var10000 = list.stream();
            ids.getClass();
            return (List)var10000.filter(ids::contains).collect(Collectors.toList());
        }) : Optional.ofNullable(this.dynamicDao.findAll(clazz, ids));
    }

    public <T extends Domain> Optional<T> get(Class<T> clazz, Long id) {
        MetaDomain metaDomain = this.metaService.getByFullName(clazz.getName());
        return this.isCache(metaDomain) ? this.findAll(clazz).map((list) -> {
            return (Domain)list.stream().filter((Domain)(o) -> {
                return o.getId().equals(id);
            }).findFirst().orElseGet((Supplier)null);
        }) : Optional.ofNullable(this.dynamicDao.getOne(clazz, id));
    }


    public <T extends Domain> Optional<T> get(Class<T> clazz, Query<T> query) {
        MetaDomain metaDomain = this.metaService.getByFullName(clazz.getName());
        if (this.isCache(metaDomain)) {
            if (log.isDebugEnabled()) {
                log.debug("get {} from  cache", clazz);
            }

            return this.findAll(clazz, query).map((list) -> {
                return list.isEmpty() ? null : list.get(0);
            });
        } else {
            return Optional.ofNullable(this.dynamicDao.getOne(clazz, query));
        }
    }

    public <T extends Domain> boolean exists(Class<T> clazz, Long id) {
        MetaDomain metaDomain = this.metaService.getByFullName(clazz.getName());
        return this.isCache(metaDomain) ? this.findAll(clazz).map((list) -> {
            return list.stream().filter((o) -> {
                return o.getId().equals(id);
            }).findAny();
        }).isPresent() : this.dynamicDao.exists(clazz, id);
    }

    public <T extends Domain> T create(T domain) {
        Assert.notNull(domain, "create domain can not be null");
        MetaDomain metaDomain = this.metaService.getByFullName(domain.getClass().getName());
        this.checkUnique(metaDomain, domain);
        this.checkValue(metaDomain, domain);
        this.audit(domain);
        T _new_ = this.dynamicDao.create(domain);
        if (this.isCache(metaDomain)) {
            Cache cache = this.cacheManager.getCache(metaDomain.getCacheName());
            cache.evict(metaDomain.getListCacheName());
        }

        if (this.multicaster != null) {
            this.multicaster.multicastEvent(new DomainEvent(Type.CREATE, metaDomain, _new_));
        }

        return _new_;
    }

    public <T extends Domain> List<T> create(Iterable<T> domains) {
        Assert.notNull(domains, "create domain can not be null");
        List<T> list = Lists.newArrayList();
        domains.forEach((d) -> {
            list.add(this.create(d));
        });
        if (this.multicaster != null) {
            MetaDomain metaDomain = this.metaService.getByFullName(((Domain)domains.iterator().next()).getClass().getName());
            this.multicaster.multicastEvent(new DomainEvent(Type.BATCHCREATE, metaDomain, list));
        }

        return list;
    }

    public <T extends Domain> T update(T domain) {
        Assert.notNull(domain, "update domain can not be null");
        MetaDomain metaDomain = this.metaService.getByFullName(domain.getClass().getName());
        this.checkUnique(metaDomain, domain);
        this.audit(domain);
        this.dynamicDao.update(domain);
        if (this.isCache(metaDomain)) {
            Cache cache = this.cacheManager.getCache(metaDomain.getCacheName());
            cache.evict(metaDomain.getListCacheName());
        }

        if (this.multicaster != null) {
            this.multicaster.multicastEvent(new DomainEvent(Type.UPDATE, metaDomain, domain));
        }

        return domain;
    }

    public <T extends Domain> T patch(T domain) {
        Assert.notNull(domain, "update domain can not be null");
        MetaDomain metaDomain = this.metaService.getByFullName(domain.getClass().getName());
        this.checkUnique(metaDomain, domain);
        T original = this.dynamicDao.getOne(domain.getClass(), domain.getId());
        BeanUtils.copyProperties(domain, original);
        this.audit(original);
        T updated = this.dynamicDao.update(original);
        if (this.isCache(metaDomain)) {
            Cache cache = this.cacheManager.getCache(metaDomain.getCacheName());
            cache.evict(metaDomain.getListCacheName());
        }

        if (this.multicaster != null) {
            this.multicaster.multicastEvent(new DomainEvent(Type.UPDATE, metaDomain, updated));
        }

        return updated;
    }

    public <T extends Domain> void update(Iterable<T> domains) {
        Iterator var2 = domains.iterator();

        while(var2.hasNext()) {
            T domain = (Domain)var2.next();
            this.update(domain);
        }

        if (this.multicaster != null) {
            MetaDomain metaDomain = this.metaService.getByFullName(((Domain)domains.iterator().next()).getClass().getName());
            this.multicaster.multicastEvent(new DomainEvent(Type.BATCHUPDATE, metaDomain, domains));
        }

    }

    public <T extends Domain> void delete(Class<T> clazz, Long id) {
        this.delete(this.dynamicDao.getOne(clazz, id));
    }

    public <T extends Domain> void deleteInBatch(Iterable<T> domains) {
        this.dynamicDao.deleteInBatch(domains);
    }

    public <T extends Domain> void deleteAll(Class<T> clazz) {
        MetaDomain metaDomain = this.metaService.getByFullName(clazz.getClass().getName());
        if (this.isCache(metaDomain)) {
            Cache cache = this.cacheManager.getCache(metaDomain.getCacheName());
            cache.evict(metaDomain.getListCacheName());
        }

        this.dynamicDao.deleteAllInBatch(clazz.getSimpleName());
    }

    public void flush() {
        this.dynamicDao.flush();
    }

    public <T extends Domain> void delete(T domain) {
        if (domain instanceof KeyDomain) {
            KeyDomain keyDomain = (KeyDomain)domain;
            if (keyDomain.getIsDelete() != null && keyDomain.getIsDelete().booleanValue()) {
                this.dynamicDao.delete(domain);
            } else {
                keyDomain.setIsDelete(Boolean.TRUE);
                this.dynamicDao.update(keyDomain);
            }
        } else {
            this.dynamicDao.delete(domain);
        }

        MetaDomain metaDomain = this.metaService.getByFullName(domain.getClass().getName());
        if (this.isCache(metaDomain)) {
            Cache cache = this.cacheManager.getCache(metaDomain.getCacheName());
            cache.evict(metaDomain.getListCacheName());
        }

        if (this.multicaster != null) {
            this.multicaster.multicastEvent(new DomainEvent(Type.REMOVE, metaDomain, domain));
        }

    }

    public <T extends Domain> void deleteAll(Class<T> clazz, Query<T> query) {
        this.dynamicDao.deleteAll(clazz, query);
        MetaDomain metaDomain = this.metaService.getByFullName(clazz.getClass().getName());
        if (this.isCache(metaDomain)) {
            Cache cache = this.cacheManager.getCache(metaDomain.getCacheName());
            cache.evict(metaDomain.getListCacheName());
        }

    }

    private boolean isCache(MetaDomain meta) {
        return this.cacheManager != null && meta.getIsCache().booleanValue();
    }

    private <T extends Domain> void checkUnique(MetaDomain meta, T domain) {
        Unique unique = (Unique)domain.getClass().getAnnotation(Unique.class);
        if (unique != null) {
            Query<T> query = new Query();
            BeanWrapper wrapper = new BeanWrapperImpl(domain);
            if (domain.getId() != null) {
                query.ne("id", domain.getId());
            }

            if (unique.fields().length > 0) {
                StringBuffer fieldNames = new StringBuffer();
                String[] var7 = unique.fields();
                int var8 = var7.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    String s = var7[var9];
                    query.eq(s, wrapper.getPropertyValue(s));
                    fieldNames.append(meta.getField(s).getLabel() + " ");
                }

                long count = this.count(domain.getClass(), query);
                if (count > 0L) {
                    throw new ServiceException(fieldNames + "  必须唯一", new Object[0]);
                }
            }
        }

    }

    private <T extends Domain> List<T> filterByCondition(List<T> list, Query<T> query) {
        Stream<T> result = list.stream().filter((d) -> {
            if (d == null) {
                return false;
            } else {
                try {
                    BeanWrapper wrapper = new BeanWrapperImpl(d);
                    BeanWrapper compareWrapper = new BeanWrapperImpl(d.getClass().newInstance());
                    Iterator var4 = query.getConds().iterator();

                    while(var4.hasNext()) {
                        Condition cond = (Condition)var4.next();
                        String field = cond.getField();
                        compareWrapper.setPropertyValue(field, cond.getValue());
                        switch(cond.getType()) {
                            case eq:
                                if (!(new EqualsPredicate(compareWrapper.getPropertyValue(field))).test(wrapper.getPropertyValue(field))) {
                                    return false;
                                }
                                break;
                            case ne:
                                if (!(new NotEqualsPredicate(compareWrapper.getPropertyValue(field))).test(wrapper.getPropertyValue(field))) {
                                    return false;
                                }
                        }
                    }

                    return true;
                } catch (Exception var7) {
                    if (log.isErrorEnabled()) {
                        log.error("error when filter domain {} with id {} cause {}", new Object[]{d.getClass(), d.getId(), var7.getMessage()});
                    }

                    return false;
                }
            }
        });
        Comparator comparator;
        if (!query.getOrders().isEmpty()) {
            for(Iterator var4 = query.getOrders().entrySet().iterator(); var4.hasNext(); result = result.sorted(comparator)) {
                Entry<String, OrderType> order = (Entry)var4.next();
                String fieldName = (String)order.getKey();
                comparator = (o1, o2) -> {
                    try {
                        Field field1 = o1.getClass().getDeclaredField(fieldName);
                        Field field2 = o2.getClass().getDeclaredField(fieldName);
                        field1.setAccessible(true);
                        field2.setAccessible(true);
                        Object value1 = field1.get(o1);
                        Object value2 = field2.get(o2);
                        if (value1 == null && value2 == null) {
                            return 0;
                        } else {
                            return value1 == null && value2 != null ? -1 : ((Comparable)value1).compareTo(value2);
                        }
                    } catch (IllegalAccessException | NoSuchFieldException var7) {
                        log.error(var7.getLocalizedMessage());
                        return 0;
                    }
                };
                if (OrderType.desc.equals(order.getValue())) {
                    comparator = comparator.reversed();
                }
            }
        }

        return (List)result.collect(Collectors.toList());
    }

    private void checkQueryPermission(MetaDomain meta, Query query) {
        if (this.authenticateService != null) {
            if (StringUtils.isNotBlank(meta.getOwner())) {
                if (this.authenticateService.isAnonymous().booleanValue()) {
                    throw new NoAuthException("no user", new Object[0]);
                }

                query.eq(meta.getOwner(), this.authenticateService.getCurrentUserId());
            }

            if (meta.getListRole().length > 0 && !this.authenticateService.currentUserHasRole(meta.getListRole()).booleanValue()) {
                throw new PermissionException("no permission", new Object[0]);
            }
        }

    }

    private void checkCreatePermission(MetaDomain meta) {
        if (meta.getCreateRole().length > 0) {
            if (this.authenticateService.isAnonymous().booleanValue()) {
                throw new NoAuthException("no user", new Object[0]);
            }

            if (!this.authenticateService.currentUserHasRole(meta.getCreateRole()).booleanValue()) {
                throw new PermissionException("no permission", new Object[0]);
            }
        }

    }

    private void checkUpdatePermission(MetaDomain meta) {
        if (meta.getUpdateRole().length > 0) {
            if (this.authenticateService.isAnonymous().booleanValue()) {
                throw new NoAuthException("no user", new Object[0]);
            }

            if (!this.authenticateService.currentUserHasRole(meta.getUpdateRole()).booleanValue()) {
                throw new PermissionException("no permission", new Object[0]);
            }
        }

    }

    private void checkDeletePermission(MetaDomain meta) {
        if (meta.getDeleteRole().length > 0) {
            if (this.authenticateService.isAnonymous().booleanValue()) {
                throw new NoAuthException("no user", new Object[0]);
            }

            if (!this.authenticateService.currentUserHasRole(meta.getDeleteRole()).booleanValue()) {
                throw new PermissionException("no permission", new Object[0]);
            }
        }

    }

    private void audit(Domain domain) {
        if (domain instanceof AuditableDomain) {
            AuditableDomain auditable = (AuditableDomain)domain;
            if (this.authenticateService != null) {
                auditable.setCreatedBy(this.authenticateService.getCurrentUserName());
            }

            if (domain.getId() != null && this.authenticateService != null) {
                auditable.setLastUpdatedBy(this.authenticateService.getCurrentUserName());
            }
        }

    }

    private void checkValue(MetaDomain meta, Domain domain) {
        if (StringUtils.isNotBlank(meta.getOwner())) {
            if (this.authenticateService.getCurrentUser() == null) {
                throw new PermissionException("error.security.noAuth", new Object[0]);
            }

            Long userId = this.authenticateService.getCurrentUserId();
            (new BeanWrapperImpl(domain)).setPropertyValue(meta.getOwner(), userId);
        }

    }
}
