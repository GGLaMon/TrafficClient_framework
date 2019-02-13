/**
 *
 */
package com.mad.trafficclient.fragment;

import com.j256.ormlite.dao.Dao;
import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.F1_History;
import com.mad.trafficclient.db.DB;
import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.UrlUtils;
import com.mad.trafficclient.result.Result213;
import com.mad.trafficclient.result.Result214;
import com.mad.trafficclient.util.LoadingDialog;
import com.mad.trafficclient.util.Util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Fragment_1 extends Fragment implements View.OnClickListener {
    private TextView mTvBalance;
    private Spinner mSpCarid;
    private EditText mEtRecharge;
    private Button mBtnQuery;
    private Button mBtnRecharge;
    private Dao dao;
    private int spinnerId = 1;
    private String money;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout01, container, false);
        initView(view);
        return view;
    }

    private void getbalance() {
        LoadingDialog.showDialog(getActivity());
        Map<String, String> p = new HashMap<>();
        p.put("CarId", String.valueOf(spinnerId));
        HttpUtils.request(UrlUtils.url213, p, Result213.class, new HttpUtils.RequestListener<Result213>() {
            @Override
            public void onSuccess(Result213 result) {
                mTvBalance.setText(result.getBalance() + "元");
                LoadingDialog.disDialog();
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialog.disDialog();
                LoadingDialog.showToast(msg);
            }
        });
    }

    private void initView(View view) {
        mTvBalance = (TextView) view.findViewById(R.id.tv_balance);
        mSpCarid = (Spinner) view.findViewById(R.id.sp_carid);
        mEtRecharge = (EditText) view.findViewById(R.id.et_recharge);
        mBtnQuery = (Button) view.findViewById(R.id.btn_query);
        mBtnRecharge = (Button) view.findViewById(R.id.btn_recharge);
        mBtnQuery.setOnClickListener(this);
        mBtnRecharge.setOnClickListener(this);
        dao = DB.getInstance().getDao(F1_History.class);
        mSpCarid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //将选择中的位置传给spinner
                spinnerId = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //对edittext 进行输入限制
        mEtRecharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int m = Integer.valueOf(mEtRecharge.getText().toString());
                if (m < 1) {
                    mEtRecharge.setText("1");
                    LoadingDialog.showToast("充值最小金额为1");
                }
                if (m > 999) {
                    mEtRecharge.setText("999");
                    LoadingDialog.showToast("充值最大金额为999");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                getbalance();
                break;
            case R.id.btn_recharge:
                submit();
                break;
        }
    }

    private void submit() {
// validate
        money = mEtRecharge.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            Toast.makeText(getActivity(), "充值金额不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        LoadingDialog.showDialog(getActivity(), "正在充值");
        Map<String, String> p = new HashMap<>();
        p.put("CarId", String.valueOf(spinnerId));
        p.put("Money", mEtRecharge.getText().toString());
        HttpUtils.request(UrlUtils.url214, p, Result214.class, new HttpUtils.RequestListener<Result214>() {
            @Override
            public void onSuccess(Result214 result) {
                LoadingDialog.showToast("充值成功");
                LoadingDialog.disDialog();
                getbalance();
                try {
                    dao.create(new F1_History(String.valueOf(spinnerId), Integer.parseInt(money), Util.getUser().getUserName(), System.currentTimeMillis()));
                    Log.e("DB", "success");
                } catch (SQLException e) {
                    e.printStackTrace();
                    Log.e("DB", "failue");
                }
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialog.showToast(msg);
                LoadingDialog.disDialog();
            }
        });
    }
}