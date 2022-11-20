package com.gmail.arthurstrokov.aop.autoconfigure;

import com.gmail.arthurstrokov.aop.aspect.LoggingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "aop.logging.enabled", havingValue = "true", matchIfMissing = true)
public class LoggingAspectAutoConfigure {

    @Bean
    public LoggingAspect getLoggingAspect() {
        return new LoggingAspect();
    }
}
