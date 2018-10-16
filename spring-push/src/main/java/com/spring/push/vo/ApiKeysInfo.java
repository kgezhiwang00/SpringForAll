package com.spring.push.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class ApiKeysInfo {

    private Integer id;


    private String apiKey;


    private String testKey;


    private String keyInfo;


    private String status;

    private String userid;


    private String pushplatform;


    private String remark;


    private String secretkey;


    private String devpassword;


    private String productpassword;


    private String thirdapikey;


    private String packagename;

    // 推送类型
    private String pushtype;

    // 应用类型
    private String apptype;

    private Date createTime;



}