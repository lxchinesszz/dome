package com.example.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Package: com.example.config
 * @Description: 定制一个接口
 * @author: liuxin
 * @date: 17/2/23 下午4:20
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface MyLog {
  public String value() default "我是日志注解";
}
