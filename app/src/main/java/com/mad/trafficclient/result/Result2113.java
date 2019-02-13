package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result2113 {
    /**
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"CarId":1,"Time":"2017-11-26 04:58:11","Cost":10}]
     * RESULT : S
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAIL> ROWSDETAIL;

    public static Result2113 objectFromData(String str) {

        return new Gson().fromJson(str, Result2113.class);
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
         * CarId : 1
         * Time : 2017-11-26 04:58:11
         * Cost : 10
         */

        @SerializedName("CarId")
        private int CarId;
        @SerializedName("Time")
        private String Time;
        @SerializedName("Cost")
        private int Cost;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public int getCarId() {
            return CarId;
        }

        public void setCarId(int CarId) {
            this.CarId = CarId;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public int getCost() {
            return Cost;
        }

        public void setCost(int Cost) {
            this.Cost = Cost;
        }
    }
}
