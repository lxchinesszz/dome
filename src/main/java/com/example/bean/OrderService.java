package com.example.bean;

import com.example.builder.User;

import java.lang.reflect.Field;

/**
 * @Package: com.example.bean
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/4/5 下午12:44
 */
@MyService
public class OrderService {
    @MAuto
    User user;

    public void test() {
        System.out.println(user.get());
    }


    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("com.example.bean.OrderService");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MAuto.class)) {
                field.setAccessible(true);//使用private的用
                Class clsszz = User.class;
                Object obj = cls.newInstance();
                System.out.println(clsszz.newInstance());
                field.set(obj, clsszz.newInstance());
                System.out.println(((User) field.get(obj)).get());
            }
        }
    }
}
