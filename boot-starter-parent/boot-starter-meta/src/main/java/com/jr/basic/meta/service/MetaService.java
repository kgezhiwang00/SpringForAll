//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.service;

import com.jr.basic.meta.domain.MetaDomain;
import java.util.List;

public interface MetaService {
    String NAME = "metaService";

    List<MetaDomain> findAll();

    MetaDomain getByName(String var1);

    MetaDomain getByFullName(String var1);
}
