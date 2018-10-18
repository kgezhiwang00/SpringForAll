//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.aop.security;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 方法拦截器，如果方法允许debug，则在方法执行前后打log日志
 */
@Component
@Aspect
public class ServiceAspect {
    private static final Logger log = LoggerFactory.getLogger(ServiceAspect.class);

    public ServiceAspect() {
    }

    @Pointcut("execution(* com.jr.basic.meta.service..*.*(..))")
    public void dynamicServicePointcut() {
    }

    @Around("dynamicServicePointcut()")
    public Object serviceSecurityAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===============================111 =>> security interceptor ");
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", new Object[]{joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs())});
        }

        Object result = joinPoint.proceed();
        if (log.isDebugEnabled()) {
            log.debug("Exit: {}.{}() with result = {}", new Object[]{joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result});
        }

        return result;
    }
}
