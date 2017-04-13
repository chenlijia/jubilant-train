package com.overcloud.stat.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.overcloud.stat.common.consts.Constants;
import com.overcloud.stat.dao.ICreateTableDao;
import com.overcloud.stat.service.ICreateTableService;

@Service
public class CreateTableServiceImpl implements ICreateTableService {

    private final Logger logger = LoggerFactory.getLogger(GameInfoStatServiceImpl.class);

    @Resource
    ICreateTableDao createTableDao;

    @Override
    public void doCreateTable(String suffix) {
        // 获取下个月日期, 格式为yyMM

        Map<String, Object> map = new HashMap<String, Object>();

        this.logger.info("盒子开关机统计表创建...");
        map.put("BoxSwitchTable", Constants.BOX_SWITCH_TABLE.concat("_").concat(suffix));
        this.createTableDao.createStatBoxSwitch(map);
        this.logger.info("盒子开关机统计表创建成功");

        this.logger.info("游戏下载统计表创建...");
        map.put("GameDownloadTable", Constants.GAME_DOWNLOAD_TABLE.concat("_").concat(suffix));
        this.createTableDao.createStatGameDownload(map);
        this.logger.info("游戏下载统计表创建成功");

        this.logger.info("游戏安装统计表创建...");
        map.put("GameInstallTable", Constants.GAME_INSTALL_TABLE.concat("_").concat(suffix));
        this.createTableDao.createStatGameInstall(map);
        this.logger.info("游戏安装统计表创建成功");

        this.logger.info("游戏启动统计表创建...");
        map.put("GameStartupTable", Constants.GAME_STARTUP_TABLE.concat("_").concat(suffix));
        this.createTableDao.createStatGameStartup(map);
        this.logger.info("游戏启动统计表创建成功");

        this.logger.info("游戏卸载统计表创建...");
        map.put("GameUninstallTable", Constants.GAME_UNINSTALL_TABLE.concat("_").concat(suffix));
        this.createTableDao.createStatGameUninstall(map);
        this.logger.info("游戏卸载统计表创建成功");

        this.logger.info("游戏页面访问统计表创建...");
        map.put("GamePageAccessTable", Constants.GAME_PAGE_ACCESS_TABLE.concat("_").concat(suffix));
        this.createTableDao.createStatGamePageAccess(map);
        this.logger.info("游戏页面访问统计表创建成功");

        this.logger.info("游戏详情页面跳转统计表创建...");
        map.put("GameDetailIntentTable", Constants.GAME_DETAIL_INTENT_TABLE.concat("_").concat(suffix));
        this.createTableDao.createStatGameDetailIntent(map);
        this.logger.info("游戏详情页面跳转统计表创建成功");

        this.logger.info("记录各个版本的更新统计表创建...");
        map.put("ApkUpdateTable", Constants.APK_UPDATE_TABLE.concat("_").concat(suffix));
        this.createTableDao.createStatApkUpdate(map);
        this.logger.info("记录各个版本的更新统计表创建成功");
    }
}
