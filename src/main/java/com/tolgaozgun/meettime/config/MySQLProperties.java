package com.tolgaozgun.meettime.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.data.mysql")
@Data
public class MySQLProperties {
    private String driverClassName;
    private String username;
    private String password;
    private String host;
    private String port;
    private String database;

}
