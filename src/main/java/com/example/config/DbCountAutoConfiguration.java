package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * @Package: com.example.config
 * @Description: 健康监测，未完成
 * @author: liuxin
 * @date: 17/3/6 下午3:31
 */
@EnableAutoConfiguration
public class DbCountAutoConfiguration {
    @Autowired
    HealthAggregator healthAggregator;
    @Autowired
    MongoTemplate mongoTemplate;

    @Bean
    public HealthIndicator dbCountHealthIndicator(Collection<CrudRepository> repositories) {
        CompositeHealthIndicator compositeHealthIndicator = new
                CompositeHealthIndicator(healthAggregator);
        for (CrudRepository repository: repositories) {
            String name = mongoTemplate.getDb().getName();
            compositeHealthIndicator.addHealthIndicator(name, new MyHealth(mongoTemplate));
        }
         return compositeHealthIndicator;
    }
}
