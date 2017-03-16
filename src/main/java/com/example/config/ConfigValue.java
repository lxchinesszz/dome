package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Package: com.example.config
 * @Description: 自动注入配置信息
 * @author: liuxin
 * @date: 17/3/10 上午10:16
 */
@Component
@ConfigurationProperties(prefix = "demo.info")
public class ConfigValue {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
