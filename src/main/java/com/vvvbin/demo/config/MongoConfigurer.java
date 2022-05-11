package com.vvvbin.demo.config;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

@Configuration
@Slf4j
public class MongoConfigurer {

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.test")
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.test.pool")
    public MongoPoolProperties mongoPoolProperties() {
        return new MongoPoolProperties();
    }

    @Bean
    public MongoClientOptions mongoClientOptions(@Qualifier("mongoPoolProperties") MongoPoolProperties mongoPoolProperties) {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(mongoPoolProperties.getMaxConnectionsPerHost());
        builder.minConnectionsPerHost(mongoPoolProperties.getMinConnectionsPerHost());
        builder.maxConnectionIdleTime(mongoPoolProperties.getMaxConnectionIdleTime());
        builder.threadsAllowedToBlockForConnectionMultiplier(mongoPoolProperties.getMaxThreadBlockForConnection());
        builder.maxWaitTime(mongoPoolProperties.getMaxWaitTime());
        builder.connectTimeout(mongoPoolProperties.getConnectionTimeout());
        builder.socketTimeout(mongoPoolProperties.getSocketTimeout());
        builder.readPreference(ReadPreference.secondaryPreferred());
        return builder.build();
    }


    @Bean
    public MongoDbFactory mongoDbFactory(@Qualifier("mongoProperties") MongoProperties mongoProperties,
                                         @Qualifier("mongoClientOptions") MongoClientOptions mongoClientOptions) {
        MongoClientURI mongoClientURI = new MongoClientURI(mongoProperties.getUri(), MongoClientOptions.builder(mongoClientOptions));
        MongoDbFactory mongoDbFactory = null;
        try {
            mongoDbFactory = new SimpleMongoDbFactory(mongoClientURI);
        } catch (Exception e) {
            log.error("初始化mongoDbFactory出错，e:{}", e);
        }
        return mongoDbFactory;
    }



    @Bean
    @Autowired
    public MongoTemplate mongoTemplate(@Qualifier("mongoDbFactory") MongoDbFactory mongoDbFactory) {
        MongoTemplate mongoTemplate=new MongoTemplate(mongoDbFactory);
        return mongoTemplate ;
    }
}
