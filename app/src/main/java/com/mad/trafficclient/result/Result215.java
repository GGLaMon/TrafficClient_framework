package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result215 {

    /**
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"id":1,"carnumber":"鲁B10001","pcode":"1001A　　","pdatetime":"2016-5-21 08:19:21","paddr":"北京路"}]
     * RESULT : S
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAIL> ROWSDETAIL;

    public static Result215 objectFromData(String str) {

        return new Gson().fromJson(str, Result215.class);
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
         * id : 1
         * carnumber : 鲁B10001
         * pcode : 1001A　　
         * pdatetime : 2016-5-21 08:19:21
         * paddr : 北京路
         */

        @SerializedName("id")
        private int id;
        @SerializedName("carnumber")
        private String carnumber;
        @SerializedName("pcode")
        private String pcode;
        @SerializedName("pdatetime")
        private String pdatetime;
        @SerializedName("paddr")
        private String paddr;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCarnumber() {
            return carnumber;
        }

        public void setCarnumber(String carnumber) {
            this.carnumber = carnumber;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getPdatetime() {
            return pdatetime;
        }

        public void setPdatetime(String pdatetime) {
            this.pdatetime = pdatetime;
        }

        public String getPaddr() {
            return paddr;
        }

        public void setPaddr(String paddr) {
            this.paddr = paddr;
        }
    }
}
