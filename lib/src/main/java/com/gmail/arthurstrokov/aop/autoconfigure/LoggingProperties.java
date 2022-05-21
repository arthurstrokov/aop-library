package com.gmail.arthurstrokov.aop.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("logging.method.exec")
public class LoggingProperties {

    private String loggerName = "Audit logger";
}
