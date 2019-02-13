package com.mad.trafficclient.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.mad.trafficclient.BaseApplication;
import com.mad.trafficclient.bean.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

/**
 * 项目名称：
 * 类描述：
 * 创建人：zhaowei
 * 创建时间：2017/4/19 9:20
 * 修改人：Administrator
 * 修改时间：2017/4/19 9:20
 * 修改备注：
 */
public class Util {

    public static String urlHttp;
    public static String urlPort;

    /**
     * 描述：保存数据到SharedPreferences对象中
     *
     * @param ipUrl
     * @param ipPort
     */

    public static void saveSetting(String ipUrl, String ipPort, Context context) {
        SharedPreferences spSettingSave = context.getSharedPreferences("setting", MODE_PRIVATE);// 将需要记录的数据保存在setting.xml文件中
        SharedPreferences.Editor editor = spSettingSave.edit();
        editor.putString("ipUrl", ipUrl);
        editor.putString("ipPort", ipPort);
        editor.commit();
    }

    /**
     * 描述：获取数据到SharedPreferences对象中
     *
     * @return
     */
    public static UrlBean loadSetting(Context context) {
        UrlBean urlBean = new UrlBean();

        SharedPreferences loadSettingLoad = context.getSharedPreferences("setting", MODE_PRIVATE);
        //这里是将setting.xml 中的数据读出来
        urlBean.setUrl(loadSettingLoad.getString("ipUrl", "139.199.220.137"));
        urlBean.setPort(loadSettingLoad.getString("ipPort", "8080"));

//        String urlSetting = "http://" + urlHttp+ ":" + urlPort + "/";
        return urlBean;
    }

    public static void setUser(String username, String userRole) {
        SharedPreferences.Editor editor = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putString("UserName", username);
        editor.putString("UserRole", userRole);
        editor.apply();

    }

    public static User getUser() {
        SharedPreferences preferences = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE);
        return new User(preferences.getString("UserName", "user1"), preferences.getString("UserRole", "R02"));
    }

    //用于获取模拟数据，与比赛无关
    public static String getLocalJson(String fileName) {
        String json = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStream is = BaseApplication.getInstance().getAssets().open(fileName);
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
