package com.overcloud.stat.entity;

import java.util.Date;

public class GameInstall extends BaseBean {

    private int id;
    private String packageName;
    private String channel;
    private String version;
    private String pageName;
    private String mac;
    private String ip;
    private Date installTime;
    private short insResult;
    private String reason;

    public String getChannel() {
        return this.channel;
    }

    public int getId() {
        return this.id;
    }

    public short getInsResult() {
        return this.insResult;
    }

    public Date getInstallTime() {
        return this.installTime;
    }

    public String getIp() {
        return this.ip;
    }

    public String getMac() {
        return this.mac;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getPageName() {
        return this.pageName;
    }

    public String getReason() {
        return this.reason;
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

    public void setInsResult(short insResult) {
        this.insResult = insResult;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
