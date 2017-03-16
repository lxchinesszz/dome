package com.example.config;

import com.mongodb.Mongo;
import com.mongodb.client.jndi.MongoClientFactory;
import org.springframework.boot.Banner;

/**
 * @Package: com.example.config
 * @Description: 配置mongo连接池
 * @author: liuxin
 * @date: 17/3/8 下午6:12
 */
public class MongoConfig {
    Mongo mongo=new Mongo("");

    public Mongo getMongo() {
        return mongo;
    }
}
