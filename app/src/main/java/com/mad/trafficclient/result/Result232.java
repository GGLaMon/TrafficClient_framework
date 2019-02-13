package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result232 {
    /**
     * RESULT : S
     * ERRMSG : 成功
     * ControlMode : Manual
     */

    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("ControlMode")
    private String ControlMode;

    public static Result232 objectFromData(String str) {

        return new Gson().fromJson(str, Result232.class);
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

    public String getControlMode() {
        return ControlMode;
    }

    public void setControlMode(String ControlMode) {
        this.ControlMode = ControlMode;
    }
}
