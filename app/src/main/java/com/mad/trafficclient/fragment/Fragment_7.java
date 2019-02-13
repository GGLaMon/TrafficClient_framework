/**
 *
 */
package com.mad.trafficclient.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.HttpUtils.RequestListener;
import com.mad.trafficclient.httppost.UrlUtils;
import com.mad.trafficclient.result.Result241;
import com.mad.trafficclient.util.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_7 extends Fragment implements OnClickListener {
    private TextView mTvSwitch;
    private SwitchCompat mSw;
    private EditText mEt1;
    private EditText mEt2;
    private EditText mEt3;
    private EditText mEt4;
    private EditText mEt5;
    private EditText mEt6;
    private Button mBtnSave;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private List<View> viewList = new ArrayList<>();
    private Timer timer;
    private int temp;
    private int hum;
    private int light;
    private int co2;
    private int pm;
    private int road;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout07, container, false);
        initView(view);
        getData();
        refresh();
        initNotify();
        return view;
    }

    private void initView(View view) {
        mTvSwitch = (TextView) view.findViewById(R.id.tv_switch);
        mSw = (SwitchCompat) view.findViewById(R.id.sw);
        mEt1 = (EditText) view.findViewById(R.id.et1);
        mEt2 = (EditText) view.findViewById(R.id.et2);
        mEt3 = (EditText) view.findViewById(R.id.et3);
        mEt4 = (EditText) view.findViewById(R.id.et4);
        mEt5 = (EditText) view.findViewById(R.id.et5);
        mEt6 = (EditText) view.findViewById(R.id.et6);
        mBtnSave = (Button) view.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        preferences = getActivity().getSharedPreferences("yuzhi", Context.MODE_PRIVATE);
        editor = preferences.edit();
        mSw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void
            onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setEnable(!isChecked);
                if (isChecked) {
                    mTvSwitch.setText("开");
                } else {
                    mTvSwitch.setText("关");
                }
            }
        });
        viewList.add(mEt1);
        viewList.add(mEt2);
        viewList.add(mEt3);
        viewList.add(mEt4);
        viewList.add(mEt5);
        viewList.add(mEt6);
        viewList.add(mBtnSave);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                submit();
                break;
        }
    }

    private void submit() {
// validate
        String et1String = mEt1.getText().toString().trim();
        if (TextUtils.isEmpty(et1String)) {
            Toast.makeText(getContext(), "阈值不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String et2String = mEt2.getText().toString().trim();
        if (TextUtils.isEmpty(et2String)) {
            Toast.makeText(getContext(), "阈值不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String et3String = mEt3.getText().toString().trim();
        if (TextUtils.isEmpty(et3String)) {
            Toast.makeText(getContext(), "阈值不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String et4String = mEt4.getText().toString().trim();
        if (TextUtils.isEmpty(et4String)) {
            Toast.makeText(getContext(), "阈值不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String et5String = mEt5.getText().toString().trim();
        if (TextUtils.isEmpty(et5String)) {
            Toast.makeText(getContext(), "阈值不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String et6String = mEt6.getText().toString().trim();
        if (TextUtils.isEmpty(et6String)) {
            Toast.makeText(getContext(), "阈值不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        editor.putInt("temp", Integer.parseInt(et1String));
        editor.putInt("hum", Integer.parseInt(et2String));
        editor.putInt("light", Integer.parseInt(et3String));
        editor.putInt("co2", Integer.parseInt(et4String));
        editor.putInt("pm", Integer.parseInt(et5String));
        editor.putInt("road", Integer.parseInt(et6String));
        editor.apply();
        LoadingDialog.showToast("保存成功");
        getData();
    }

    private void getData() {
        temp = preferences.getInt("temp", -1);
        hum = preferences.getInt("hum", -1);
        light = preferences.getInt("light", -1);
        co2 = preferences.getInt("co2", -1);
        pm = preferences.getInt("pm", -1);
        road = preferences.getInt("road", -1);
        if (temp == -1) {
            return;
        }
        mEt1.setText(String.valueOf(temp));
        mEt2.setText(String.valueOf(hum));
        mEt3.setText(String.valueOf(light));
        mEt4.setText(String.valueOf(co2));
        mEt5.setText(String.valueOf(pm));
        mEt6.setText(String.valueOf(road));
    }

    private void setEnable(boolean enable) {
        for (int i = 0; i < viewList.size(); i++) {
            viewList.get(i).setEnabled(enable);
        }
    }

    private void refresh() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                HttpUtils.request(UrlUtils.url241, new HashMap<String, String>(), Result241.class, new RequestListener<Result241>() {
                    @Override
                    public void
                    onSuccess(Result241 result) {
                        getData();
                        if (temp == -1) {
                            return;
                        }
                        if (result.getTemperature() < temp) {
                            builder.setSubText("温度报警").setContentTitle("阈值为：" + temp + "，当前值：" + result.getTemperature());
                            notificationManager.notify(1, builder.build()
                            );
                        }
                        if (result.getHumidity() < hum) {
                            builder.setSubText("湿度报警").setContentTitle("阈值为：" + hum + "，当前值：" + result.getHumidity());
                            notificationManager.notify(2, builder.build()
                            );
                        }
                        if (result.getLightIntensity() < light) {
                            builder.setSubText("光照报警").setContentTitle("阈值为：" + light + "，当前值：" + result.getLightIntensity());
                            notificationManager.notify(3, builder.build());
                        }
                        if (result.getCo2() < co2) {
                            builder.setSubText("CO2报警").setContentTitle("阈值为：" + co2 + "，当前值：" + result.getCo2());
                            notificationManager.notify(4, builder.build()
                            );
                        }
                        if (result.get_$Pm25159() < pm) {
                            builder.setSubText("PM2.5报警").setContentTitle("阈值为：" + pm + "，当前值：" + result.get_$Pm25159());
                            notificationManager.notify(5, builder.build()
                            );
                        }
                    }

                    @Override
                    public void
                    onFailure(String msg) {
                        LoadingDialog.showToast(msg);
                    }
                });
            }
        }, 100, 10000);
    }

    private void initNotify() {
        notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(getActivity());
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 1, new Intent(), 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)).setTicker("告警").setDefaults(~0).setPriority(Notification.PRIORITY_HIGH).setAutoCancel(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }
}