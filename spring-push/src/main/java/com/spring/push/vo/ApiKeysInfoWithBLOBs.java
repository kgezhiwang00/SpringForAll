package com.spring.push.vo;

import lombok.Data;

@Data
public class ApiKeysInfoWithBLOBs extends ApiKeysInfo {

    private byte[] devcertificate;

    private byte[] productcertificate;


}