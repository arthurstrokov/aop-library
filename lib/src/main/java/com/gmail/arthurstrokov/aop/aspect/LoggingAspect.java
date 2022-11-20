package com.gmail.arthurstrokov.aop.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@annotation(com.gmail.arthurstrokov.aop.annotations.Logging)")
    public void loggingPointcut() {
        // Pointcut
    }

    @Pointcut("execution(* (@org.springframework.stereotype.Service *).*(..))")
    public void servicePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("execution(* (@org.springframework.web.bind.annotation.RestController *).*(..))")
    public void controllerPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
    public void logAfterThrowing(final JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    @Around("loggingPointcut()")
    public Object logAroundLoggingAnnotation(final ProceedingJoinPoint joinPoint) {
        return logAround(joinPoint);
    }

    @Around("servicePointcut()")
    public Object logAroundService(final ProceedingJoinPoint joinPoint) {
        return logAround(joinPoint);
    }

    @Around("controllerPointcut()")
    public Object logAroundController(final ProceedingJoinPoint joinPoint) {
        return logAround(joinPoint);
    }

    @SneakyThrows
    private Object logAround(final ProceedingJoinPoint joinPoint) {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
}
