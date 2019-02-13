package com.mad.trafficclient.result;

import com.google.gson.Gson;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result291 {
    /**
     * ERRMSG : 成功
     * UserRole : R02
     * RESULT : S
     */

    private String ERRMSG;
    private String UserRole;
    private String RESULT;

    public static Result291 objectFromData(String str) {

        return new Gson().fromJson(str, Result291.class);
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String UserRole) {
        this.UserRole = UserRole;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }
}
