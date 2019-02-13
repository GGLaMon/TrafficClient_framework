package com.mad.trafficclient.result;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 */
public class Result39 {

    /**
     * RESULT : S
     * ERRMSG : 查询成功
     * ROWS_DETAIL : [{"Type":"科技","News":[{"Title":"短视频行业2018风向标： 渠道下沉，平台抢夺三四线城市流量红利"}]}]
     */

    private String RESULT;
    private String ERRMSG;
    private List<ROWSDETAIL> ROWS_DETAIL;

    public static Result39 objectFromData(String str) {

        return new Gson().fromJson(str, Result39.class);
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
         * Type : 科技
         * News : [{"Title":"短视频行业2018风向标： 渠道下沉，平台抢夺三四线城市流量红利"}]
         */

        private String Type;
        private List<News> News;

        public static ROWSDETAIL objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAIL.class);
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public List<News> getNews() {
            return News;
        }

        public void setNews(List<News> News) {
            this.News = News;
        }

        public static class News {
            /**
             * Title : 短视频行业2018风向标： 渠道下沉，平台抢夺三四线城市流量红利
             */

            private String Title;

            public static News objectFromData(String str) {

                return new Gson().fromJson(str, News.class);
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }
        }
    }
}
