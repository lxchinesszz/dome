package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @Package: com.util
 * @Description: 一个检验参数是否空值的对象
 * @author: liuxin
 * @date: 17/3/28 下午4:36
 */
public final class ValidNull {
    private final static Logger logger = LoggerFactory.getLogger(ValidNull.class);

    public static void isNUll(Object... objects) throws Exception {
        for (int i = 0; i < objects.length; i++) {
            Object o = objects[i];
            if (o != null) {
                String str = (String) o;
                if (StringUtils.isEmpty(str)) {
                    Class c = o.getClass();
                    logger.info("请检查第{}个字段", i);
                }
            } else {
                String oo = String.valueOf(o);
                logger.info("请检查第{}个字段", i);
            }
        }
    }
}
