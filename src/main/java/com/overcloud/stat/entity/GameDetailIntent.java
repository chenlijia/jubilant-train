package com.overcloud.stat.entity;

import java.util.Date;

public class GameDetailIntent extends BaseBean {

    private int id;
    private String packageName;
    private String channel;
    private String version;
    private String mac;
    private String ip;
    private String fromPage;
    private String referPage;
    private String category;
    private Date createDateTime;

    public String getCategory() {
        return this.category;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getFromPage() {
        return this.fromPage;
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

    public String getReferPage() {
        return this.referPage;
    }

    public String getVersion() {
        return this.version;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
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

    public void setReferPage(String referPage) {
        this.referPage = referPage;
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
