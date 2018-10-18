//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.controller;

import com.google.common.base.Throwables;
import com.jr.basic.api.exception.GraphqlExecuteException;
import com.jr.basic.meta.exception.NoAuthException;
import com.jr.basic.meta.exception.PermissionException;
import com.jr.basic.meta.exception.ServiceException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    @Autowired(
        required = false
    )
    private MessageSource messageSource;

    public ControllerExceptionHandler() {
    }

    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        return super.handleBindException(ex, headers, status, request);
    }

    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleServletRequestBindingException(ex, headers, status, request);
    }

    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<AjaxResponse> handleException(ConstraintViolationException ex, WebRequest request) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuffer message = new StringBuffer();
        Iterator var5 = violations.iterator();

        while(var5.hasNext()) {
            ConstraintViolation<?> violation = (ConstraintViolation)var5.next();
            message.append(violation.getRootBeanClass().getSimpleName() + "." + violation.getPropertyPath().toString() + ":" + violation.getMessage() + "\n");
        }

        AjaxResponse ajaxResponse = new AjaxResponse(HttpStatus.BAD_REQUEST.value());
        ajaxResponse.setMessage(message.toString());
        if (log.isErrorEnabled()) {
            log.error(ex.getMessage());
        }

        return new ResponseEntity(ajaxResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MultipartException.class})
    public ResponseEntity handleFileException(MultipartException e, HttpServletRequest request) {
        AjaxResponse ajaxResponse = new AjaxResponse(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ajaxResponse.setMessage(e.getMessage());
        return new ResponseEntity(ajaxResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NoAuthException.class})
    public final ResponseEntity<AjaxResponse> handleException(NoAuthException ex, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        AjaxResponse ajaxResponse = new AjaxResponse(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity(ajaxResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({PermissionException.class})
    public final ResponseEntity<AjaxResponse> handleException(PermissionException ex, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        AjaxResponse ajaxResponse = new AjaxResponse(HttpStatus.FORBIDDEN.value());
        return new ResponseEntity(ajaxResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ServiceException.class})
    public final ResponseEntity<AjaxResponse> handleException(ServiceException ex, WebRequest request) {
        if (log.isErrorEnabled()) {
            log.error("error for serviceException info {} ", Throwables.getRootCause(ex).getLocalizedMessage());
        }

        AjaxResponse ajaxResponse = new AjaxResponse(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String message = ex.getMessage();
        ajaxResponse.setMessage("Service Error");
        return new ResponseEntity(ajaxResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({GraphqlExecuteException.class})
    public final ResponseEntity<AjaxResponse> handleException(GraphqlExecuteException ex, WebRequest request) {
        if (log.isErrorEnabled()) {
            log.error("error for serviceException info {} ", ex.getMessage());
        }

        AjaxResponse ajaxResponse = new AjaxResponse(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ajaxResponse.setMessage(ex.getMessage());
        return new ResponseEntity(ajaxResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<AjaxResponse> handleException(RuntimeException ex, WebRequest request) {
        ex.printStackTrace();
        log.error(ex.getLocalizedMessage());
        AjaxResponse ajaxResponse = new AjaxResponse(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(ajaxResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected String getMessage(String message, Object[] arguments) {
        try {
            return this.messageSource.getMessage(message, arguments, Locale.getDefault());
        } catch (Exception var4) {
            log.error("get message error :  " + Throwables.getRootCause(var4).getLocalizedMessage());
            return message;
        }
    }
}
