package com.example.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.MongoHealthIndicator;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Package: com.example.config
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/6 下午3:28
 */
public class MyHealth extends MongoHealthIndicator {

    public MyHealth(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        super.doHealthCheck(builder);
    }
}
