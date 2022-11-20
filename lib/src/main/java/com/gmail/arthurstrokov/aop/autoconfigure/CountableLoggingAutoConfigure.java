package com.gmail.arthurstrokov.aop.autoconfigure;

import com.gmail.arthurstrokov.aop.aspect.CountableAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "aop.logging.enabled", havingValue = "true", matchIfMissing = true)
public class CountableLoggingAutoConfigure {

    private final LoggingProperties properties;

    public CountableLoggingAutoConfigure(LoggingProperties properties) {
        this.properties = properties;
    }

    @Bean
    public CountableAspect getCountableAspect() {
        return new CountableAspect(properties.getLoggerName());
    }
}
