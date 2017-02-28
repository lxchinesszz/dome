package com.CodeInfo;


import org.junit.Test;

/**
 * Created by liuxin on 17/1/22.
 */
public class CodeInfo {
    @Test
    public void test() {
        String name = null;
        int age = name == null ? 23 : 322;
        System.out.println(age);
        throw new NullPointerException("dsafds");
    }
}
