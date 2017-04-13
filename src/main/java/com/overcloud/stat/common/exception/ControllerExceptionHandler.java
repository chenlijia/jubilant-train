package com.overcloud.stat.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.overcloud.stat.common.consts.ReturnCode;
import com.overcloud.stat.common.vo.ViewResult;

/**
 * 异常拦截处理
 * 
 * @since
 * @version 1.0
 * @author
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 处理BizException.
     */
    @ExceptionHandler(value = { BizException.class })
    public final ResponseEntity<?> handleBizException(BizException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
        this.logger.error(ex.getMessage(), ex);
        ViewResult vr = new ViewResult(ex.getCode(), ex.getMessage());
        return this.handleExceptionInternal(ex, vr, headers, HttpStatus.OK, request);
    }

    /**
     * 处理Exception.
     */
    @ExceptionHandler(value = { Exception.class })
    public final ResponseEntity<?> handleDefaultException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
        this.logger.error(ex.getMessage(), ex);
        ViewResult vr = new ViewResult(ReturnCode.COMMON_ERR_SERVER.getCode(),
                ReturnCode.COMMON_ERR_SERVER.getMessage());
        return this.handleExceptionInternal(ex, vr, headers, HttpStatus.OK, request);
    }
}
