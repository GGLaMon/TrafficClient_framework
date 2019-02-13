package com.mad.trafficclient.httppost;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.mad.trafficclient.BaseApplication;
import com.mad.trafficclient.result.Result;
import com.mad.trafficclient.util.Util;

import org.json.JSONObject;

import java.util.Map;

public class HttpUtils {
    //回调接口
    public interface RequestListener<T> {
        void onSuccess(T result);

        void onFailure(String msg);
    }

    /**
     * Request.
     *
     * @param <T>      解析的类型(泛型)
     * @param url      请求地址
     * @param params   请求参数
     * @param tClass   解析的类型
     * @param listener 回调监听
     */
    public static <T> void request(String url, Map<String, String> params, final Class<T> tClass, final RequestListener<T> listener) {
        //默认添加UserName参数
        if (!params.containsKey("UserName")) {
            params.put("UserName", Util.getUser().getUserName());
        }
        Request request = new JsonObjectRequest(Request.Method.POST, url, new Gson().toJson(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        //获取RESULT
                        String result = jsonObject.optString("RESULT");
                        try {
                            T bean = new Gson().fromJson(jsonObject.toString(), tClass);
                            //根据服务器返回的数据进行对应的处理
                            if (result.equals("S")) {
                                listener.onSuccess(bean);
                            } else {
                                listener.onFailure(jsonObject.optString("ERRMSG"));
                            }
                        } catch (JsonParseException e) {
                            //数据解析异常
                            listener.onFailure("数据异常，请检查服务器");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        //打印请求失败的原因
                        if (!TextUtils.isEmpty(volleyError.getMessage())) {
                            Log.e("请求失败", volleyError.getMessage());
                        }

                        if (volleyError instanceof ServerError) {//服务器异常(返回码为4xx)
                            int code = volleyError.networkResponse.statusCode;
                            if (code == 400) {
                                String s = new String(volleyError.networkResponse.data);
                                Result r = new Gson().fromJson(s, Result.class);
                                listener.onFailure(r.getERRMSG());
                                Log.e("请求失败", r.getERRMSG());
                                return;
                            }
                            listener.onFailure("参数或请求地址错误");
                        } else if (volleyError instanceof NoConnectionError) {//连接异常
                            listener.onFailure("无法连接服务器");
                        } else if (volleyError instanceof TimeoutError) {//超时
                            listener.onFailure("连接超时，请检查网络状态");
                        } else {
                            listener.onFailure("未知错误");
                        }
                    }
                }).setRetryPolicy(new DefaultRetryPolicy(
                //超时时间
                10000,
                //失败重连次数(默认为0)
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                /*
                 * 超时时间的乘积因子(默认为1f)
                 * 以默认超时时间为10s为例:
                 * 1. DEFAULT_BACKOFF_MULT = 1f, 每次超时时间都是10s*1f*DEFAULT_BACKOFF_MULT.
                 * 2. DEFAULT_BACKOFF_MULT = 2f, 则第二次超时时间为:10s+10s*2=30s,第三次超时时间为:30s+10*2=50s
                 */
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );

        //将request加入请求队列
        BaseApplication.getQueue().add(request);
    }
}
