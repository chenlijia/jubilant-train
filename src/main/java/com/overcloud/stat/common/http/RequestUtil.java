package com.overcloud.stat.common.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtil {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    public static void ajaxResponse(HttpServletResponse response, Object result) {
        ajaxResponse(response, com.alibaba.fastjson.JSON.toJSONString(result));
    }

    /**
     * 返回JSON
     * 
     * @param response
     * @param val
     */
    public static void ajaxResponse(HttpServletResponse response, String val) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.write(val);
            out.flush();
            out.close();
        }
        catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取真实IP
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 校验基本参数参数
     * 
     * @param request
     * @return
     */
    public static boolean validateNullBaseParam(Map<String, String> paramMap) {
        String channel = paramMap.get("channel");
        String version = paramMap.get("version");
        String pageName = paramMap.get("pageName");
        String mac = paramMap.get("mac");

        if (channel == null || version == null || pageName == null || mac == null) {
            return true;
        }
        return false;
    }

}
