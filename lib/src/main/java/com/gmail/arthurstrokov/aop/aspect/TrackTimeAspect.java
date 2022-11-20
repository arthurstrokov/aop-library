package com.gmail.arthurstrokov.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class TrackTimeAspect {
    private final Logger log;

    public TrackTimeAspect(String loggerName) {
        super();
        log = LoggerFactory.getLogger(loggerName);
    }

    @Pointcut("@annotation(com.gmail.arthurstrokov.aop.annotations.TrackTime)")
    public void executeTiming() {
        // Pointcut
    }

    @Around("executeTiming()")
    public Object logMethodTiming(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object returnValue = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info("Time Taken by service '{}' is {} ms",
                proceedingJoinPoint.getSignature().getName(),
                stopWatch.getTotalTimeMillis()
        );
        return returnValue;
    }
}
