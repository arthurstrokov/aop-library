package com.gmail.arthurstrokov.aop.autoconfigure;

import com.gmail.arthurstrokov.aop.aspect.TrackTimeAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(TrackTimeAspect.class)
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnProperty(name = "aop.logging.enabled", havingValue = "true", matchIfMissing = true)
public class TrackTimeLoggingAutoConfigure {

    private final LoggingProperties properties;

    public TrackTimeLoggingAutoConfigure(LoggingProperties properties) {
        this.properties = properties;
    }

    @Bean
    public TrackTimeAspect loggableAspect() {
        return new TrackTimeAspect(properties.getLoggerName());
    }
}