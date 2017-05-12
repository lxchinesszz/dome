package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext cac = SpringApplication.run(DemoApplication.class, args);
//        Arrays.asList(cac.getBeanDefinitionNames()).forEach(name -> System.out.println(name));
        System.gc();
    }
    @Scheduled(cron = "* * * * * ?")
    public void test(){
        System.out.println(11111);
    }
}
