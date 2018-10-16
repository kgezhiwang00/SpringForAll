package com.spring.push.vo;

import com.spring.push.rest.CommonResult;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zcx
 * @Date: 2018/10/16 09:22
 * @Description:
 */
@Data
public class NotificationResponse extends CommonResult {

    private Map resultMap = new HashMap();
}
