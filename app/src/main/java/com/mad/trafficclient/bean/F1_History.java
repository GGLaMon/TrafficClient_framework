package com.mad.trafficclient.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class F1_History {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String carId;
    @DatabaseField
    private int money;
    @DatabaseField
    private String userName;
    @DatabaseField
    private Long time;
    public F1_History() {
    }
    public F1_History(String carId, int money, String userName, Long time) {
        this.carId = carId;
        this.money = money;
        this.userName = userName;
        this.time = time;
    }
    public String getCarId() {
        return carId;
    }
    public void setCarId(String carId) {
        this.carId = carId;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getTime() {
        return time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
}
