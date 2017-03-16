package com.example.config;

import org.springframework.boot.actuate.health.RedisHealthIndicator;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @Package: com.example.config
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/6 下午12:50
 */
public class RedisHealth extends RedisHealthIndicator {
    public RedisHealth(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
