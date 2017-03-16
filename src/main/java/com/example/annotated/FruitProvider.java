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
public @interface FruitProvider {
    /**
     * 供应商编号
     * @return
     */
    public int id() default -1;

    /**
     * 供应商名称
     * @return
     */
    public String name() default "";

    /**
     * 供应商地址
     * @return
     */
    public String address() default "";
}
