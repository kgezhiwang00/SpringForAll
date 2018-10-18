package com.jdk8.test.defaultInterface;

/**
 * @Auther: zcx
 * @Date: 2018/10/18 09:46
 * @Description:ava 8允许我们给接口添加一个非抽象的方法实现，
 * 只需要使用 default关键字即可，这个特征又叫做扩展方法，
 */
public class FormulaImpl implements Formula {
    @Override
    public double calculate(int a) {
        return 0;
    }




    public static void main(String[] args) {
        Formula formula = new Formula(){

            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
    }
}
