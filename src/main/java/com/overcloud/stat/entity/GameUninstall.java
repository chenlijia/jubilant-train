package com.overcloud.stat.entity;

import java.util.Date;

public class GameUninstall extends BaseBean {

    private int id;
    private String packageName;
    private String channel;
    private String version;
    private String mac;
    private String ip;
    private Date uninstallTime;

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

    public String getPackageName() {
        return this.packageName;
    }

    public Date getUninstallTime() {
        return this.uninstallTime;
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

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setUninstallTime(Date uninstallTime) {
        this.uninstallTime = uninstallTime;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
