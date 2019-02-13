package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result35 {

    /**
     * RESULT : S
     * ERRMSG : 查询成功
     * ROWS_DETAIL : [{"Name":"东部华侨城","Address":"中国深圳大梅沙","Tel":"88888888"}]
     */

    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAIL> ROWSDETAIL;

    public static Result35 objectFromData(String str) {

        return new Gson().fromJson(str, Result35.class);
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
         * Name : 东部华侨城
         * Address : 中国深圳大梅沙
         * Tel : 88888888
         */

        @SerializedName("Name")
        private String Name;
        @SerializedName("Address")
        private String Address;
        @SerializedName("Tel")
        private String Tel;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }
    }
}
