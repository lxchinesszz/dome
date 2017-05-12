package com.example.stream;

import com.example.builder.User;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Package: com.example.stream
 * @Description: Java8 stream Api
 * @author: liuxin
 * @date: 17/3/10 下午2:42
 */
public class StreamTest {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = numbers.stream();
        stream.filter((x) -> {
            return x % 2 == 0;
        }).map((x) -> {
            return x * x;
        }).sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2 > 0 ? -1 : 1;//前面数字大，就将序排列。
            }
        }).forEach(System.out::println);


//        stream.skip(5).limit(5).forEach(System.out::print);

//        stream.filter((x)->{
//            return x>5;
//        }).forEach(System.out::print);

        String[] chapter = new String[]{"a", "b", "c", "d"};
        Optional optional = Arrays.stream(chapter).map((x) -> {
            return x.toUpperCase();
        }).reduce((x, xx) -> {
            return x + "|" + xx;
        });
        optional.ifPresent(System.out::println);


        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);

        User user=null;
        System.out.println(ObjectUtils.isEmpty(user));
    }


}
