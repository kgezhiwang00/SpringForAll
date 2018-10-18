//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service;

import com.jr.basic.api.vo.SysApiDefine;
import graphql.ExecutionResult;
import java.util.List;

public interface SysApiManagerService {
    List<SysApiDefine> findAll();

    SysApiDefine getByName(String var1);

    ExecutionResult execute(String var1);
}
