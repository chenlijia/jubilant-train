package com.overcloud.stat.entity;

import java.util.Date;

public class GameStartup extends BaseBean {

    private int id;
    private String packageName;
    private String channel;
    private String version;
    private String pageName;
    private String mac;
    private String ip;
    private String ms;
    private Date createDateTime;

    public String getChannel() {
        return this.channel;
    }

    public int getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }

    public String getMac() {
        return this.mac;
    }

    public String getMs() {
        return this.ms;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getPageName() {
        return this.pageName;
    }

    public String getVersion() {
        return this.version;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getCreateDateTime() {
        return this.createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}
