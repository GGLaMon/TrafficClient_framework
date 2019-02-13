package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result234 {
    /**
     * RESULT : S
     * ERRMSG : 成功
     * Status : Close
     */

    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("Status")
    private String Status;

    public static Result234 objectFromData(String str) {

        return new Gson().fromJson(str, Result234.class);
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
