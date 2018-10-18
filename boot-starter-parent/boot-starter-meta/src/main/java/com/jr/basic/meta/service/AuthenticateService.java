//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.service;

import java.util.Set;

public interface AuthenticateService {
    Boolean isAnonymous();

    String getCurrentUserName();

    Long getCurrentUserId();

    Set<String> getCurrentUserRole();

    Object getCurrentUser();

    String encodePassword(String var1, String var2);

    Boolean currentUserHasRole(String[] var1);
}
