/**
 *
 */
package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.CommonAdapter;
import com.mad.trafficclient.adapter.CommonViewHolder;
import com.mad.trafficclient.db.DB;
import com.mad.trafficclient.bean.F1_History;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Fragment_3 extends Fragment implements OnClickListener {
    private Spinner mSpinner;
    private Button mBtnFind;
    private ListView mLv;
    private List<F1_History> list = new ArrayList<>();
    private TextView mTvNull;
    private CommonAdapter<F1_History> adapter;
    private Dao dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout03, container, false);
        initView(view);
        dao = DB.getInstance().getDao(F1_History.class);
        getData(true);
        return view;
    }

    private void initView(View view) {
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mBtnFind = (Button) view.findViewById(R.id.btn_find);
        mTvNull = (TextView) view.findViewById(R.id.tv_null);
        mLv = (ListView) view.findViewById(R.id.lv);

        mBtnFind.setOnClickListener(this);

        adapter = new CommonAdapter<F1_History>(getActivity(), list, R.layout.fragment_layout3_item) {
            @Override
            public void convert(CommonViewHolder holder, F1_History item, int position) {
                String time = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.CHINA).format(new Date(item.getTime()));
                holder.setText(R.id.tv_num, String.valueOf(position + 1));
                holder.setText(R.id.tv_car_id, item.getCarId());
                holder.setText(R.id.tv_money, String.valueOf(item.getMoney()));
                holder.setText(R.id.tv_username, item.getUserName());
                holder.setText(R.id.tv_pay_time, time);
            }
        };

        mLv.setAdapter(adapter);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.fragment_spinner_item, getResources().getStringArray(R.array.f3_sort));
        arrayAdapter.setDropDownViewResource(R.layout.fragment_spinner_dropdown_item);
        mSpinner.setAdapter(arrayAdapter);
        mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    getData(true);
                } else {
                    getData(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find:

                break;

        }

    }


    private void getData(boolean asc) {
        list.clear();
        try {
            list.addAll(dao.queryBuilder().orderBy("time", asc).query());
            adapter.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        if (list.size() == 0) {
            mTvNull.setVisibility(View.VISIBLE);

        }
    }
}
