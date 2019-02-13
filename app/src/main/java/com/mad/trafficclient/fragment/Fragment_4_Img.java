/**
 *
 */
package com.mad.trafficclient.fragment;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mad.trafficclient.R;

public class Fragment_4_Img extends Fragment implements OnClickListener {

    private ImageView mImg;
    private Button mBtnBack;
    private ScaleGestureDetector detector;
    private int dw;
    private int dh;
    private int w;
    private int h;
    private Matrix matrix;
    private float initScale;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_layout04_img, container, false);

        initView(view);
        view.setClickable(true);//防止点击穿透
        return view;
    }

    private void initView(View view) {
        mImg = (ImageView) view.findViewById(R.id.img);
        mBtnBack = (Button) view.findViewById(R.id.btn_back);

        mBtnBack.setOnClickListener(this);

        if (getArguments() != null) {
            mImg.setImageResource(getArguments().getInt("img"));
        }

        detector = new ScaleGestureDetector(getContext(), new ScaleListener());
        mImg.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }

        });

        Drawable bd = mImg.getDrawable();
        dw = bd.getIntrinsicWidth();
        dh = bd.getIntrinsicHeight();

        matrix = mImg.getImageMatrix();

        mImg.post(new Runnable() {
            @Override
            public void run() {
                w = mImg.getWidth();
                h = mImg.getHeight();
                matrix.setTranslate((w - dw) / 2, (h - dh) / 2);//移动到中间
                matrix.postScale((float) h / dh, (float) h / dh, w / 2, h / 2);//缩放
                mImg.setImageMatrix(matrix);
                mImg.setVisibility(View.VISIBLE);
                initScale = getScale();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                getFragmentManager().beginTransaction().remove(Fragment_4_Img.this).commit();
                getFragmentManager().beginTransaction().show(new Fragment_4()).commit();
                break;
        }
    }

    public float getScale() {
        float[] values = new float[9];
        matrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }

    //缩放手势监听
    private class ScaleListener implements OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = getScale();
            float scaleFactor = detector.getScaleFactor();
            //放大限制
            if (scale >= 2 * initScale && scaleFactor > 1) {
                return true;
            }
            //缩小限制
            if (scale <= initScale && scaleFactor < 1) {
                return true;
            }
            matrix.postScale(scaleFactor, scaleFactor, w / 2, h / 2);
            mImg.setImageMatrix(matrix);
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}
