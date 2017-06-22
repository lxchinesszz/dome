package com.example.initannotated;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Package: com.example.initannotated
 * @Description:     @PostConstruct
 * @PreDestroy
 * @author: liuxin
 * @date: 17/5/16 上午11:37
 */
@Component
public class InitMain {
    @PostConstruct
    public void init(){
        System.out.println("执行初始化方法");
    }

    public void say(){
        System.out.println("正常方法");
    }

    @PreDestroy
    public void destory(){
        System.out.println("执行销毁方法");
    }
}



