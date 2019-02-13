/**
 *
 */
package com.mad.trafficclient.fragment;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.UrlUtils;
import com.mad.trafficclient.result.Result;
import com.mad.trafficclient.util.LoadingDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_8 extends Fragment {
    private TextView mBtnDate;
    private TextView mTvCar;
    private TextView mTvSwitch1;
    private SwitchCompat mSw1;
    private TextView mTvSwitch2;
    private SwitchCompat mSw2;
    private TextView mTvSwitch3;
    private SwitchCompat mSw3;
    private ImageView mImgLight;
    private DatePickerDialog datePickerDialog;
    private List<SwitchCompat> switchList;
    private List<TextView> textViewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout08, container, false);
        initView(view);
        initDateDialog();
        return view;
    }

    private void initView(View view) {
        mBtnDate = (TextView) view.findViewById(R.id.btn_date);
        mTvCar = (TextView) view.findViewById(R.id.tv_car);
        mTvSwitch1 = (TextView) view.findViewById(R.id.tv_switch1);
        mSw1 = (SwitchCompat) view.findViewById(R.id.sw1);
        mTvSwitch2 = (TextView) view.findViewById(R.id.tv_switch2);
        mSw2 = (SwitchCompat) view.findViewById(R.id.sw2);
        mTvSwitch3 = (TextView) view.findViewById(R.id.tv_switch3);
        mSw3 = (SwitchCompat) view.findViewById(R.id.sw3);
        mImgLight = (ImageView) view.findViewById(R.id.img_light);
        AnimationDrawable drawable = (AnimationDrawable) mImgLight.getDrawable();
        drawable.start();
        switchList = new ArrayList<>();
        switchList.add(mSw1);
        switchList.add(mSw2);
        switchList.add(mSw3);
        textViewList = new ArrayList<>();
        textViewList.add(mTvSwitch1);
        textViewList.add(mTvSwitch2);
        textViewList.add(mTvSwitch3);
        for (int i = 0; i < switchList.size(); i++) {
            final int finalI = i;
            switchList.get(i).setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void
                onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        setAction(finalI + 1, "Start");
                        textViewList.get(finalI).setText("开");
                    } else {
                        setAction(finalI + 1, "Stop");
                        textViewList.get(finalI).setText("关");
                    }
                }
            });
        }
        mBtnDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!datePickerDialog.isShowing()) {
                    datePickerDialog.show();
                }
            }
        });
    }

    private void initDateDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mBtnDate.setText(year + "年" + (month + 1) + "月" + day + "日");
        check(day);
        datePickerDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mBtnDate.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
                check(dayOfMonth);
            }
        }, year, month, day);
    }

    private void check(int day) {
        boolean status;
        if (day % 2 == 0) {
            mTvCar.setText("单号出行车辆：2");
            status = false;
        } else {
            mTvCar.setText("单号出行车辆：1、3");
            status = true;
        }
        mSw1.setChecked(status);
        mSw2.setChecked(!status);
        mSw3.setChecked(status);
        mSw1.setEnabled(status);
        mSw2.setEnabled(!status);
        mSw3.setEnabled(status);
    }

    private void setAction(final int carId, final
    String action) {
        Map<String, String> map = new HashMap<>();
        map.put("CarId", String.valueOf(carId));
        map.put("CarAction", action);
        HttpUtils.request(UrlUtils.url211, map, Result.class, new HttpUtils.RequestListener<Result>() {
            @Override
            public void onSuccess(Result result) {
                LoadingDialog.disDialog();
                LoadingDialog.showToast(carId + "号小车设置成功");
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialog.disDialog();
                LoadingDialog.showToast(carId + "号小车设置失败");
            }
        });
    }
}