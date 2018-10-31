package com.jr.basic.meta.query;

import lombok.Data;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 16:32
 * @Description: 查询条件
 */
@Data
public class Condition {

    // 查询逻辑
    private ConditionLogic logic;

    // 查询类型
    private ConditionType type;

    // 查询值
    private Object value;

    // 字段名
    private String field;


}
