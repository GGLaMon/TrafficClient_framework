/**
 *
 */
package com.mad.trafficclient.fragment;

import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.CommonAdapter;
import com.mad.trafficclient.adapter.CommonViewHolder;
import com.mad.trafficclient.bean.RoadLight;
import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.UrlUtils;
import com.mad.trafficclient.result.Result222;
import com.mad.trafficclient.util.LoadingDialog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_2 extends Fragment implements View.OnClickListener {
    private Spinner mSpFrag2;
    private Button mBtnQuery;
    private ListView mLsFrag2;
    private List<RoadLight> list = new ArrayList<>();
    private CommonAdapter<RoadLight> adapter;
    private int spinnerId;
    private int count = 0;//请求次数

    //先定义这个方法
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_layout02, container, false);
        initView(view);
        getData();
        return view;
    }

    private void getData() {
        LoadingDialog.showDialog(getActivity());
        Map<String, String> p = new HashMap<>();
        //这里只要求显示5 个路口,所以for 循环5 次
        for (int i = 1; i <= 5; i++) {
            p.put("TrafficLightId", String.valueOf(i));
            final int finalI = i;
            HttpUtils.request(UrlUtils.url222, p, Result222.class, new HttpUtils.RequestListener<Result222>() {
                @Override
                public void onSuccess(Result222 result) {
                    count++;
                    list.add(new RoadLight(finalI, result.getRedTime(),
                            result.getGreenTime(), result.getYellowTime()));
                    //每循环一次count 就加一次，当获取到5 次时则实现排序,并通知适配器改变
                    if (count == 5) {
                        paixu();
                        // adapter.notifyDataSetChanged();
                        LoadingDialog.disDialog();
                        count = 0;
                    }
                }

                @Override
                public void onFailure(String msg) {
                    LoadingDialog.showToast(msg);
                }
            });
        }
    }

    private void paixu() {
        switch (spinnerId) {
            case 0://路口升序
                Collections.sort(list, new Comparator<RoadLight>() {
                    @Override
                    public int compare(RoadLight o1, RoadLight o2) {
                        return o1.getRoadId() - o2.getRoadId();
                    }
                });
                break;
            case 1://路口降序
                Collections.sort(list, new Comparator<RoadLight>() {
                    @Override
                    public int compare(RoadLight o1, RoadLight o2) {
                        return o2.getRoadId() - o1.getRoadId();
                    }
                });
                break;
            case 2://红灯升序
                Collections.sort(list, new Comparator<RoadLight>() {
                    @Override
                    public int compare(RoadLight o1, RoadLight o2) {
                        return o1.getRed() - o2.getRed();
                    }
                });
                break;
            case 3://红灯降序
                Collections.sort(list, new Comparator<RoadLight>() {
                    @Override
                    public int compare(RoadLight o1, RoadLight o2) {
                        return o2.getRed() - o1.getRed();
                    }
                });
                break;
            case 4://绿灯升序
                Collections.sort(list, new Comparator<RoadLight>() {
                    @Override
                    public int compare(RoadLight o1, RoadLight o2) {
                        return o1.getGreen() - o2.getGreen();
                    }
                });
                break;
            case 5://绿灯降序
                Collections.sort(list, new Comparator<RoadLight>() {
                    @Override
                    public int compare(RoadLight o1, RoadLight o2) {
                        return o2.getGreen() - o1.getGreen();
                    }
                });
                break;
            case 6://黄灯升序
                Collections.sort(list, new Comparator<RoadLight>() {
                    @Override
                    public int compare(RoadLight o1, RoadLight o2) {
                        return o1.getYellow() - o2.getYellow();
                    }
                });
                break;
            case 7://黄灯降序
                Collections.sort(list, new Comparator<RoadLight>() {
                    @Override
                    public int compare(RoadLight o1, RoadLight o2) {
                        return o2.getYellow() - o1.getYellow();
                    }
                });
                break;
        }
        adapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        mSpFrag2 = (Spinner) view.findViewById(R.id.sp_frag2);
        mBtnQuery = (Button) view.findViewById(R.id.btn_query);
        mLsFrag2 = (ListView) view.findViewById(R.id.ls_frag2);
        mBtnQuery.setOnClickListener(this);
        //初始化适配器，将上下文，数据集list，及子布局进行绑定
        adapter = new CommonAdapter<RoadLight>(getActivity(), list, R.layout.frag2_item) {
            @Override
            public void convert(CommonViewHolder holder, RoadLight item, int position) {
                holder.setText(R.id.tv_road_id, String.valueOf(item.getRoadId()));
                holder.setText(R.id.tv_green, String.valueOf(item.getGreen()));
                holder.setText(R.id.tv_yellow, String.valueOf(item.getYellow()));
                holder.setText(R.id.tv_red, String.valueOf(item.getRed()));
            }
        };
        mLsFrag2.setAdapter(adapter);
        mSpFrag2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                paixu();
                break;
        }
    }
}