package com.overcloud.stat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.overcloud.stat.entity.GameDetailIntent;
import com.overcloud.stat.entity.GameDownload;
import com.overcloud.stat.entity.GameInstall;
import com.overcloud.stat.entity.GamePageAccess;
import com.overcloud.stat.entity.GameStartup;
import com.overcloud.stat.entity.GameUninstall;

@Repository
public interface IGameInfoStatDao {

    void saveDetailIntent(List<GameDetailIntent> list, String tableName);

    void saveDownload(List<GameDownload> list, String tableName);

    void saveInstall(List<GameInstall> list, String tableName);

    void savePageAccess(List<GamePageAccess> list, String tableName);

    void saveStartup(List<GameStartup> list, String tableName);

    void saveUninstall(List<GameUninstall> list, String tableName);
}
