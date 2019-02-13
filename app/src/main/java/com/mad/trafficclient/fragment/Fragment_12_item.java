/**
 *
 */
package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.CommonAdapter;
import com.mad.trafficclient.adapter.CommonViewHolder;
import com.mad.trafficclient.bean.F12;
import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.UrlUtils;
import com.mad.trafficclient.result.Result215;
import com.mad.trafficclient.util.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_12_item extends Fragment{


    private ListView mLv12;
    private List<F12> list = new ArrayList<>();
    private CommonAdapter<F12> adapter;
    private String num;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_layout12_item, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        Map<String, String> map = new HashMap<>();
        if (getArguments() != null) {
            num = getArguments().getString("number");

        } else {
            num = "10001";
        }
        map.put("carnumber", "鲁B" + num);
        HttpUtils.request(UrlUtils.url215, map, Result215.class, new HttpUtils.RequestListener<Result215>() {
            @Override
            public void onSuccess(Result215 result) {
                for (int i = 0; i < 10; i++) {
                    list.add(new F12(result.getROWSDETAIL().get(i).getCarnumber(),
                            result.getROWSDETAIL().get(i).getPcode(),
                            result.getROWSDETAIL().get(i).getPdatetime(),
                            result.getROWSDETAIL().get(i).getPaddr()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialog.showToast("没有查询到鲁" + num + "车的违章数据！");
            }
        });
    }


    private void initView(View view) {
        mLv12 = (ListView) view.findViewById(R.id.lv_12);
        adapter = new CommonAdapter<F12>(getActivity(),list,R.layout.fragment_layout012_item2) {
            @Override
            public void convert(CommonViewHolder holder, F12 item, int position) {
                holder.setText(R.id.carnumber12,item.getCarnumber());
                holder.setText(R.id.pcode12,item.getPcode());
                holder.setText(R.id.paddr12,item.getPaddr());
                holder.setText(R.id.pdatetime12,item.getPdatetime());
            }
        };
        mLv12.setAdapter(adapter);
        mLv12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_12_img()).commit();
            }
        });

    }
}
