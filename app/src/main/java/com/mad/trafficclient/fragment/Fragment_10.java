package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.CommonViewHolder;
import com.mad.trafficclient.adapter.ExpandAdapter;
import com.mad.trafficclient.bean.F10;
import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.UrlUtils;
import com.mad.trafficclient.result.Result251;
import com.mad.trafficclient.result.Result271;
import com.mad.trafficclient.util.LoadingDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_10 extends Fragment implements View.OnClickListener {
    private Button mBtn;
    private ExpandableListView mExlv;
    private TextView mTvPeoplesum;
    private ExpandAdapter<String, F10> expandAdapter;
    private List<String> groupList = new ArrayList<>();
    private List<List<F10>> childList = new ArrayList<>();

    private List<F10> dataList1 = new ArrayList<>();
    private List<F10> dataList2 = new ArrayList<>();
    private int times;
    private Timer timer;
    private int people;
    private int peopleSum = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout10, container, false);
        initView(view);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getStation();
                getPeople();
            }
        }, 100, 3000);

        return view;
    }

    private void getPeople() {
        people = 0;
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 15; i++) {
            map.put("BusId", String.valueOf(i + 1));
            final int finalI = i;
            HttpUtils.request(UrlUtils.url251, map, Result251.class, new HttpUtils.RequestListener<Result251>() {
                @Override
                public void onSuccess(Result251 result) {
                    if (finalI < 2) {
                        childList.get(0).get(finalI).setPeople(result.getBusCapacity());
                        childList.get(1).get(finalI).setPeople(result.getBusCapacity());
                    }
                    peopleSum += result.getBusCapacity();
                    mTvPeoplesum.setText("当前承载能力"+peopleSum+"人");
                    isFinish();
                }

                @Override
                public void onFailure(String msg) {

                }
            });
        }
    }

    private void getStation() {

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            map.put("BusStationId", String.valueOf(i + 1));
            final int finalI = i;
            HttpUtils.request(UrlUtils.url271, map, Result271.class, new HttpUtils.RequestListener<Result271>() {
                @Override
                public void onSuccess(Result271 result) {
                    List<Result271.ROWSDETAIL> results = result.getROWSDETAIL().subList(0, 2);
                    for (int j = 0; j < results.size(); j++) {
                        childList.get(finalI).get(j).setBusId(results.get(j).getBusId());
                        childList.get(finalI).get(j).setDistance(results.get(j).getDistance());
                        childList.get(finalI).get(j).setTime((int) Math.ceil(results.get(j).getDistance() / (20 * 1000 / 60f)));
                    }
                    isFinish();
                }

                @Override
                public void onFailure(String msg) {
                    LoadingDialog.showToast(msg);
                }
            });
        }
    }

    private void isFinish() {
        times++;
        if (times == 15) {
            Collections.sort(dataList1, new Comparator<F10>() {
                @Override
                public int compare(F10 o1, F10 o2) {
                    return o1.getDistance() - o2.getDistance();
                }
            });
            Collections.sort(dataList2, new Comparator<F10>() {
                @Override
                public int compare(F10 o1, F10 o2) {
                    return o1.getDistance() - o2.getDistance();
                }
            });
            expandAdapter.notifyDataSetChanged();
        }
    }

    private void initView(View view) {
        mBtn = (Button) view.findViewById(R.id.btn);
        mExlv = (ExpandableListView) view.findViewById(R.id.exlv);

        mBtn.setOnClickListener(this);

        expandAdapter = new ExpandAdapter<String, F10>(getActivity()
                , groupList, childList
                , R.layout.fragment_layout10_group_item
                , R.layout.fragment_layout10_child_item) {
            @Override
            public void convertGroup(CommonViewHolder holder, String item, int position) {
                holder.setText(R.id.tv_sta_name, item);
            }

            @Override
            public void convertChild(CommonViewHolder holder, F10 item, int position) {
                holder.setText(R.id.tv_busid, item.getBusId() + "号");
                holder.setText(R.id.tv_people, "(" + item.getPeople() + ")人");
                holder.setText(R.id.tv_bustime, item.getTime() + "分钟到达");
                holder.setText(R.id.tv_distance, "距离站台" + item.getDistance() + "米");
            }
        };

        mExlv.setAdapter(expandAdapter);
        int x = expandAdapter.getGroupCount();
        for (int i = 0; i < x; i++) {
            mExlv.expandGroup(i);
        }

        dataList1.add(new F10());
        dataList1.add(new F10());

        dataList2.add(new F10());
        dataList2.add(new F10());

        childList.add(dataList1);
        childList.add(dataList2);
        groupList.add("中医院站");
        groupList.add("联想大厦");

        mTvPeoplesum = (TextView) view.findViewById(R.id.tv_peoplesum);
        mTvPeoplesum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:

                break;
        }
    }
}