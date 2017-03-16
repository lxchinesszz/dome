package com.example.stream;

import java.util.Arrays;
import java.util.List;
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
        }).forEach(System.out::println);


//        stream.skip(5).limit(5).forEach(System.out::print);

//        stream.filter((x)->{
//            return x>5;
//        }).forEach(System.out::print);
    }


}
