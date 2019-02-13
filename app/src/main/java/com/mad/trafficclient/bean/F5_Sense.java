package com.mad.trafficclient.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class F5_Sense {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private int value;
    @DatabaseField
    private int type;// 1温度 2湿度 3光照 4CO2 5PM2.5 6道路
    @DatabaseField(defaultValue = "0")
    private int yuzhi;
    @DatabaseField
    private int normal=1;//1为正常 0为异常
    @DatabaseField
    private long time;
    public F5_Sense() {
    }
    public F5_Sense(int value, int type, int yuzhi, int normal, long time) {
        this.value = value;
        this.type = type;
        this.yuzhi = yuzhi;
        this.normal = normal;
        this.time = time;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getYuzhi() {
        return yuzhi;
    }
    public void setYuzhi(int yuzhi) {
        this.yuzhi = yuzhi;
    }
    public int getNormal() {
        return normal;
    }
    public void setNormal(int normal) {
        this.normal = normal;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
}