package com.overcloud.stat.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.overcloud.stat.common.DBUtil;
import com.overcloud.stat.common.DateTimeUtil;
import com.overcloud.stat.common.Global;
import com.overcloud.stat.common.SysConfig;
import com.overcloud.stat.common.consts.Constants;
import com.overcloud.stat.common.jedis.JedisConnectionPool;
import com.overcloud.stat.dao.IGameDlCountDao;
import com.overcloud.stat.dao.IGameInfoStatDao;
import com.overcloud.stat.entity.GameDetailIntent;
import com.overcloud.stat.entity.GameDownload;
import com.overcloud.stat.entity.GameInstall;
import com.overcloud.stat.entity.GamePageAccess;
import com.overcloud.stat.entity.GameStartup;
import com.overcloud.stat.entity.GameUninstall;
import com.overcloud.stat.service.IGameInfoStatService;

@Service
public class GameInfoStatServiceImpl implements IGameInfoStatService {

    private final Logger logger = LoggerFactory.getLogger(GameInfoStatServiceImpl.class);

    @Resource
    IGameInfoStatDao gameInfoStatDao;

    @Resource
    IGameDlCountDao gameDlCountDao;

    @Override
    public void download(Map<String, String> paramMap) {
        Jedis jedis = JedisConnectionPool.getInstance().getJedis(Constants.JEDIS_DATASOURCE);
        jedis.select(Constants.JEDIS_NUM_GAME_DOWNLOAD);// db0
        // 缓存起来
        this.logger.info("游戏下载缓存：" + paramMap);
        jedis.hmset(DBUtil.keyGenerator(), paramMap);

        // 判断已缓存数量
        Set<String> keys = jedis.keys("*");
        if (keys.size() > SysConfig.getInt("download.cached.number", 200)) {
            List<GameDownload> list = new ArrayList<GameDownload>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String key = it.next();
                Map<String, String> valueMap = jedis.hgetAll(key);
                GameDownload gameDownload = new GameDownload();
                gameDownload.setChannel(valueMap.get("channel"));
                String packageName = valueMap.get("packageName");// 包名
                gameDownload.setPackageName(packageName);
                Integer result = Integer.parseInt(valueMap.get("result"));
                gameDownload.setDlResult(result.shortValue());
                try {
                    gameDownload.setDownloadTime(DateTimeUtil.parseDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN,
                            valueMap.get("downloadTime")));
                }
                catch (ParseException e) {
                    this.logger.error(e.getMessage(), e);
                }
                gameDownload.setIp(valueMap.get("IP"));
                gameDownload.setMac(valueMap.get("mac"));
                gameDownload.setPageName(valueMap.get("pageName"));
                gameDownload.setReason(valueMap.get("reason"));
                gameDownload.setVersion(valueMap.get("version"));
                gameDownload.setCountry(valueMap.get("country"));
                gameDownload.setProvince(valueMap.get("province"));
                gameDownload.setCity(valueMap.get("city"));
                list.add(gameDownload);

                this.logger.debug("清空数据" + key);
                jedis.del(key);
                // 更新下载总数以便查看
                if (result.intValue() == 1) {
                    if (Global.packageNames.contains(packageName)) {// 包含，update
                        this.gameDlCountDao.edit(packageName);
                    }
                    else {// 不包括，新游戏需要insert
                        this.gameDlCountDao.add(packageName);
                        Global.packageNames.add(packageName);// 加入全局变量
                    }
                }
            }
            // 插入mysql数据库
            String suffix = DateTimeUtil.formatDateOrTime(DateTimeUtil.YEARMONTH_PATTERN);
            this.gameInfoStatDao.saveDownload(list, Constants.GAME_DOWNLOAD_TABLE.concat("_").concat(suffix));
        }
        // 返回Jedis对象
        JedisConnectionPool.getInstance().returnJedis(Constants.JEDIS_DATASOURCE, jedis);
    }

    @Override
    public void install(Map<String, String> paramMap) {
        Jedis jedis = JedisConnectionPool.getInstance().getJedis(Constants.JEDIS_DATASOURCE);
        jedis.select(Constants.JEDIS_NUM_GAME_INSTALL);// db1
        // 缓存起来
        this.logger.info("游戏安装缓存：" + paramMap);
        jedis.hmset(DBUtil.keyGenerator(), paramMap);

        // 判断已缓存数量
        Set<String> keys = jedis.keys("*");
        if (keys.size() > SysConfig.getInt("install.cached.number", 200)) {
            List<GameInstall> list = new ArrayList<GameInstall>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String key = it.next();
                Map<String, String> valueMap = jedis.hgetAll(key);
                GameInstall gameInstall = new GameInstall();
                gameInstall.setChannel(valueMap.get("channel"));
                gameInstall.setPackageName(valueMap.get("packageName"));
                gameInstall.setInsResult(Short.parseShort(valueMap.get("result")));
                try {
                    gameInstall.setInstallTime(DateTimeUtil.parseDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN,
                            valueMap.get("installTime")));
                }
                catch (ParseException e) {
                    this.logger.error(e.getMessage(), e);
                }
                gameInstall.setIp(valueMap.get("IP"));
                gameInstall.setMac(valueMap.get("mac"));
                gameInstall.setPageName(valueMap.get("pageName"));
                gameInstall.setReason(valueMap.get("reason"));
                gameInstall.setVersion(valueMap.get("version"));
                gameInstall.setCountry(valueMap.get("country"));
                gameInstall.setProvince(valueMap.get("province"));
                gameInstall.setCity(valueMap.get("city"));
                list.add(gameInstall);
                this.logger.info("清空数据" + key);
                jedis.del(key);
            }
            // 插入mysql数据库
            String suffix = DateTimeUtil.formatDateOrTime(DateTimeUtil.YEARMONTH_PATTERN);
            this.gameInfoStatDao.saveInstall(list, Constants.GAME_INSTALL_TABLE.concat("_").concat(suffix));
        }
        // 返回Jedis对象
        JedisConnectionPool.getInstance().returnJedis(Constants.JEDIS_DATASOURCE, jedis);
    }

    @Override
    public void intent(Map<String, String> paramMap) {
        Jedis jedis = JedisConnectionPool.getInstance().getJedis(Constants.JEDIS_DATASOURCE);
        jedis.select(Constants.JEDIS_NUM_GAME_INTENT);// db4
        // 缓存起来
        this.logger.info("游戏详情页跳转缓存：" + paramMap);
        jedis.hmset(DBUtil.keyGenerator(), paramMap);

        // 判断已缓存数量
        Set<String> keys = jedis.keys("*");
        if (keys.size() > SysConfig.getInt("intent.cached.number", 200)) {
            List<GameDetailIntent> list = new ArrayList<GameDetailIntent>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String key = it.next();
                Map<String, String> valueMap = jedis.hgetAll(key);
                GameDetailIntent gameDetailIntent = new GameDetailIntent();
                gameDetailIntent.setPackageName(valueMap.get("packageName"));// 包名
                gameDetailIntent.setChannel(valueMap.get("channel"));
                gameDetailIntent.setIp(valueMap.get("IP"));
                gameDetailIntent.setMac(valueMap.get("mac"));
                gameDetailIntent.setVersion(valueMap.get("version"));
                gameDetailIntent.setCountry(valueMap.get("country"));
                gameDetailIntent.setProvince(valueMap.get("province"));
                gameDetailIntent.setCity(valueMap.get("city"));
                gameDetailIntent.setFromPage(valueMap.get("from"));
                gameDetailIntent.setReferPage(valueMap.get("refer"));
                gameDetailIntent.setCategory(valueMap.get("category"));// 分类
                try {
                    gameDetailIntent.setCreateDateTime(DateTimeUtil.parseDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN,
                            valueMap.get("createDateTime")));
                }
                catch (ParseException e) {
                    this.logger.error(e.getMessage(), e);
                }

                list.add(gameDetailIntent);
                this.logger.debug("清空数据" + key);
                jedis.del(key);
            }
            // 插入mysql数据库
            String suffix = DateTimeUtil.formatDateOrTime(DateTimeUtil.YEARMONTH_PATTERN);
            this.gameInfoStatDao.saveDetailIntent(list, Constants.GAME_DETAIL_INTENT_TABLE.concat("_").concat(suffix));
        }
        // 返回Jedis对象
        JedisConnectionPool.getInstance().returnJedis(Constants.JEDIS_DATASOURCE, jedis);
    }

    @Override
    public void pageAccess(Map<String, String> paramMap) {
        Jedis jedis = JedisConnectionPool.getInstance().getJedis(Constants.JEDIS_DATASOURCE);
        jedis.select(Constants.JEDIS_NUM_GAME_PAGE);// db5
        // 缓存起来
        this.logger.info("游戏页面访问缓存：" + paramMap);
        jedis.hmset(DBUtil.keyGenerator(), paramMap);

        // 判断已缓存数量
        Set<String> keys = jedis.keys("*");
        if (keys.size() > SysConfig.getInt("page.cached.number", 200)) {
            List<GamePageAccess> list = new ArrayList<GamePageAccess>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String key = it.next();
                Map<String, String> valueMap = jedis.hgetAll(key);
                GamePageAccess gamePageAccess = new GamePageAccess();
                gamePageAccess.setChannel(valueMap.get("channel"));
                gamePageAccess.setIp(valueMap.get("IP"));
                gamePageAccess.setMac(valueMap.get("mac"));
                gamePageAccess.setPageName(valueMap.get("pageName"));
                gamePageAccess.setPackageName(valueMap.get("packageName"));
                gamePageAccess.setVersion(valueMap.get("version"));
                gamePageAccess.setCountry(valueMap.get("country"));
                gamePageAccess.setProvince(valueMap.get("province"));
                gamePageAccess.setCity(valueMap.get("city"));
                gamePageAccess.setMs(valueMap.get("ms"));// 毫秒数
                gamePageAccess.setCategory(valueMap.get("category"));
                try {
                    /*
                     * if (valueMap.get("in") != null && valueMap.get("out") !=
                     * null) {
                     * gamePageAccess.setEnterTime(DateTimeUtil.parseDateOrTime
                     * (DateTimeUtil.TIME_STANDARDPATTERN, valueMap.get("in")));
                     * gamePageAccess
                     * .setLeaveTime(DateTimeUtil.parseDateOrTime(DateTimeUtil
                     * .TIME_STANDARDPATTERN, valueMap.get("out"))); }
                     */
                    gamePageAccess.setCreateDateTime(DateTimeUtil.parseDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN,
                            valueMap.get("createDateTime")));
                }
                catch (ParseException e) {
                    this.logger.error(e.getMessage(), e);
                }

                list.add(gamePageAccess);
                this.logger.info("清空数据" + key);
                jedis.del(key);
            }
            // 插入mysql数据库
            String suffix = DateTimeUtil.formatDateOrTime(DateTimeUtil.YEARMONTH_PATTERN);
            this.gameInfoStatDao.savePageAccess(list, Constants.GAME_PAGE_ACCESS_TABLE.concat("_").concat(suffix));
        }
        // 返回Jedis对象
        JedisConnectionPool.getInstance().returnJedis(Constants.JEDIS_DATASOURCE, jedis);
    }

    @Override
    public void startup(Map<String, String> paramMap) {
        Jedis jedis = JedisConnectionPool.getInstance().getJedis(Constants.JEDIS_DATASOURCE);
        jedis.select(Constants.JEDIS_NUM_GAME_STARTUP);// db2
        // 缓存起来
        this.logger.info("游戏启动缓存：" + paramMap);
        jedis.hmset(DBUtil.keyGenerator(), paramMap);

        // 判断已缓存数量
        Set<String> keys = jedis.keys("*");
        if (keys.size() > SysConfig.getInt("startup.cached.number", 200)) {
            List<GameStartup> list = new ArrayList<GameStartup>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String key = it.next();
                Map<String, String> valueMap = jedis.hgetAll(key);
                GameStartup gameStartup = new GameStartup();
                gameStartup.setChannel(valueMap.get("channel"));
                gameStartup.setPackageName(valueMap.get("packageName"));
                gameStartup.setMs(valueMap.get("ms"));// 毫秒数
                gameStartup.setIp(valueMap.get("IP"));
                gameStartup.setMac(valueMap.get("mac"));
                gameStartup.setPageName(valueMap.get("pageName"));
                gameStartup.setVersion(valueMap.get("version"));
                gameStartup.setCountry(valueMap.get("country"));
                gameStartup.setProvince(valueMap.get("province"));
                gameStartup.setCity(valueMap.get("city"));
                gameStartup.setCreateDateTime(new Date());// 记录当前时间
                try {
                    gameStartup.setCreateDateTime(DateTimeUtil.parseDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN,
                            valueMap.get("createDateTime")));
                }
                catch (ParseException e) {
                    this.logger.error(e.getMessage(), e);
                }
                list.add(gameStartup);
                this.logger.info("清空数据" + key);
                jedis.del(key);
            }
            // 插入mysql数据库
            String suffix = DateTimeUtil.formatDateOrTime(DateTimeUtil.YEARMONTH_PATTERN);
            this.gameInfoStatDao.saveStartup(list, Constants.GAME_STARTUP_TABLE.concat("_").concat(suffix));
        }
        // 返回Jedis对象
        JedisConnectionPool.getInstance().returnJedis(Constants.JEDIS_DATASOURCE, jedis);
    }

    @Override
    public void uninstall(Map<String, String> paramMap) {
        Jedis jedis = JedisConnectionPool.getInstance().getJedis(Constants.JEDIS_DATASOURCE);
        jedis.select(Constants.JEDIS_NUM_GAME_UNINSTALL);// db3
        // 缓存起来
        this.logger.info("游戏卸载缓存：" + paramMap);
        jedis.hmset(DBUtil.keyGenerator(), paramMap);

        // 判断已缓存数量
        Set<String> keys = jedis.keys("*");
        if (keys.size() > SysConfig.getInt("uninstall.cached.number", 200)) {
            List<GameUninstall> list = new ArrayList<GameUninstall>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String key = it.next();
                Map<String, String> valueMap = jedis.hgetAll(key);
                GameUninstall gameUninstall = new GameUninstall();
                gameUninstall.setChannel(valueMap.get("channel"));
                gameUninstall.setPackageName(valueMap.get("packageName"));
                try {
                    gameUninstall.setUninstallTime(DateTimeUtil.parseDateOrTime(DateTimeUtil.TIME_STANDARDPATTERN,
                            valueMap.get("uninstallTime")));
                }
                catch (ParseException e) {
                    this.logger.error(e.getMessage(), e);
                }
                gameUninstall.setIp(valueMap.get("IP"));
                gameUninstall.setMac(valueMap.get("mac"));
                gameUninstall.setVersion(valueMap.get("version"));
                gameUninstall.setCountry(valueMap.get("country"));
                gameUninstall.setProvince(valueMap.get("province"));
                gameUninstall.setCity(valueMap.get("city"));
                list.add(gameUninstall);
                this.logger.info("清空数据" + key);
                jedis.del(key);
            }
            // 插入mysql数据库
            String suffix = DateTimeUtil.formatDateOrTime(DateTimeUtil.YEARMONTH_PATTERN);
            this.gameInfoStatDao.saveUninstall(list, Constants.GAME_UNINSTALL_TABLE.concat("_").concat(suffix));
        }
        // 返回Jedis对象
        JedisConnectionPool.getInstance().returnJedis(Constants.JEDIS_DATASOURCE, jedis);
    }
}
