package com.spring.conf.vo;

import lombok.Data;

/**
 * @Auther: zcx
 * @Date: 2018/10/29 09:03
 * @Description:
 */
@Data
public class ServerInfoRequest {

    private String id;

    private String name;

    private String addDev;

    private String addPro;

    private String git;

    private String svn;

    private String description;
}
