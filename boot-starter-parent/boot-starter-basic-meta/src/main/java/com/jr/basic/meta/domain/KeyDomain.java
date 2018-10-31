package com.jr.basic.meta.domain;

import lombok.Data;

/**
 * @Auther: zcx
 * @Date: 2018/10/24 09:32
 * @Description: 如果是逻辑删除则需要继承此类，否则直接删除
 */
@Data
public class KeyDomain extends Domain {

    private Boolean isDelete;
}
