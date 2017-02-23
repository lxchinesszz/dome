package com.example.config;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * Created by liuxin on 17/1/13.
 */
public  class MockitoBasedTest {
    @Before
    public void setUp() throws Exception {
        // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
        MockitoAnnotations.initMocks(this);
    }
}
