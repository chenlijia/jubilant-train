package com.overcloud.stat.common.consts;

public interface Constants {

    public static String JEDIS_DATASOURCE = "redis";

    // redis数据库索引
    public static int JEDIS_NUM_GAME_DOWNLOAD = 0;
    public static int JEDIS_NUM_GAME_INSTALL = 1;
    public static int JEDIS_NUM_GAME_STARTUP = 2;
    public static int JEDIS_NUM_GAME_UNINSTALL = 3;
    public static int JEDIS_NUM_GAME_INTENT = 4;
    public static int JEDIS_NUM_GAME_PAGE = 5;
    public static int JEDIS_NUM_BOX_SWITCH = 6;

    /**
     * 游戏下载表
     */
    public static String GAME_DOWNLOAD_TABLE = "oc_stat_game_download";
    /**
     * 游戏安装表
     */
    public static String GAME_INSTALL_TABLE = "oc_stat_game_install";
    /**
     * 游戏启动表
     */
    public static String GAME_STARTUP_TABLE = "oc_stat_game_startup";
    /**
     * 游戏卸载表
     */
    public static String GAME_UNINSTALL_TABLE = "oc_stat_game_uninstall";
    /**
     * 游戏详情页面跳转表
     */
    public static String GAME_DETAIL_INTENT_TABLE = "oc_stat_gamedetail_intent";
    /**
     * 游戏页面访问表
     */
    public static String GAME_PAGE_ACCESS_TABLE = "oc_stat_gamepage_access";
    /**
     * 盒子开关机表
     */
    public static String BOX_SWITCH_TABLE = "oc_stat_box_switch";
    /**
     * 记录各个版本的更新表
     */
    public static String APK_UPDATE_TABLE = "oc_stat_apk_update";
}
