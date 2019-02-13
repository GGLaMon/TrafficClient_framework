package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result292 {
    /**
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"username":"user1","pname":"王生安","pcardid":"370101196101011001","psex":"男","ptel":"13804110001","pregisterdate":"1990-5-21 02:19:21"}]
     * RESULT : S
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAIL> ROWSDETAIL;

    public static Result292 objectFromData(String str) {

        return new Gson().fromJson(str, Result292.class);
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
         * username : user1
         * pname : 王生安
         * pcardid : 370101196101011001
         * psex : 男
         * ptel : 13804110001
         * pregisterdate : 1990-5-21 02:19:21
         */

        @SerializedName("username")
        private String username;
        @SerializedName("pname")
        private String pname;
        @SerializedName("pcardid")
        private String pcardid;
        @SerializedName("psex")
        private String psex;
        @SerializedName("ptel")
        private String ptel;
        @SerializedName("pregisterdate")
        private String pregisterdate;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getPcardid() {
            return pcardid;
        }

        public void setPcardid(String pcardid) {
            this.pcardid = pcardid;
        }

        public String getPsex() {
            return psex;
        }

        public void setPsex(String psex) {
            this.psex = psex;
        }

        public String getPtel() {
            return ptel;
        }

        public void setPtel(String ptel) {
            this.ptel = ptel;
        }

        public String getPregisterdate() {
            return pregisterdate;
        }

        public void setPregisterdate(String pregisterdate) {
            this.pregisterdate = pregisterdate;
        }
    }
}
