package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result2101 {
    /**
     * RESULT : S
     * ERRMSG : 查询成功
     * WCurrent : 19
     * ROWS_DETAIL : [{"WData":"2017-06-06","temperature":"14~ 22"}]
     */

    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("WCurrent")
    private String WCurrent;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAIL> ROWSDETAIL;

    public static Result2101 objectFromData(String str) {

        return new Gson().fromJson(str, Result2101.class);
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

    public String getWCurrent() {
        return WCurrent;
    }

    public void setWCurrent(String WCurrent) {
        this.WCurrent = WCurrent;
    }

    public List<ROWSDETAIL> getROWSDETAIL() {
        return ROWSDETAIL;
    }

    public void setROWSDETAIL(List<ROWSDETAIL> ROWSDETAIL) {
        this.ROWSDETAIL = ROWSDETAIL;
    }

    public static class ROWSDETAIL {
        /**
         * WData : 2017-06-06
         * temperature : 14~ 22
         */

        @SerializedName("WData")
        private String WData;
        @SerializedName("temperature")
        private String temperature;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public String getWData() {
            return WData;
        }

        public void setWData(String WData) {
            this.WData = WData;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }
    }
}
