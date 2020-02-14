package com.feng.companyframe.utils;

import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.exception.code.ResponseCodeInterface;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: 与前端统一返回的数据 格式
 * author: 冯凡利
 * create:  2020/2/1 15:22
 */
@Data
public class DataResult<T> {

    /*
     * 请求响应code，0表示请求成功，其他表示失败
     * */
    @ApiModelProperty(value = "请求响应code，0表示请求成功，其他表示失败")
    private int code = 0;

    /*
     * 响应客户端的提示
     * */
    @ApiModelProperty(value = "响应异常码详细情况")
    private String msg;

    /*
     * 响应客户端内容
     * */
    @ApiModelProperty(value = "响应客户端内容")
    private T data;

    /*
    * 加上相应的构造方法  7个
    * */

    public DataResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public DataResult(int code, T data) {
        this.code = code;
        this.msg = null;
        this.data = data;
    }

    public DataResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataResult() {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = null;
    }

    public DataResult(T data) {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = data;
    }

    public DataResult(ResponseCodeInterface responseCodeInterface) {
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
        this.data = null;
    }

    public DataResult(ResponseCodeInterface responseCodeInterface, T data) {
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
        this.data = data;
    }

    // 以下 为定义的方法，直接调用上面封装好的 构造方法。  6个
    /**
     * 操作成功 data为null
     */
    public static <T> DataResult success() {
        return new <T>DataResult();
    }

    /**
     * 操作成功 data 不为null
     */
    public static <T> DataResult success(T data) {
        return new <T>DataResult(data);
    }

    /**
     * 自定义 返回操作 data 可控
     */
    public static <T> DataResult getResult(int code, String msg, T data) {
        return new <T>DataResult(code, msg, data);
    }

    /**
     * 自定义返回  data为null
     */
    public static <T> DataResult getResult(int code, String msg) {
        return new <T>DataResult(code, msg);
    }

    /**
     * 自定义返回 入参一般是异常code枚举 data为空
     */
    public static <T> DataResult getResult(BaseResponseCode responseCode) {
        return new <T>DataResult(responseCode);
    }

    /**
     * 自定义返回 入参一般是异常code枚举 data 可控
     */
    public static <T> DataResult getResult(BaseResponseCode responseCode, T data) {
        return new <T>DataResult(responseCode, data);
    }
}
