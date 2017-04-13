package com.overcloud.stat.common.exception;

import com.overcloud.stat.common.consts.ReturnCode;

/**
 * 业务异常
 * 
 * @since
 * @version 1.0
 * @author
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 591856257454026393L;

    private String code;
    private String message;

    public BizException(ReturnCode rc) {
        this.code = rc.getCode();
        this.message = rc.getMessage();
    }

    public BizException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
