package com.feng.companyframe.controller;

import com.feng.companyframe.bean.TesTBean;
import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @ApiOperation(value = "测试一：测试统一返回格式接口")
    @GetMapping("/home1")
    public DataResult hello(){
        //测试1
        DataResult result = new DataResult();
        result.setData("哈哈 我请求成功了");
        return result;
    }
    @ApiOperation(value = "测试二：测试统一返回格式接口")
    @GetMapping("/home2")
    public DataResult hello2(){
        //测试2
        DataResult result = DataResult.getResult(BaseResponseCode.SUCCESS);
        result.setData("哈哈 我请求成功了");
        return result;
    }
    @ApiOperation(value = "测试三：返回list集合，并出现异常被catch")
    @GetMapping("/home3")
    public DataResult hello3(){
        //测试3
        DataResult result = null;
        try {
            result = DataResult.getResult(BaseResponseCode.SUCCESS);
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            result.setData(list);
            int a = 1/0;
        } catch (Exception e) {
            result = DataResult.getResult(BaseResponseCode.SYSTEM_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "测试四：使用全局异常统一处理")
    @GetMapping("/home4")
    public DataResult<String> getHome(){
        // 测试 4
        int i = 1/0;
        DataResult<String> result = DataResult.success("成功测试了");
        return result;
    }


    //新增测试主动抛出业务异常接口
    @ApiOperation(value = "测试五：测试主动抛出业务异常接口")
    @GetMapping("/business/error")
    public DataResult<String> testBusinessError(@RequestParam String type){
        if (!"1".equals(type) || "2".equals(type) || "3".equals(type)){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        DataResult<String> result = new DataResult<>();
        return  result;
    }

    /**
     * @RequestBody: 接收值
     * @Valid： 开启校验,放在对应的 Javabean 前面
     *
     * @param bean
     * @return
     */
    @ApiOperation(value = "测试六：测试 Hibernate Validator 注解")
    @PostMapping("/valid/error")
    public DataResult testValid(@RequestBody @Valid TesTBean bean){
        DataResult result = DataResult.success();
        return result;
    }


}
