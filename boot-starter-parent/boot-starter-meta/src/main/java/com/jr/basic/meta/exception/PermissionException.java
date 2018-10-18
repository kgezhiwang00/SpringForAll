//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.exception;

public class PermissionException extends RuntimeException {
    private static final long serialVersionUID = -5679470848335540739L;
    private String errorMessage;
    private Object[] arguments;

    public PermissionException(Exception e) {
        super(e);
    }

    public PermissionException(String message, Object... objects) {
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
