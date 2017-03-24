package com.example.Nashorn;

import java.util.function.Supplier;

/**
 * @Package: com.example.Nashorn
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/23 下午5:40
 */
public  class Car {


    public static Car create( Supplier<Car>supplier){
        return supplier.get();
    }

    public static void getName(Car c){
        System.out.println("宝马");
    }

    public void follow( final Car another ) {
        System.out.println( "Following the " + another.toString() );
    }

    public void repair() {
        System.out.println( "Repaired " + this.toString() );
    }

}
