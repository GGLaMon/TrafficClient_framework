package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.mad.trafficclient.R;
import com.mad.trafficclient.result.Result215;
import com.mad.trafficclient.result.Result281;
import com.mad.trafficclient.result.Result282;
import com.mad.trafficclient.result.Result292;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fragment_15 extends Fragment {
    private ViewPager pager;
    private RadioGroup tab;
    private List<Result215.ROWSDETAIL> peccancyList = new ArrayList<>();//所有违章记录
    private List<Result281.ROWSDETAIL> carInfoList = new ArrayList<>();//所有车辆信息
    private List<Result282.ROWSDETAIL> typeList = new ArrayList<>();//违章代码
    private List<Result292.ROWSDETAIL> userList = new ArrayList<>();//所有用户信息

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout15, container, false);
        initView(view);
        initPager();
        return view;
    }

    private void initPager() {
        List<Integer> tabs = new ArrayList<>();
        tabs.add(R.id.tab1);
        tabs.add(R.id.tab2);
        tabs.add(R.id.tab3);
        tabs.add(R.id.tab4);
        tabs.add(R.id.tab5);
        tabs.add(R.id.tab6);
        tabs.add(R.id.tab7);

        Bundle bundle = new Bundle();
        bundle.putSerializable("peccancy", (Serializable) peccancyList);
    }

    private void initView(View view) {
        pager = (ViewPager) view.findViewById(R.id.pager);
        tab = (RadioGroup) view.findViewById(R.id.tab);
    }
}
