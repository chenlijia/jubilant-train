package com.overcloud.stat.timetask;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.overcloud.stat.common.Global;
import com.overcloud.stat.dao.IGameDlCountDao;
import com.overcloud.stat.entity.GameDlCount;

@Component
public class ReloadGameList implements InitializingBean {

    @Resource
    IGameDlCountDao dao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        this.doSync();
    }

    private void doSync() {

        this.logger.info("[info] - 开始加载包名列表");
        List<GameDlCount> list = this.dao.queryList();
        for (GameDlCount gameDlCount : list) {
            String packageName = gameDlCount.getPackageName();
            Global.packageNames.add(packageName);
        }
        this.logger.info("[info] - 加载包名列表完毕");

    }

}
