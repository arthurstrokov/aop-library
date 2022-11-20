package com.gmail.arthurstrokov.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class CountableAspect {

    private final Logger log;
    private static final Map<String, Integer> countingMap = new HashMap<>();

    public CountableAspect(String loggerName) {
        super();
        log = LoggerFactory.getLogger(loggerName);
    }

    @Pointcut("@annotation(com.gmail.arthurstrokov.aop.annotations.Countable)")
    public void executeCounting() {
        // Pointcut
    }

    @Before("executeCounting()")
    public void incrementCount(final JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName();
        if (countingMap.containsKey(methodName)) {
            int current = countingMap.get(methodName);
            current++;
            countingMap.put(methodName, current);
        } else {
            countingMap.put(methodName, 1);
        }
        StringBuilder message = new StringBuilder("Service '");
        countingMap.forEach((k, v) -> message.append(k).append("' executed (").append(v).append(") times"));
        log.info("{}", message);
    }
}
