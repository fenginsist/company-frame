package com.feng.companyframe.exception;


import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.exception.code.ResponseCodeInterface;
import lombok.Data;

/**
 * @ClassName: BusinessException
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class BusinessException extends RuntimeException{
    /**
     *  异常 code
     */
    private final int code;

       /**
     *  异常提示
     */
    public final String defaultMessage;

    /**
     * 构造函数
     * @param code ResponseCodeInterface 异常码
     */
    public BusinessException(ResponseCodeInterface code) {
        this(code.getCode(), code.getMsg());
    }

    /**
     *  构造函数
     * @param baseResponseCode  传入的枚举类，异常码
     */
    public BusinessException(BaseResponseCode baseResponseCode){
        this(baseResponseCode.getCode(), baseResponseCode.getMsg());
    }

    /**
     * 正常的 构造函数
     * @param code 异常码
     * @param defaultMessage 异常信息
     */
    public BusinessException(int code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
