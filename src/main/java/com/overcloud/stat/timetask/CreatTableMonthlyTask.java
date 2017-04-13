package com.overcloud.stat.timetask;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.overcloud.stat.common.DateTimeUtil;
import com.overcloud.stat.service.ICreateTableService;

@Component
public class CreatTableMonthlyTask {

    private static Logger logger = LoggerFactory.getLogger(CreatTableMonthlyTask.class);

    @Resource
    private ICreateTableService createTableService;

    /**
     * 定时创建表 每月20日凌晨2点执行一次
     */
    @Scheduled(cron = "0 0 2 20 * ?")
    public void createTable() {
        try {
            String suffix = DateTimeUtil.getNextMonth(DateTimeUtil.YEARMONTH_PATTERN);
            this.createTableService.doCreateTable(suffix);
            logger.info("创建统计基础表执行成功");
        }
        catch (Exception e) {
            logger.error("创建统计基础表执行失败", e);
        }
    }
}
