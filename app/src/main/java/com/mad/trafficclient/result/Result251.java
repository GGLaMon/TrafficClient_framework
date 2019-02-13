package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result251 {
    /**
     * ERRMSG : 成功
     * RESULT : S
     * BusCapacity : 60
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("BusCapacity")
    private int BusCapacity;

    public static Result251 objectFromData(String str) {

        return new Gson().fromJson(str, Result251.class);
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public int getBusCapacity() {
        return BusCapacity;
    }

    public void setBusCapacity(int BusCapacity) {
        this.BusCapacity = BusCapacity;
    }
}
