package com.mad.trafficclient.bean;

public class F16_carInfo {
    private String carBrand;
    private String carNum;

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public F16_carInfo(String carBrand, String carNum) {

        this.carBrand = carBrand;
        this.carNum = carNum;
    }
}