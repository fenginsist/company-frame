package com.feng.companyframe.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestList
 * @Description： 描述
 * @createTime: 2020/2/15 9:57
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class TestList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("22");
        list.add("33");
        list.add("44");
        list.add("55");

        List<String> list1 = new ArrayList<>(list);
        System.out.println(list1.toString());

        List<String> list2 = new ArrayList<>(2);
        list2.add("11");
        list2.add("22");
        list2.add("33");
        list2.add("44");
        System.out.println(list2.toString());
    }
}

