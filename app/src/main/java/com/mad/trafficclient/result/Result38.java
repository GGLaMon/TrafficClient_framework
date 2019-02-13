package com.mad.trafficclient.result;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result38 {

    /**
     * RESULT : S
     * ERRMSG : 查询成功
     * ROWS_DETAIL : [{"Number":"M101","Route":"信息学院\u2014\u2014科技园总站","Fare":8,"Mileage":40,"GoTime":"06:30","BackTime":"18:45"}]
     */

    private String RESULT;
    private String ERRMSG;
    private List<ROWSDETAIL> ROWS_DETAIL;

    public static Result38 objectFromData(String str) {

        return new Gson().fromJson(str, Result38.class);
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

    public List<ROWSDETAIL> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAIL> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAIL {
        /**
         * Number : M101
         * Route : 信息学院——科技园总站
         * Fare : 8.0
         * Mileage : 40.0
         * GoTime : 06:30
         * BackTime : 18:45
         */

        private String Number;
        private String Route;
        private float Fare;
        private float Mileage;
        private String GoTime;
        private String BackTime;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public String getNumber() {
            return Number;
        }

        public void setNumber(String Number) {
            this.Number = Number;
        }

        public String getRoute() {
            return Route;
        }

        public void setRoute(String Route) {
            this.Route = Route;
        }

        public float getFare() {
            return Fare;
        }

        public void setFare(float Fare) {
            this.Fare = Fare;
        }

        public float getMileage() {
            return Mileage;
        }

        public void setMileage(float Mileage) {
            this.Mileage = Mileage;
        }

        public String getGoTime() {
            return GoTime;
        }

        public void setGoTime(String GoTime) {
            this.GoTime = GoTime;
        }

        public String getBackTime() {
            return BackTime;
        }

        public void setBackTime(String BackTime) {
            this.BackTime = BackTime;
        }
    }
}
