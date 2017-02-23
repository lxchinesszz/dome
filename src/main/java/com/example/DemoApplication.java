package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext cac = SpringApplication.run(DemoApplication.class, args);
//        Arrays.asList(cac.getBeanDefinitionNames()).forEach(name -> System.out.println(name));
    }
}
