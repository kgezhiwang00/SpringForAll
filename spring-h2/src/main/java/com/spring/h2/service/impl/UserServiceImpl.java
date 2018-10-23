package com.spring.h2.service.impl;

import com.jr.basic.meta.query.Condition;
import com.jr.basic.meta.query.ConditionLogic;
import com.jr.basic.meta.query.ConditionType;
import com.jr.basic.meta.query.Query;
import com.jr.basic.meta.service.impl.DynamicService;
import com.spring.h2.domain.User;
import com.spring.h2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 14:48
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private DynamicService dynamicService;

    @Override
    public void createUser(User user) {

    }

    @Override
    public User getUserById(String id) {
        Query query = new Query();

        List<Condition> conditionList = new ArrayList<>();
        Condition condition = new Condition();
        condition.setType(ConditionType.eq);
        condition.setField("id");
        condition.setLogic(ConditionLogic.and);
        condition.setValue(id);
        conditionList.add(condition);
        query.setConditionList(conditionList);
        Optional<List<User>> userList = dynamicService.findAll(User.class,query);
        List<User> list = userList.get();
        return list.get(0);

    }
}
