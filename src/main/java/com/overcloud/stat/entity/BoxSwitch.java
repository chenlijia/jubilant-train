package com.overcloud.stat.entity;

import java.util.Date;

@SuppressWarnings("serial")
public class BoxSwitch extends BaseBean {

    private int id;
    private String channel;
    private String mac;
    private String ip;
    private Date time;
    private short timeType;

    public int getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }

    public String getMac() {
        return this.mac;
    }

    public Date getTime() {
        return this.time;
    }

    public short getTimeType() {
        return this.timeType;
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

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTimeType(short timeType) {
        this.timeType = timeType;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}
