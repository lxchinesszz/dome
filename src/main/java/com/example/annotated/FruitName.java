package com.example.annotated;

import java.lang.annotation.*;

/**
 * @Package: com.example.annotated
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/16 下午2:55
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName{
    String value() default "";
}
