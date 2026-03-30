package com.feng.companyframe.aop.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: MyLog
 * @Description： 描述
 * @createTime: 2020/2/28 15:24
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    /*
    * 用户操作哪个模块
    * */
    String title() default "";

    /*
    * 记录用户操作的动作
    * */
    String action() default "";
}

