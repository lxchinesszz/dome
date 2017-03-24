package com.example.Nashorn;

import java.util.function.Supplier;

/**
 * @Package: com.example.Nashorn
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/23 下午5:26
 */
public interface DefaulableFactory {
    static Defaulable create(Supplier< Defaulable > supplier){
        return supplier.get();
    }
}
