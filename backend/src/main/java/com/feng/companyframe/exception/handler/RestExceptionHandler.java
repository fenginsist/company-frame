package com.feng.companyframe.exception.handler;

import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @ClassName: RestExceptionHandler
 * @Description： 全局异常 统一处理类
 * @createTime: 2020/2/1 21:30
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 全局处理 Exception 异常
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public <T> DataResult<T> handleException(Exception e) {
        log.error("Exception, exception:{},exception:{}", e.getLocalizedMessage(), e);
        return DataResult.getResult(BaseResponseCode.SYSTEM_ERROR);
    }

    /**
     * 全局处理 RuntimeException 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public DataResult businessExceptionHandler(BusinessException e) {
        log.error("BusinessException,exception:{},exception:{}", e.getDefaultMessage(), e);
        return new DataResult(e.getCode(), e.getDefaultMessage());
    }

    /**
     * 全局处理 @Valid开启的 异常
     * 在 Javabean 属性上的注解
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public DataResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}",
                e.getBindingResult().getAllErrors(), e);

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return createVaildExceptionResp(errors);
    }

    private DataResult createVaildExceptionResp(List<ObjectError> errors) {
        String[] msgs = new String[errors.size()];
        int i = 0;
        for (ObjectError error : errors) {
            msgs[i] = error.getDefaultMessage();
            log.info("msg={}", msgs[i]);
            i++;
        }
        return DataResult.getResult(BaseResponseCode.METHOD_IDENTITY_ERROR.getCode(), msgs[0]);
    }

    /**
     * 没有权限 异常 捕获
     * @RequiresPermissions("sys:user:role:update") 这个注解所爆出的异常 UnauthorizedException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public DataResult unauthorizedException(UnauthorizedException e) {
        log.error("UnauthorizedException:{},exception:{}", e.getLocalizedMessage(), e);
        return DataResult.getResult(BaseResponseCode.NOT_PERMISSION);
    }
}

