package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result212 {

    /**
     * Status : Stop
     * ERRMSG : 成功
     * RESULT : S
     */

    @SerializedName("Status")
    private String Status;
    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;

    public static Result212 objectFromData(String str) {

        return new Gson().fromJson(str, Result212.class);
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
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
}
