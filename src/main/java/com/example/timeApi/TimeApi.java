package com.example.timeApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @Package: com.example.timeApi
 * @Description: Time 的Api
 * Java 8通过发布新的Date-Time API (JSR 310)来进一步加强对日期与时间的处理。
 * 对日期与时间的操作一直是Java程序员最痛苦的地方之一。标准的 java.util.Date
 * 以及后来的java.util.Calendar一点没有改善这种情况（可以这么说，它们一定程度
 * 上更加复杂）。
 * @author: liuxin
 * @date: 17/3/23 下午4:51
 */
public class TimeApi {
    public static void main(String[] args) {
        //16:54:21.367
        LocalTime time = LocalTime.now();
        System.out.println(time);
        //2017-03-23
        LocalDate date = LocalDate.now();
        System.out.println(date);
        //2017-03-23T16:55:21.927MM
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        //2017年03月23日
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY年MM月dd日");
        System.out.println(date.format(formatter).toString());
        //转换日期
        LocalDateTime ldt = LocalDateTime.parse("2017-03-23 17:03:22",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //2017年03月23日
        System.out.println(ldt.format(formatter));
    }
}
