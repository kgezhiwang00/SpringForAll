package com.jdk8.test.lambda;

/**
 * @Auther: zcx
 * @Date: 2018/10/18 10:09
 * @Description:@FunctionalInterface 只运行有一个抽象方法
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
