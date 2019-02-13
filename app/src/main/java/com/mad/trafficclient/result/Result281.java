package com.mad.trafficclient.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result281 {

    /**
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"carnumber":"鲁A10001","number":101,"pcardid":"370214197107271055","carbrand":"audi","buydate":"2016-06-01"}]
     * RESULT : S
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAIL> ROWSDETAIL;

    public static Result281 objectFromData(String str) {

        return new Gson().fromJson(str, Result281.class);
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
         * carnumber : 鲁A10001
         * number : 101
         * pcardid : 370214197107271055
         * carbrand : audi
         * buydate : 2016-06-01
         */

        @SerializedName("carnumber")
        private String carnumber;
        @SerializedName("number")
        private int number;
        @SerializedName("pcardid")
        private String pcardid;
        @SerializedName("carbrand")
        private String carbrand;
        @SerializedName("buydate")
        private String buydate;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public String getCarnumber() {
            return carnumber;
        }

        public void setCarnumber(String carnumber) {
            this.carnumber = carnumber;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getPcardid() {
            return pcardid;
        }

        public void setPcardid(String pcardid) {
            this.pcardid = pcardid;
        }

        public String getCarbrand() {
            return carbrand;
        }

        public void setCarbrand(String carbrand) {
            this.carbrand = carbrand;
        }

        public String getBuydate() {
            return buydate;
        }

        public void setBuydate(String buydate) {
            this.buydate = buydate;
        }
    }
}
