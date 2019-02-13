/**
 *
 */
package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.UrlUtils;
import com.mad.trafficclient.result.Result215;
import com.mad.trafficclient.util.LoadingDialog;

import java.util.HashMap;
import java.util.Map;

public class Fragment_12 extends Fragment implements View.OnClickListener {


    private EditText mEt12;
    private Button mBt12;
    private String number;
    private Bundle bundle;
    private Fragment fragment = new Fragment_12_item();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout12, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mEt12 = (EditText) view.findViewById(R.id.et_12);
        mBt12 = (Button) view.findViewById(R.id.bt_12);

        mBt12.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_12:
                submit();
                break;
        }
    }

    private void submit() {
        number = mEt12.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("carnumber", "鲁B" + number);
        HttpUtils.request(UrlUtils.url215, map, Result215.class, new HttpUtils.RequestListener<Result215>() {
            @Override
            public void onSuccess(Result215 result) {
                bundle = new Bundle();
                bundle.putString("number",number);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.maincontent, fragment).commit();
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialog.showToast("没有查询到鲁" + number + "车的违章数据！");
            }
        });
        // validate
        String mEt = mEt12.getText().toString().trim();
        if (TextUtils.isEmpty(mEt)) {
            Toast.makeText(getContext(), "12不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
