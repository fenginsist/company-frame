package com.feng.companyframe.controller;

import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * description: TestController
 * author: 冯凡利
 * create:  2020/2/1 14:41
 */
@Api(value = "测试相关接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation(value = "Sagger 测试 index 接口",httpMethod = "GET", response = String.class)
    @GetMapping("/index")
    public String testResult(){
        return "hello world";
    }


    @ApiOperation(value = "测试统一返回格式接口")
    @GetMapping("/home")
    public DataResult hello(){
        //测试1
//        DataResult result = new DataResult();
//        result.setData("哈哈 我请求成功了");

        //测试2
//        DataResult result = DataResult.getResult(BaseResponseCode.SUCCESS);
//        result.setData("哈哈 我请求成功了");

        //测试3
        DataResult result = DataResult.getResult(BaseResponseCode.SUCCESS);
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        result.setData(list);
        return result;
    }
}
