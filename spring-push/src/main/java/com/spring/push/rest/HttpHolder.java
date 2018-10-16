//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.spring.push.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpHolder {
    private static final ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal();
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal();

    public HttpHolder() {
    }

    public static void setResponse(HttpServletResponse response) {
        responseHolder.set(response);
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse)responseHolder.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest)requestHolder.get();
    }
}
