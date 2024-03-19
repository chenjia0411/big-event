package com.heima.exception;

import com.heima.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Auther: chenjia
 * @Date: 2023/11/23 - 11 - 23 - 14:50
 * @Description: com.heima.exception
 * @version: 1.0
 */
//@RestControllerAdvice用于标识当前类为REST风格对应的异常处理器
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleExceptiong(Exception e){
        e.printStackTrace();//异常信息输入到后台
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败");//

        /*StringUtils.hasLength(e.getMessage()) 是一个用于判断字符串是否有长度的方法。

StringUtils 是 Apache Commons Lang 库中提供的一个工具类，用于操作字符串的常用方法。

hasLength() 方法会判断给定的字符串参数是否不为空且长度大于零。如果字符串不为空且长度大于零，则返回 true；否则返回 false。

在上述代码中，e.getMessage() 是用于获取异常对象 e 的错误信息。通过调用 StringUtils.hasLength(e.getMessage()) 方法对异常信息进行判断，可以判断异常信息是否为空或长度是否大于零，从而进行相应的处理。*/
    }
}
