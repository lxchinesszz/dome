package com.example.initannotated;

import com.example.DemoApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Package: com.example.initannotated
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/5/16 上午11:40
 */
public class InitMainTest  extends DemoApplicationTests{
    @Autowired
    InitMain initMain;
    @Test
    public void say() throws Exception {
        initMain.say();
    }

}