package com.tolgaozgun.meettime.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(MySQLProperties.class)
public class DataSourceConfig {

    @Autowired
    private MySQLProperties mySQLProperties;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mySQLProperties.getDriverClassName());
        dataSource.setUsername(mySQLProperties.getUsername());
        dataSource.setPassword(mySQLProperties.getPassword());
        // Construct URL from properties and set it
        String url = "jdbc:mysql://" + mySQLProperties.getHost() + ":" + mySQLProperties.getPort() + "/" + mySQLProperties.getDatabase();
        dataSource.setUrl(url);
        return dataSource;
    }
}
