package com.overcloud.stat.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.overcloud.stat.common.DBUtil;
import com.overcloud.stat.common.DateTimeUtil;
import com.overcloud.stat.common.consts.ReturnCode;
import com.overcloud.stat.common.http.RequestUtil;
import com.overcloud.stat.common.http.WebParameterUtils;
import com.overcloud.stat.common.vo.ViewResult;
import com.overcloud.stat.service.ICreateTableService;
import com.overcloud.stat.service.IGameInfoStatService;

@RestController
@RequestMapping("/stat/game")
public class GameInfoStatController {

    private final Logger logger = LoggerFactory.getLogger(GameInfoStatController.class);

    @Resource
    private ICreateTableService creatTableService;

    @Resource
    private IGameInfoStatService gameInfoStatService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ViewResult create(HttpServletResponse response, HttpServletRequest request) {
        String suffix = DateTimeUtil.formatDateOrTime(DateTimeUtil.YEARMONTH_PATTERN);
        this.creatTableService.doCreateTable(suffix);
        ViewResult vr = new ViewResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMessage());
        return vr;
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ViewResult download(HttpServletResponse response, HttpServletRequest request) {
        ViewResult vr = null;

        Map<String, String> paramMap = WebParameterUtils.getParameterMap(request);
        if (RequestUtil.validateNullBaseParam(paramMap) || paramMap.get("packageName") == null) {// 参数错误
            this.logger.error("游戏下载参数错误");
            vr = new ViewResult(ReturnCode.COMMON_ERR_PARM.getCode(), ReturnCode.COMMON_ERR_PARM.getMessage());
        }
        else {
            String ipAddr = RequestUtil.getIpAddr(request);
            DBUtil.setIpParam(paramMap, ipAddr);
            paramMap.put("downloadTime", DateTimeUtil.formatDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN));
            paramMap.put("reason", paramMap.get("reason") == null ? "" : paramMap.get("reason"));
            paramMap.put("result", paramMap.get("result") == null ? "1" : paramMap.get("result"));
            this.gameInfoStatService.download(paramMap);
            vr = new ViewResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMessage());
        }
        return vr;
    }

    @RequestMapping(value = "/install", method = RequestMethod.POST)
    public ViewResult install(HttpServletResponse response, HttpServletRequest request) {
        ViewResult vr = null;

        Map<String, String> paramMap = WebParameterUtils.getParameterMap(request);
        if (RequestUtil.validateNullBaseParam(paramMap) || paramMap.get("packageName") == null) {// 参数错误
            this.logger.error("游戏安装参数错误");
            vr = new ViewResult(ReturnCode.COMMON_ERR_PARM.getCode(), ReturnCode.COMMON_ERR_PARM.getMessage());
        }
        else {
            String ipAddr = RequestUtil.getIpAddr(request);
            DBUtil.setIpParam(paramMap, ipAddr);
            paramMap.put("installTime", DateTimeUtil.formatDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN));
            paramMap.put("reason", paramMap.get("reason") == null ? "" : paramMap.get("reason"));
            paramMap.put("result", paramMap.get("result") == null ? "1" : paramMap.get("result"));
            this.gameInfoStatService.install(paramMap);
            vr = new ViewResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMessage());
        }
        return vr;
    }

    @RequestMapping(value = "/intent", method = RequestMethod.POST)
    public ViewResult intent(HttpServletResponse response, HttpServletRequest request) {
        ViewResult vr = null;

        Map<String, String> paramMap = WebParameterUtils.getParameterMap(request);
        if (paramMap.get("channel") == null || paramMap.get("version") == null || paramMap.get("mac") == null
                || paramMap.get("from") == null || paramMap.get("refer") == null) {// 参数错误
            this.logger.error("游戏详情页跳转参数错误");
            vr = new ViewResult(ReturnCode.COMMON_ERR_PARM.getCode(), ReturnCode.COMMON_ERR_PARM.getMessage());
        }
        else {
            String ipAddr = RequestUtil.getIpAddr(request);
            DBUtil.setIpParam(paramMap, ipAddr);
            paramMap.put("createDateTime", DateTimeUtil.formatDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN));
            this.gameInfoStatService.intent(paramMap);
            vr = new ViewResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMessage());
        }
        return vr;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ViewResult page(HttpServletResponse response, HttpServletRequest request) {
        ViewResult vr = null;

        Map<String, String> paramMap = WebParameterUtils.getParameterMap(request);
        if (RequestUtil.validateNullBaseParam(paramMap) || paramMap.get("ms") == null) {// 参数错误
            this.logger.error("游戏页面访问参数错误");
            vr = new ViewResult(ReturnCode.COMMON_ERR_PARM.getCode(), ReturnCode.COMMON_ERR_PARM.getMessage());
        }
        else {
            String ipAddr = RequestUtil.getIpAddr(request);
            DBUtil.setIpParam(paramMap, ipAddr);
            paramMap.put("createDateTime", DateTimeUtil.formatDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN));
            this.gameInfoStatService.pageAccess(paramMap);
            vr = new ViewResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMessage());
        }
        return vr;
    }

    @RequestMapping(value = "/startup", method = RequestMethod.POST)
    public ViewResult startup(HttpServletResponse response, HttpServletRequest request) {
        ViewResult vr = null;

        Map<String, String> paramMap = WebParameterUtils.getParameterMap(request);
        if (RequestUtil.validateNullBaseParam(paramMap) || paramMap.get("packageName") == null
                || paramMap.get("ms") == null) {// 参数错误
            this.logger.error("游戏启动参数错误:" + paramMap);
            vr = new ViewResult(ReturnCode.COMMON_ERR_PARM.getCode(), ReturnCode.COMMON_ERR_PARM.getMessage());
        }
        else {
            int ms = Integer.parseInt(paramMap.get("ms"));
            if (ms > 5 * 60 * 60 * 1000) {// 大于5小时的视为问题数据
                this.logger.error("游戏时长参数错误：" + ms);
                vr = new ViewResult(ReturnCode.COMMON_ERR_PARM.getCode(), ReturnCode.COMMON_ERR_PARM.getMessage());
            }
            else {
                String ipAddr = RequestUtil.getIpAddr(request);
                DBUtil.setIpParam(paramMap, ipAddr);
                paramMap.put("createDateTime", DateTimeUtil.formatDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN));
                this.gameInfoStatService.startup(paramMap);
                vr = new ViewResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMessage());
            }
        }
        return vr;
    }

    @RequestMapping(value = "/uninstall", method = RequestMethod.POST)
    public ViewResult uninstall(HttpServletResponse response, HttpServletRequest request) {
        ViewResult vr = null;

        Map<String, String> paramMap = WebParameterUtils.getParameterMap(request);
        if (RequestUtil.validateNullBaseParam(paramMap) || paramMap.get("packageName") == null) {// 参数错误
            this.logger.error("游戏卸载参数错误");
            vr = new ViewResult(ReturnCode.COMMON_ERR_PARM.getCode(), ReturnCode.COMMON_ERR_PARM.getMessage());
        }
        else {
            String ipAddr = RequestUtil.getIpAddr(request);
            DBUtil.setIpParam(paramMap, ipAddr);
            paramMap.put("uninstallTime", DateTimeUtil.formatDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN));
            this.gameInfoStatService.uninstall(paramMap);
            vr = new ViewResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMessage());
        }
        return vr;
    }
}
