package com.vvvbin.demo.config;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(
        prefix = "spring.data.mongodb"
)
@Data
public class MongoSettingsProperties {
    @NotBlank
    @Value("${spring.data.mongodb.test}")
    private String database;
 
    @NotBlank
    @Value("${spring.data.mongodb.host}")
    private String host;
 
    @NotBlank
    @Value("${spring.data.mongodb.port}")
    private String port;
 
    @Value("${spring.data.mongodb.username}")
    private String username;
 
    @Value("${spring.data.mongodb.password}")
    private String password;
 
    private String replicaSet;
    private String authenticationDatabase;
    private Integer minConnectionsPerHost = 10;
    private Integer connectionsPerHost = 2;
}