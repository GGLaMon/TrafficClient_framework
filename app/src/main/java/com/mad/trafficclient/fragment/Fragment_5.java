/**
 *
 */
package com.mad.trafficclient.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.mad.trafficclient.R;
import com.mad.trafficclient.db.DB;
import com.mad.trafficclient.bean.F5_Sense;
import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.UrlUtils;
import com.mad.trafficclient.result.Result241;
import com.mad.trafficclient.result.Result261;
import com.mad.trafficclient.util.LoadingDialog;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_5 extends Fragment implements OnClickListener {
    private TextView mTvTemp;
    private TextView mTvHum;
    private TextView mTvLight;
    private TextView mTvCo2;
    private TextView mTvPm;
    private TextView mTvRoad;
    private Timer timer;
    private RelativeLayout mBg1;
    private RelativeLayout mBg2;
    private RelativeLayout mBg3;
    private RelativeLayout mBg4;
    private RelativeLayout mBg5;
    private RelativeLayout mBg6;
    private Dao<F5_Sense, Integer> dao;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout05, container, false);
        dao = DB.getInstance().getDao(F5_Sense.class);
        preferences = getActivity().getSharedPreferences("yuzhi", Context.MODE_PRIVATE);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        timer();
    }

    private void initView(View view) {
        mTvTemp = (TextView) view.findViewById(R.id.tv_temp);
        mTvHum = (TextView) view.findViewById(R.id.tv_hum);
        mTvLight = (TextView) view.findViewById(R.id.tv_light);
        mTvCo2 = (TextView) view.findViewById(R.id.tv_co2);
        mTvPm = (TextView) view.findViewById(R.id.tv_pm);
        mTvRoad = (TextView) view.findViewById(R.id.tv_road);
        mBg1 = (RelativeLayout) view.findViewById(R.id.bg1);
        mBg1.setOnClickListener(this);
        mBg2 = (RelativeLayout) view.findViewById(R.id.bg2);
        mBg2.setOnClickListener(this);
        mBg3 = (RelativeLayout) view.findViewById(R.id.bg3);
        mBg3.setOnClickListener(this);
        mBg4 = (RelativeLayout) view.findViewById(R.id.bg4);
        mBg4.setOnClickListener(this);
        mBg5 = (RelativeLayout) view.findViewById(R.id.bg5);
        mBg5.setOnClickListener(this);
        mBg6 = (RelativeLayout) view.findViewById(R.id.bg6);
        mBg6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bg1:
                jump(0);
                break;
            case R.id.bg2:
                jump(1);
                break;
            case R.id.bg3:
                jump(2);
                break;
            case R.id.bg4:
                jump(3);
                break;
            case R.id.bg5:
                jump(4);
                break;
            case R.id.bg6:
                jump(5);
                break;
        }
    }

    private void jump(int index) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment_6 fragment = new Fragment_6();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.maincontent, fragment);
        fragmentTransaction.commit();
    }

    private void timer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getData();
            }
        }, 100, 3000);
    }

    private void getData() {
        final int temp = preferences.getInt("temp", 8);
        final int hum = preferences.getInt("hum", 50);
        final int light = preferences.getInt("light", 3000);
        final int co2 = preferences.getInt("co2", 5000);
        final int pm = preferences.getInt("pm", 200);
        final int road = preferences.getInt("road", 3);
        HttpUtils.request(UrlUtils.url241, new HashMap<String, String>(), Result241.class, new HttpUtils.RequestListener<Result241>() {
            @Override
            public void onSuccess(Result241 result) {
                long time = System.currentTimeMillis();
                F5_Sense tempSense = new F5_Sense();
                F5_Sense humSense = new F5_Sense();
                F5_Sense lightSense = new F5_Sense();
                F5_Sense co2Sense = new F5_Sense();
                F5_Sense pmSense = new F5_Sense();
                mTvTemp.setText(String.valueOf(result.getTemperature()));
                mTvHum.setText(String.valueOf(result.getHumidity()));
                mTvLight.setText(String.valueOf(result.getLightIntensity()));
                mTvCo2.setText(String.valueOf(result.getCo2()));
                mTvPm.setText(String.valueOf(result.get_$Pm25159()));
                tempSense.setValue(result.getTemperature());
                tempSense.setType(1);
                tempSense.setNormal(1);
                tempSense.setYuzhi(temp);
                tempSense.setTime(time);
                humSense.setValue(result.getHumidity());
                humSense.setType(2);
                humSense.setNormal(1);
                humSense.setYuzhi(hum);
                humSense.setTime(time);
                lightSense.setValue(result.getLightIntensity());
                lightSense.setType(3);
                lightSense.setNormal(1);
                lightSense.setYuzhi(light);
                lightSense.setTime(time);
                co2Sense.setValue(result.getCo2());
                co2Sense.setType(4);
                co2Sense.setNormal(1);
                co2Sense.setYuzhi(co2);
                co2Sense.setTime(time);
                pmSense.setValue(result.get_$Pm25159());
                pmSense.setType(5);
                pmSense.setNormal(1);
                pmSense.setYuzhi(pm);
                pmSense.setTime(time);
                if (result.getTemperature() > temp) {
                    tempSense.setNormal(0);
                    mBg1.setBackgroundColor(Color.RED);
                } else {
                    mBg1.setBackgroundColor(Color.GREEN);
                }
                if (result.getHumidity() > hum) {
                    humSense.setNormal(0);
                    mBg2.setBackgroundColor(Color.RED);
                } else {
                    mBg2.setBackgroundColor(Color.GREEN);
                }
                if (result.getLightIntensity() > light) {
                    lightSense.setNormal(0);
                    mBg3.setBackgroundColor(Color.RED);
                } else {
                    mBg3.setBackgroundColor(Color.GREEN);
                }
                if (result.getCo2() > co2) {
                    co2Sense.setNormal(0);
                    mBg4.setBackgroundColor(Color.RED);
                } else {
                    mBg4.setBackgroundColor(Color.GREEN);
                }
                if (result.get_$Pm25159() > pm) {
                    pmSense.setNormal(0);
                    mBg5.setBackgroundColor(Color.RED);
                } else {
                    mBg5.setBackgroundColor(Color.GREEN);
                }
                try {
                    dao.create(tempSense);
                    dao.create(humSense);
                    dao.create(lightSense);
                    dao.create(co2Sense);
                    dao.create(pmSense);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialog.showToast(msg);
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("RoadId", "1");
        HttpUtils.request(UrlUtils.url261, map, Result261.class, new HttpUtils.RequestListener<Result261>() {
            @Override
            public void onSuccess(Result261 result) {
                long time = System.currentTimeMillis();
                F5_Sense sense = new F5_Sense();
                sense.setValue(result.getStatus());
                sense.setType(6);
                sense.setNormal(1);
                sense.setYuzhi(road);
                sense.setTime(time);
                mTvRoad.setText(String.valueOf(result.getStatus()));
                if (result.getStatus() > road) {
                    sense.setNormal(0);
                    mBg6.setBackgroundColor(Color.RED);
                } else {
                    mBg6.setBackgroundColor(Color.GREEN);
                }
                try {
                    dao.create(sense);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialog.showToast(msg);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}