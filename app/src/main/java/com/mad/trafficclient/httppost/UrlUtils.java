package com.mad.trafficclient.httppost;

import com.mad.trafficclient.BaseApplication;
import com.mad.trafficclient.util.Util;

public class UrlUtils {
    private static String getUrl() {
        String url = Util.loadSetting(BaseApplication.getInstance()).getUrl();
        String port = Util.loadSetting(BaseApplication.getInstance()).getPort();
        return "http://" + url + ":" + port + "/api/v2/";
    }

    public static String url211 = getUrl() + "set_car_move";
    public static String url212 = getUrl() + "get_car_move";
    public static String url213 = getUrl() + "get_car_account_balance";
    public static String url214 = getUrl() + "set_car_account_recharge";
    public static String url215 = getUrl() + "get_car_peccancy";
    public static String url216 = getUrl() + "get_all_car_peccancy";
    public static String url221 = getUrl() + "set_trafficlight_config";
    public static String url222 = getUrl() + "get_trafficlight_config";
    public static String url231 = getUrl() + "set_roadlight_control_mode";
    public static String url232 = getUrl() + "get_roadlight_control_mode";
    public static String url233 = getUrl() + "set_roadlight_status";
    public static String url234 = getUrl() + "get_roadlight_status";
    public static String url235 = getUrl() + "set_light_value";
    public static String url236 = getUrl() + "get_light_value";
    public static String url241 = getUrl() + "get_all_sense";
    public static String url242 = getUrl() + "get_sense_by_name";
    public static String url251 = getUrl() + "get_bus_capacity";
    public static String url261 = getUrl() + "get_road_status";
    public static String url271 = getUrl() + "get_bus_station_info";
    public static String url281 = getUrl() + "get_car_info";
    public static String url282 = getUrl() + "get_peccancy_type";
    public static String url291 = getUrl() + "user_login";
    public static String url292 = getUrl() + "get_all_user_info";
    public static String url2101 = getUrl() + "get_weather";
    public static String url2111 = getUrl() + "set_etc_rate";
    public static String url2112 = getUrl() + "get_etc_rate";
    public static String url2113 = getUrl() + "get_car_account_record";
    public static String url2114 = getUrl() + "get_car_account_fee";
    public static String url2115 = getUrl() + "set_car_account_fee";
    public static String url2116 = getUrl() + "get_etc_traffic_log";
    public static String url2117 = getUrl() + "get_etc_blacklist";

}
