package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result222 {

    /**
     * ERRMSG : 成功
     * GreenTime : 30
     * YellowTime : 5
     * RESULT : S
     * RedTime : 20
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("GreenTime")
    private int GreenTime;
    @SerializedName("YellowTime")
    private int YellowTime;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("RedTime")
    private int RedTime;

    public static Result222 objectFromData(String str) {

        return new Gson().fromJson(str, Result222.class);
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public int getGreenTime() {
        return GreenTime;
    }

    public void setGreenTime(int GreenTime) {
        this.GreenTime = GreenTime;
    }

    public int getYellowTime() {
        return YellowTime;
    }

    public void setYellowTime(int YellowTime) {
        this.YellowTime = YellowTime;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public int getRedTime() {
        return RedTime;
    }

    public void setRedTime(int RedTime) {
        this.RedTime = RedTime;
    }
}
