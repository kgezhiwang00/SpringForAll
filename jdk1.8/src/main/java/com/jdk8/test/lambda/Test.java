package com.jdk8.test.lambda;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Auther: zcx
 * @Date: 2018/10/18 10:02
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>();
        List<String> newList = new ArrayList<>();
        nameList.add("zcx");
        nameList.add("yhy");
        nameList.add("xyz");
        nameList.add("zxyz");

        nameList.stream().filter((s)->s.startsWith("z")).forEach(newList::add);
        newList.stream().forEach(System.out::println);
//
//        Collections.sort(nameList, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
//
//
//        Collections.sort(nameList,(String o1,String o2) ->{
//            return o1.compareTo(o2);
//        });
//
//        Collections.sort(nameList,(o1,o2)->o1.compareTo(o2));
//
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//        Integer converted = converter.convert("123");
//        System.out.println(converted);    // 123
//
//        Converter<String,Integer> converter1 = Integer::valueOf;
//        Predicate<String> predicate = (s) -> s.length() == 0;
//        Predicate<String> predicates = (s) -> s.equals("aaa");
//        //Boolean flag =predicate.and(predicates).test("aaa")   ;            // true
//        Boolean flags  = predicate.negate().test("");     // false
//        //System.out.println(flag);
//
//        Predicate<Boolean> isNull = Objects::isNull;
//
//        Predicate<String> isEmpty = String::isEmpty;
//        Boolean flags1  = isEmpty.negate().test("");
//        //System.out.println(flags1);
//
//        Function<String, Integer> toInteger = Integer::valueOf;
//
//        Optional<String> optional = Optional.of("null");
//        String name = optional.orElse("bbb");
//        Boolean flag  = optional.isPresent();
//        System.out.println("name:"+name);

    }
}
