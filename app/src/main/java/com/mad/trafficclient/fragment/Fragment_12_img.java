/**
 *
 */
package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mad.trafficclient.R;

public class Fragment_12_img extends Fragment implements View.OnClickListener {


    private Button mBack12;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_layout12_img, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mBack12 = (Button) view.findViewById(R.id.back12);
        mBack12.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back12:
                getFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_12_item()).commit();
                break;
        }
    }
}
