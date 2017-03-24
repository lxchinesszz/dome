package com.example.Nashorn;

/**
 * @Package: com.example.Nashorn
 * @Description: 默认接口
 * @author: liuxin
 * @date: 17/3/23 下午5:25
 */
public interface Defaulable {
    default String notRequired() {
        return "Default implementation";
    }
}
