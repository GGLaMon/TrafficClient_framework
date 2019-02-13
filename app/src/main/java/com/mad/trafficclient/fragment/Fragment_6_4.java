/**
 *
 */
package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.j256.ormlite.dao.Dao;
import com.mad.trafficclient.R;
import com.mad.trafficclient.db.DB;
import com.mad.trafficclient.bean.F5_Sense;
import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.UrlUtils;
import com.mad.trafficclient.result.Result241;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_6_4 extends Fragment {
    private LineChart mChart;
    private TextView mTvTitle;
    private List<String> xVals = new ArrayList<>();
    private List<Entry> yVals = new ArrayList<>();
    private Timer timer;
    private LineDataSet lineDataSet;
    private Dao dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dao = DB.getInstance().getDao(F5_Sense.class);
        View view = inflater.inflate(R.layout.fragment_layout06_item, container, false);
        initView(view);
        initChart();
        getData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        timer();
    }

    private void initView(View view) {
        mChart = (LineChart) view.findViewById(R.id.chart);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvTitle.setText("CO2");
    }

    private void initChart() {
        mChart.getLegend().setEnabled(false);
        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getAxisLeft().setTextSize(20);
        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getXAxis().setDrawGridLines(false);
        mChart.getXAxis().setPosition(XAxisPosition.BOTTOM);
        mChart.getXAxis().setTextSize(18);
        mChart.getXAxis().setLabelsToSkip(0);
        lineDataSet = new LineDataSet(yVals, "");
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawValues(false);
        LineData lineData = new LineData(xVals, lineDataSet);
        mChart.setData(lineData);
        mChart.setDescription(null);
        mChart.setNoDataText("暂无历史数据");
        mChart.setExtraOffsets(50, 10, 50, 10);
        mChart.setTouchEnabled(false);
        mChart.invalidate();
    }

    private void getData() {
        xVals.clear();
        yVals.clear();
        SimpleDateFormat format = new SimpleDateFormat("mm:ss", Locale.CHINA);
        List<F5_Sense> list = new ArrayList<>();
        try {
            list = dao.queryBuilder().orderBy("id", false).limit(20L).where().eq("type", 4).query();
            Collections.sort(list, new Comparator<F5_Sense>() {
                @Override
                public int compare(F5_Sense o1, F5_Sense o2) {
                    return (int) (o1.getTime() - o2.getTime());
                }
            });
            for (int i = 0; i < list.size(); i++) {
                xVals.add(format.format(list.get(i).getTime()));
                yVals.add(new Entry(list.get(i).getValue(), i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() == 0) {
            return;
        }
        mChart.getLineData().notifyDataChanged();
        mChart.notifyDataSetChanged();
        mChart.invalidate();
    }

    private void timer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getSense();
            }
        }, 100, 3000);
    }

    private void getSense() {
        HttpUtils.request(UrlUtils.url241, new HashMap<String, String>(), Result241.class, new HttpUtils.RequestListener<Result241>() {
            @Override
            public void
            onSuccess(Result241 result) {
                F5_Sense sense = new F5_Sense();
                sense.setValue(result.getCo2());
                sense.setType(4);
                sense.setTime(System.currentTimeMillis());
                try {
                    dao.create(sense);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                getData();
            }

            @Override
            public void onFailure(String msg) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }
}