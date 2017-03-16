package com.example;

import com.example.builder.User;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Package: com.example
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/9 下午6:06
 */
public interface iflyer {
    public void fei();

    default void run() {
        System.out.println("一起跑");
    }


    static class UseIflyer {
        public iflyer use() {
            return new iflyer() {
                @Override
                public void fei() {
                    System.out.println("一起飞");
                }
            };
        }

        public static void main(String[] args) throws Exception{
            UseIflyer useIflyer = new UseIflyer();
            useIflyer.use().fei();
            useIflyer.use().run();


            List<Integer> names = Arrays.asList(1, 2, 3, 4, 5);
            Collections.sort(names, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 > o2 ? -1 : 1;
                }
            });
            names.forEach(num -> System.out.println(num));
            User user = null;

            User user1 = new User("lx", "lx", "lx");

            Optional<User> optional = Optional.of(user1);
            optional.get();                 // "bam"
            optional.orElse(user1);    // "bam"
            System.out.println(optional.get().age);
            System.out.println(optional.orElse(user1).age);


            List<String> stringCollection = new ArrayList<>();
            stringCollection.add("dsbdd2");
            stringCollection.add("asbaa2");
            stringCollection.add("bsbbb1");
            stringCollection.add("aasba1");
            stringCollection.add("bbsbb3");
            stringCollection.add("ccsbc");
            stringCollection.add("bbbsb2");
            stringCollection.add("ddsbd1");

            //对包含sb的字符从大到小排序
            stringCollection.stream().filter((x) -> {
                return x.contains("sb");
            }).sorted(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() > o2.length() ? -1 : 1;
                }
            }).forEach(System.out::println);


//            /BBSBB3
//                    BBBSB2
//            ASBAA2
//                    AASBA1
            stringCollection
                    .stream()
                    .map(String::toUpperCase)
                    .sorted((a, b) -> b.compareTo(a))
                    .forEach(System.out::println);

            System.out.println(stringCollection.stream().count());//8


            Optional optional1 = stringCollection.stream().reduce((x, xx) -> {
                return x + "|" + xx;
            });
            //dsbdd2|asbaa2|bsbbb1|aasba1|bbsbb3|ccsbc|bbbsb2|ddsbd1
            optional1.ifPresent(System.out::println);

            Map<Integer, String> map = new HashMap<>();
            map.put(1,"ss");
            map.put(2,"sss");
            map.put(3,"ssss");

            map.computeIfPresent(3, (num, val) -> val + num);
            System.out.println(map.get(3));

            String str="%B3%C9%B9%A6";
            System.out.println(new String(URLDecoder.decode(str).getBytes("iso-8859-1"),"GBK"));;
        }


    }

}
