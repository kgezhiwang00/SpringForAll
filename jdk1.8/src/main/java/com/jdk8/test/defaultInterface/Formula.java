package com.jdk8.test.defaultInterface;

/**
 * @Auther: zcx
 * @Date: 2018/10/18 09:45
 * @Description:
 */
public interface Formula {

    double calculate(int a);

    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
