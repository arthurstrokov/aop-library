package com.gmail.arthurstrokov.service.autoconfigure;

import com.gmail.arthurstrokov.service.aop.LoggableAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(LoggableAspect.class)
@EnableConfigurationProperties(LoggingProperties.class)
public class LoggingAutoConfigure {

    private final LoggingProperties properties;

    public LoggingAutoConfigure(LoggingProperties properties) {
        this.properties = properties;
    }

    @Bean
    public LoggableAspect loggableAspect(){
        return new LoggableAspect(properties.getLoggerName());
    }
}