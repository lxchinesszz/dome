package com.example.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by liuxin on 17/1/16.
 */
//@Component
//@RabbitListener(queues = "hellos")
public class Receiver {
    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver : " + hello);
    }
}
