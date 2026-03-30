package com.feng.companyframe.test;

/**
 * description: StudyEnum
 * author: 冯凡利
 * create:  2020/2/1 19:09
 */
public class StudyEnum {
    /*
    * 1、enum 不能被继承、实现，不能new 对象，
    * 2、不能有子类
    * 3、就是定义一组常量 ，简化类中定义常量的方式。 平时定义常量就是： public static final String a = "22"
    * */
    public static void main(String[] args) {
        System.out.println(EnumTest.ONE);
        System.out.println(EnumTest.ONE.name());
        System.out.println(EnumTest.ONE.ordinal());
        System.out.println(EnumTest.ONE.getDeclaringClass());
        System.out.println(EnumTest.ONE.getClass());
        System.out.println(EnumTest.values().toString());
        System.out.println(EnumTest.valueOf("ONE"));

        System.out.println();
        System.out.println(EnumTest.ONE.getA());
        System.out.println(EnumTest.ONE.getOne());
        System.out.println(EnumTest.TWO.getA());
        System.out.println(EnumTest.TWO.getOne());

        System.out.println();
        System.out.println(EnumTest.ONE);
        System.out.println(EnumTest.ONE);
        System.out.println(EnumTest.TWO);
        System.out.println(EnumTest.TWO);
    }
}

