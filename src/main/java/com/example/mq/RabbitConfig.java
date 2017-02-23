package com.example.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuxin on 17/1/16.
 */
//@Configuration
//@EnableAutoConfiguration
public class RabbitConfig {
    @Bean
    public Queue helloQueue(){
        return new Queue("hellos");
    }
}
