package com.overcloud.stat.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 创建数据表
 * 
 * @since 2014年10月16日 下午3:01:02
 * @version 1.0
 * @author admin
 */
@Repository
public interface ICreateTableDao {

    void createStatApkUpdate(Map<String, Object> map);

    void createStatBoxSwitch(Map<String, Object> map);

    void createStatGameDetailIntent(Map<String, Object> map);

    void createStatGameDownload(Map<String, Object> map);

    void createStatGameInstall(Map<String, Object> map);

    void createStatGamePageAccess(Map<String, Object> map);

    void createStatGameStartup(Map<String, Object> map);

    void createStatGameUninstall(Map<String, Object> map);
}
