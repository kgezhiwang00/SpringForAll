package com.jr.basic.meta.service.impl;

import com.jr.basic.meta.dao.impl.DynamicDao;
import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.domain.KeyDomain;
import com.jr.basic.meta.query.Condition;
import com.jr.basic.meta.query.ConditionLogic;
import com.jr.basic.meta.query.ConditionType;
import com.jr.basic.meta.query.Query;
import com.jr.basic.meta.service.DynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 15:06
 * @Description:
 */
@Slf4j
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    private DynamicDao dynamicDao;
    @Override
    public <T extends Domain> T create(T domain) {
        log.info("start create {},info:{}",domain.getClass(),domain.toString());
        T newDomain = dynamicDao.create(domain);
        return newDomain;
    }

    @Override
    public <T extends Domain> Optional<List<T>> findAll(Class<T> clazz) {
        List<T> list = dynamicDao.findAll(clazz);
        return Optional.of(list);
    }

    @Override
    public <T extends Domain> Optional<List<T>> findAll(Class<T> clazz, Query<T> query) {
        List<T> list = dynamicDao.findAll(query,clazz);
        return Optional.of(list);
    }

    @Override
    public <T extends Domain> Optional<T> get(Class<T> clazz, Long id) {
        Query<T> query = new Query<>();
        List<Condition> conditionList = new ArrayList<>();
        Condition condition = new Condition();
        condition.setField("id");
        condition.setLogic(ConditionLogic.and);
        condition.setValue(id);
        condition.setType(ConditionType.eq);
        conditionList.add(condition);
        query.setConditionList(conditionList);
        List<T> list = findAll(clazz,query).get();
        return Optional.of(list.get(0));

    }

    @Override
    public <T extends Domain> void delete(Class<T> clazz, Long id) {
        T t = get(clazz,id).get();
        delete(t);

    }

    @Override
    public <T extends Domain> void delete(T domain) {
        if(domain instanceof KeyDomain){
            if (((KeyDomain) domain).getIsDelete()){
                ((KeyDomain) domain).setIsDelete(true);
                update(domain);
            }else {
                dynamicDao.delete(domain);
            }
        }

    }

    @Override
    public <T extends Domain> T update(T domain) {
        dynamicDao.update(domain);
        return domain;
    }
}
