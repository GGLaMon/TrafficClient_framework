package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result213 {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * Balance : 2788
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("Balance")
    private int Balance;

    public static Result213 objectFromData(String str) {

        return new Gson().fromJson(str, Result213.class);
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

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int Balance) {
        this.Balance = Balance;
    }
}
