package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result282 {
    /**
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"pcode":"1001A　　","premarks":"A 驾驶拼装的非汽车类机动车上道路行驶的","pmoney":1000,"pscore":0}]
     * RESULT : S
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAIL> ROWSDETAIL;

    public static Result282 objectFromData(String str) {

        return new Gson().fromJson(str, Result282.class);
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
         * pcode : 1001A　　
         * premarks : A 驾驶拼装的非汽车类机动车上道路行驶的
         * pmoney : 1000
         * pscore : 0
         */

        @SerializedName("pcode")
        private String pcode;
        @SerializedName("premarks")
        private String premarks;
        @SerializedName("pmoney")
        private int pmoney;
        @SerializedName("pscore")
        private int pscore;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getPremarks() {
            return premarks;
        }

        public void setPremarks(String premarks) {
            this.premarks = premarks;
        }

        public int getPmoney() {
            return pmoney;
        }

        public void setPmoney(int pmoney) {
            this.pmoney = pmoney;
        }

        public int getPscore() {
            return pscore;
        }

        public void setPscore(int pscore) {
            this.pscore = pscore;
        }
    }
}
