package com.overcloud.stat.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String country;
    private String province;
    private String city;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
