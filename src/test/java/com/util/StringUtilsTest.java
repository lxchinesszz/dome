package com.util;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.util.StringUtils;

import javax.validation.Valid;

/**
 * @Package: com.util
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/28 下午4:23
 */
public class StringUtilsTest {
    @Test
    public void test() {
        String src = "";
        boolean flag = StringUtils.isEmpty(src);
        System.out.println(flag);
    }

    @Test
    public void dbUtilsTest()throws Exception {
        QueryRunner query = new QueryRunner();
        String ss="";
        String as=null;
        ValidNull.isNUll("ls",ss,as);
    }
}
