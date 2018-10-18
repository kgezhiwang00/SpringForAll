//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorMessage;
    private Object[] arguments;

    public ServiceException(Throwable e) {
        super(e);
        this.errorMessage = e.getMessage();
    }

    public ServiceException(Exception e) {
        super(e);
        this.errorMessage = e.getMessage();
    }

    public ServiceException(Exception e, String message) {
        super(e);
        this.errorMessage = message;
    }

    public ServiceException(String message, Object... objects) {
        super(message);
        this.errorMessage = message;
        this.arguments = objects;
    }

    public Object[] getArguments() {
        return this.arguments;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
