package com.overcloud.stat.common.exception;

import com.overcloud.stat.common.consts.ReturnCode;

/**
 * 异常处理工具
 * 
 * @since
 * @version 1.0
 * @author
 */
public class ExceptionUtil {

    public static void wrapBizException(ReturnCode rc) {
        throw new BizException(rc);
    }

    public static void wrapBizException(String code, String message) {
        throw new BizException(code, message);
    }
}
