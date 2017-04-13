package com.overcloud.stat.entity;

import java.util.Date;

public class GamePageAccess extends BaseBean {

    private int id;
    private String channel;
    private String version;
    private String pageName;
    private String packageName;
    private String mac;
    private String ip;
    private Date enterTime;
    private Date leaveTime;
    private String ms;
    private Date createDateTime;
    private String category;

    public String getChannel() {
        return this.channel;
    }

    public Date getCreateDateTime() {
        return this.createDateTime;
    }

    public Date getEnterTime() {
        return this.enterTime;
    }

    public int getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }

    public Date getLeaveTime() {
        return this.leaveTime;
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

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
