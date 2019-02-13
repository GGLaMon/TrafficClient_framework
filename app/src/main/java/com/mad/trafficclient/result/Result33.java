package com.mad.trafficclient.result;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 * Date: 2018/5/8
 * Time: 15:07
 */
public class Result33 {


    /**
     * RESULT : S
     * ERRMSG : 查询成功
     * ROWS_DETAIL : [{"Title":"","From":"","Time":"","Content":"","Web":""}]
     */

    private String RESULT;
    private String ERRMSG;
    private List<ROWSDETAIL> ROWS_DETAIL;

    public static Result33 objectFromData(String str) {

        return new Gson().fromJson(str, Result33.class);
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
         * Title :
         * From :
         * Time :
         * Content :
         * Web :
         */

        private String Title;
        private String From;
        private String Time;
        private String Content;
        private String Web;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getFrom() {
            return From;
        }

        public void setFrom(String From) {
            this.From = From;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getWeb() {
            return Web;
        }

        public void setWeb(String Web) {
            this.Web = Web;
        }
    }
}


