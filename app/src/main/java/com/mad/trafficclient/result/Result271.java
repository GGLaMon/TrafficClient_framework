package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result271 {
    /**
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"BusId":1,"Distance":92110},{"BusId":2,"Distance":27755},{"BusId":3,"Distance":103346},"\u2026\u2026",{"BusId":8,"Distance":106834},{"BusId":9,"Distance":83920},{"BusId":10,"Distance":107654}]
     * RESULT : S
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAIL> ROWSDETAIL;

    public static Result271 objectFromData(String str) {

        return new Gson().fromJson(str, Result271.class);
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

    public List<ROWSDETAIL> getROWSDETAIL() {
        return ROWSDETAIL;
    }

    public void setROWSDETAIL(List<ROWSDETAIL> ROWSDETAIL) {
        this.ROWSDETAIL = ROWSDETAIL;
    }

    public static class ROWSDETAIL {
        /**
         * BusId : 1
         * Distance : 92110
         */

        @SerializedName("BusId")
        private int BusId;
        @SerializedName("Distance")
        private int Distance;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public int getBusId() {
            return BusId;
        }

        public void setBusId(int BusId) {
            this.BusId = BusId;
        }

        public int getDistance() {
            return Distance;
        }

        public void setDistance(int Distance) {
            this.Distance = Distance;
        }
    }
}
