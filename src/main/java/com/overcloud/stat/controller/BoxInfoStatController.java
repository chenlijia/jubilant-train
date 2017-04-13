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
import com.overcloud.stat.service.IBoxInfoStatService;

@RestController
@RequestMapping("/stat/box")
public class BoxInfoStatController {

    private final Logger logger = LoggerFactory.getLogger(BoxInfoStatController.class);

    @Resource
    private IBoxInfoStatService boxInfoStatService;

    @RequestMapping(value = "/switch", method = RequestMethod.POST)
    public ViewResult detailPage(HttpServletResponse response, HttpServletRequest request) {
        ViewResult vr = null;

        Map<String, String> paramMap = WebParameterUtils.getParameterMap(request);
        if (paramMap.get("channel") == null || paramMap.get("mac") == null || paramMap.get("timeType") == null) {// 参数错误
            this.logger.error("参数错误");
            vr = new ViewResult(ReturnCode.COMMON_ERR_PARM.getCode(), ReturnCode.COMMON_ERR_PARM.getMessage());
        }
        else {
            String ipAddr = RequestUtil.getIpAddr(request);
            DBUtil.setIpParam(paramMap, ipAddr);
            paramMap.put("time", DateTimeUtil.formatDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN));
            this.boxInfoStatService.useInfo(paramMap);
            vr = new ViewResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMessage());
        }
        return vr;
    }
}
