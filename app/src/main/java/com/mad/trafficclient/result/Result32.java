package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result32 {
    /**
     * RESULT : S
     * ERRMSG : 查询成功
     * ROWS_DETAIL : [{"Number":"1","Route":"罗湖-机场东","StartTime":"06:30","EedTime":"23:00","Fare":"2-9","Mileage":40.9,"Interval":4,"Stations":"罗湖站、国贸站"}]
     */

    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAIL> ROWSDETAIL;

    public static Result32 objectFromData(String str) {

        return new Gson().fromJson(str, Result32.class);
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

    public List<ROWSDETAIL> getROWSDETAIL() {
        return ROWSDETAIL;
    }

    public void setROWSDETAIL(List<ROWSDETAIL> ROWSDETAIL) {
        this.ROWSDETAIL = ROWSDETAIL;
    }

    public static class ROWSDETAIL implements Serializable {
        /**
         * Number : 1
         * Route : 罗湖-机场东
         * StartTime : 06:30
         * EedTime : 23:00
         * Fare : 2-9
         * Mileage : 40.9
         * Interval : 4
         * Stations : 罗湖站、国贸站
         */

        @SerializedName("Number")
        private int Number;
        @SerializedName("Route")
        private String Route;
        @SerializedName("StartTime")
        private String StartTime;
        @SerializedName("EedTime")
        private String EedTime;
        @SerializedName("Fare")
        private String Fare;
        @SerializedName("Mileage")
        private double Mileage;
        @SerializedName("Interval")
        private int Interval;
        @SerializedName("Stations")
        private String Stations;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public String getRoute() {
            return Route;
        }

        public void setRoute(String Route) {
            this.Route = Route;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEedTime() {
            return EedTime;
        }

        public void setEedTime(String EedTime) {
            this.EedTime = EedTime;
        }

        public String getFare() {
            return Fare;
        }

        public void setFare(String Fare) {
            this.Fare = Fare;
        }

        public double getMileage() {
            return Mileage;
        }

        public void setMileage(double Mileage) {
            this.Mileage = Mileage;
        }

        public int getInterval() {
            return Interval;
        }

        public void setInterval(int Interval) {
            this.Interval = Interval;
        }

        public String getStations() {
            return Stations;
        }

        public void setStations(String Stations) {
            this.Stations = Stations;
        }
    }
}
