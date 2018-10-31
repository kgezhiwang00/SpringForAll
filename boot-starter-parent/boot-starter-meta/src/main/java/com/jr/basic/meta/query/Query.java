package com.jr.basic.meta.query;

import com.jr.basic.meta.domain.Domain;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 16:28
 * @Description: 封装查询条件
 */
@Data
public class Query<T extends Domain>  {

    // 条件list
    private List<Condition> conditionList;

    // 排序字段以及类型
    private Map<String,OrderType> orderTypeMap = new LinkedHashMap<>(3);

    private Class<T> clazz;

    private String entityGraph;

    private Page<T> page = new Page<T>() {
    };

    // 判断是否需要排序
    public Boolean isOrder(){
        return orderTypeMap.size()>0;
    }


}
