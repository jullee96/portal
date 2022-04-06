package com.hamonize.portal;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.beans.factory.annotation.Value;
import lombok.*;

@ToString
@Setter
@Getter
@Configuration
@PropertySource(value = "file:${user.home}/env/portal.properties", ignoreResourceNotFound = true)
public class GlobalPropertySource {

    @Value("${spring.datasource.primary.jndi-name}")
    private String jndiname;

    @Value("${spring.datasource.driverClassName}")
    private String driverclassname;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
 
}
