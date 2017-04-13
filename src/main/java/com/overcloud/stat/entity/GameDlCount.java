package com.overcloud.stat.entity;

import java.util.Date;

public class GameDlCount {

    private int id;
    private String packageName;
    private int downloadCount;
    private Date modifyDateTime;

    public int getDownloadCount() {
        return this.downloadCount;
    }

    public int getId() {
        return this.id;
    }

    public Date getModifyDateTime() {
        return this.modifyDateTime;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModifyDateTime(Date modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
