package com.spring.api.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: zcx
 * @Date: 2018/10/19 09:05
 * @Description:不可变集合，顾名思义就是说集合是不可被修改的。集合的数据项是在创建的时候提供，并且在整个生命周期中都不可改变。
 * ：Collections.unmodifiableList实现的不是真正的不可变集合，当原始集合修改后，不可变集合也发生变化。E
 * 总结一下JDK的Collections.unmodifiableXXX方法实现不可变集合的一些问题：

　　1.它用起来笨拙繁琐你不得不在每个防御性编程拷贝的地方用这个方法
　　2.它不安全：如果有对象reference原始的被封装的集合类，这些方法返回的集合也就不是正真的不可改变。
　　3.效率低：因为它返回的数据结构本质仍旧是原来的集合类，所以它的操作开销，包括并发下修改检查，hash table里的额外数据空间都和原来的集合是一样的。
Guava的immutable集合

　　Guava提供了对JDK里标准集合类里的immutable版本的简单方便的实现，以及Guava自己的一些专门集合类的immutable实现。当你不希望修改一个集合类，或者想做一个常量集合类的时候，使用immutable集合类就是一个最佳的编程实践。

注意：每个Guava immutable集合类的实现都拒绝null值。我们做过对Google内部代码的全面的调查，并且发现只有5%的情况下集合类允许null值，而95%的情况下都拒绝null值。万一你真的需要能接受null值的集合类，你可以考虑用Collections.unmodifiableXXX。

　　Immutable集合使用方法：
　　一个immutable集合可以有以下几种方式来创建：
　　1.用copyOf方法, 譬如, ImmutableSet.copyOf(set)
　　2.使用of方法，譬如，ImmutableSet.of("a", "b", "c")或者ImmutableMap.of("a", 1, "b", 2)
　　3.使用Builder类
 */
public class ImmutableTest {

    @Test
    public void testJDKImmutable(){

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        List<String> unmodifiableList = Collections.unmodifiableList(list);
        System.out.println(unmodifiableList);
        List<String> unmodifiableList1 = Collections.unmodifiableList(Arrays.asList("a","b","c"));
        System.out.println(unmodifiableList1);

        String temp=unmodifiableList.get(1);
        System.out.println("unmodifiableList [0]："+temp);
        unmodifiableList.add("baby");
        list.add("aaaa");
        System.out.println("list add a item after list:"+list);
        System.out.println("list add a item after unmodifiableList:"+unmodifiableList);

//        unmodifiableList1.add("bb");
//        System.out.println("unmodifiableList add a item after list:"+unmodifiableList1);

        unmodifiableList.add("cc");
        //System.out.println("unmodifiableList add a item after list:"+unmodifiableList);

    }

    @Test
    public void testImmutable(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        ImmutableList<String> imlist = ImmutableList.copyOf(list);
        System.out.println("imlist："+imlist);

        list.add("d");
        System.out.println("imlist："+imlist);

        ImmutableList<String> imOflist=ImmutableList.of("peida","jerry","harry");

        System.out.println("imOflist："+imOflist);

        ImmutableSortedSet<String> imSortList=ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");

        System.out.println("imSortList："+imSortList);

    }
}
