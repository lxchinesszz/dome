package com.example;

import com.example.mq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private Sender sender;
    @Autowired
    Jedis jedis;
    @Test
    public void hello1() throws Exception {
        System.out.println(jedis);
    }

    @Test
    public void hello() throws Exception {
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        threadPoolTaskExecutor.setMaxPoolSize(3);
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public synchronized void run() {
                int i=0;
                while(true){
                    sender.send();
                    i++;
                    System.out.println(i);
                }
            }
        });

    }
}
