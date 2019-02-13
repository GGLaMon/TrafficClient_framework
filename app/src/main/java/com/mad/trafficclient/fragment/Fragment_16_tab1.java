/**
 *
 */
package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.CommonAdapter;
import com.mad.trafficclient.adapter.CommonViewHolder;
import com.mad.trafficclient.bean.F16_carInfo;
import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.UrlUtils;
import com.mad.trafficclient.result.Result281;
import com.mad.trafficclient.result.Result292;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment_16_tab1 extends Fragment {

    private ImageView mImav16;
    private TextView mTvName16;
    private TextView mTvGender16;
    private TextView mTvPhone16;
    private TextView mTvIdcard16;
    private TextView mTvRegistertime16;
    private TextView mTextView8;
    private ListView mLv16;
    private CommonAdapter<F16_carInfo> adapter;
    private String pcardid;
    private List<F16_carInfo> list = new ArrayList<>();
    private GridView mGv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout16_1, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        HttpUtils.request(UrlUtils.url292, new HashMap<String, String>(), Result292.class, new HttpUtils.RequestListener<Result292>() {
            @Override
            public void onSuccess(Result292 result) {
                pcardid = result.getROWSDETAIL().get(0).getPcardid();
                mTvName16.setText("姓名:" + result.getROWSDETAIL().get(0).getPname());
                mTvGender16.setText("性别:" + result.getROWSDETAIL().get(0).getPsex());
                mTvIdcard16.setText("身份证号:" + result.getROWSDETAIL().get(0).getPcardid());
                mTvPhone16.setText("手机号码:" + result.getROWSDETAIL().get(0).getPtel());
                mTvRegistertime16.setText("注册时间:" + result.getROWSDETAIL().get(0).getPregisterdate());
            }

            @Override
            public void onFailure(String msg) {

            }
        });
        HttpUtils.request(UrlUtils.url281, new HashMap<String, String>(), Result281.class, new HttpUtils.RequestListener<Result281>() {
            @Override
            public void onSuccess(Result281 result) {
                for (Result281.ROWSDETAIL rowsdetail : result.getROWSDETAIL()) {
                    if (rowsdetail.getPcardid().equals(pcardid)) {
                        list.add(new F16_carInfo(rowsdetail.getCarbrand(), rowsdetail.getCarnumber()));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    private void initView(View view) {
        mImav16 = (ImageView) view.findViewById(R.id.imav16);
        mTvName16 = (TextView) view.findViewById(R.id.tv_name16);
        mTvGender16 = (TextView) view.findViewById(R.id.tv_gender16);
        mTvPhone16 = (TextView) view.findViewById(R.id.tv_phone16);
        mTvIdcard16 = (TextView) view.findViewById(R.id.tv_idcard_16);
        mTvRegistertime16 = (TextView) view.findViewById(R.id.tv_registertime16);
        mTextView8 = (TextView) view.findViewById(R.id.textView8);

        adapter = new CommonAdapter<F16_carInfo>(getActivity(), list, R.layout.fragment_layout16_1_item) {
            @Override
            public void convert(CommonViewHolder holder, F16_carInfo item, int position) {
                holder.setText(R.id.tv_car_num, item.getCarNum());
            }
        };

        mGv = (GridView) view.findViewById(R.id.gv);
        mGv.setAdapter(adapter);
    }
}