package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result241 {
    /**
     * RESULT : S
     * ERRMSG : 成功
     * pm2.5 : 8
     * co2 : 5919
     * LightIntensity : 1711
     * humidity : 44
     * temperature : 28
     */

    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("pm2.5")
    private int _$Pm25159; // FIXME check this code
    @SerializedName("co2")
    private int co2;
    @SerializedName("LightIntensity")
    private int LightIntensity;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("temperature")
    private int temperature;

    public static Result241 objectFromData(String str) {

        return new Gson().fromJson(str, Result241.class);
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public int get_$Pm25159() {
        return _$Pm25159;
    }

    public void set_$Pm25159(int _$Pm25159) {
        this._$Pm25159 = _$Pm25159;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getLightIntensity() {
        return LightIntensity;
    }

    public void setLightIntensity(int LightIntensity) {
        this.LightIntensity = LightIntensity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
