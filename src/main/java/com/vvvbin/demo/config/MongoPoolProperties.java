package com.vvvbin.demo.config;

import lombok.Data;

//@ConfigurationProperties(prefix = "spring.data.mongodb.pool")
@Data
public class MongoPoolProperties {

    private Integer maxConnectionsPerHost;
    private Integer minConnectionsPerHost;
    private Integer maxConnectionIdleTime;
    private Integer maxThreadBlockForConnection;
    private Integer maxWaitTime;
    private Integer connectionTimeout;
    private Integer socketTimeout;

}
