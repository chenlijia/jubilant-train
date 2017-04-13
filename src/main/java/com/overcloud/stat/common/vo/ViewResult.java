package com.overcloud.stat.common.vo;

/**
 * 展现结果
 * 
 * @since
 * @version 1.0
 * @author
 */
public class ViewResult {

    // 数据
    private Object data;

    // 结果消息
    private String message;

    // 结果码
    private String code;

    public ViewResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ViewResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public Object getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
