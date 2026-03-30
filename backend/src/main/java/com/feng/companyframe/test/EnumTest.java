package com.feng.companyframe.test;

/**
 * description: EnumTest
 * author: 冯凡利
 * create:  2020/2/1 19:14
 */
public enum EnumTest {
    ONE(100, "1"),
    TWO(200 , "2");

    Integer a;

    String one ;

    EnumTest(Integer a, String one){
        this.a = a;
        this.one = one;
    }


    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }
}
