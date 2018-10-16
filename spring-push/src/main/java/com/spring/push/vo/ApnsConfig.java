//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.spring.push.vo;

import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
@Data
public class ApnsConfig {
    private String name;
    private InputStream keyStore;
    private String password;
    private boolean isDevEnv = false;
    private int poolSize = 3;
    private int cacheLength = 100;
    private int retries = 3;
    private int intervalTime = 1800000;
    private int timeout = 10000;


}
