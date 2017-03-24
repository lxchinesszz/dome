package com.example.Nashorn;

import java.util.Arrays;
import java.util.List;


/**
 * @Package: com.example.Nashorn
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/23 下午5:27
 */
public class DefaulableTest {
    public static void main(String[] args) {
        Defaulable defaulable = DefaulableFactory.create(DefaulableImpl::new);
        System.out.println(defaulable.notRequired());

        Defaulable defaulable1 = DefaulableFactory.create(DefaulableImpl2::new);
        System.out.println(defaulable1.notRequired());

        Car car = Car.create(Car::new);
        Car car1 = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car, car1);
        //引用是静态方法引用，它的语法是Class::static_method。请注意这个方法接受一个Car类型的参数。
        cars.forEach(Car::getName);
        //引用是特定类的任意对象的方法引用，它的语法是Class::method。请注意，这个方法没有参数。
        cars.forEach(Car::repair);
        //引用是特定对象的方法引用，它的语法是instance::method。请注意，这个方法接受一个Car类型的参数
        Car police = Car.create( Car::new );
        cars.forEach( police::follow );


    }
}

