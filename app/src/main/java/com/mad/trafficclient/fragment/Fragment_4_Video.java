package com.mad.trafficclient.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.mad.trafficclient.R;

public class Fragment_4_Video extends Fragment implements View.OnClickListener {
    private VideoView mVideoView;
    private Button mBtnBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout04_video, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mVideoView = (VideoView) view.findViewById(R.id.video_view);
        mBtnBack = (Button) view.findViewById(R.id.btn_back);

        mBtnBack.setOnClickListener(this);

        MediaController mc = new MediaController(getContext());
        mVideoView.setMediaController(mc);

        if (getArguments()!=null){
            mVideoView.setVideoURI(Uri.parse(getArguments().getString("uri")));
            mVideoView.start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                getFragmentManager().beginTransaction().remove(Fragment_4_Video.this).commit();
                getFragmentManager().beginTransaction().show(new Fragment_4()).commit();
                break;
        }
    }
}
