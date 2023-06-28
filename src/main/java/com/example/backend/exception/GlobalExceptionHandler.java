package com.example.backend.exception;

import com.example.backend.entity.ResultInfo;
import com.example.backend.utils.ResultInfoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常处理
     * */
    @ExceptionHandler(value = OSException.class)
    @ResponseBody
    public ResultInfo OSExceptionHandler(HttpServletRequest request, OSException e) {
        logger.error("业务异常，异常码是：{}，原因是：{}", e.getErrorCode(), e.getErrorMsg());
        return ResultInfoUtils.error(e.getErrorCode(), e.getErrorMsg());
    }

}
