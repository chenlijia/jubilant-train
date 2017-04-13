package com.overcloud.stat.entity;

import java.util.Date;

public class GameDownload extends BaseBean {

    private int id;
    private String packageName;
    private String channel;
    private String version;
    private String pageName;
    private String mac;
    private String ip;
    private Date downloadTime;
    private short dlResult;
    private String reason;

    public String getChannel() {
        return this.channel;
    }

    public short getDlResult() {
        return this.dlResult;
    }

    public Date getDownloadTime() {
        return this.downloadTime;
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

    public void setDlResult(short dlResult) {
        this.dlResult = dlResult;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
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
