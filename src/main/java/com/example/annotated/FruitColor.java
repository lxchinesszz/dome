package com.example.annotated;
import java.lang.annotation.*;
/**
 * @Package: com.example.annotated
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/16 下午2:56
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    /**
     * 颜色枚举
     * @author peida
     *
     */
    public enum Color{ BULE,RED,GREEN};

    /**
     * 颜色属性
     * @return
     */
    Color fruitColor() default Color.GREEN;

}
