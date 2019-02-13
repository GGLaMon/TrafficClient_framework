/**
 *
 */
package com.mad.trafficclient.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioButton;

import com.mad.trafficclient.BaseApplication;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.CommonAdapter;
import com.mad.trafficclient.adapter.CommonViewHolder;
import com.mad.trafficclient.bean.F4;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Fragment_4 extends Fragment {
    private RadioButton mTab1;
    private RadioButton mTab2;
    private GridView mGridView;
    private CommonAdapter<F4> adapter;
    private List<F4> list = new ArrayList<>();
    private List<F4> list1 = new ArrayList<>();//视频
    private List<F4> list2 = new ArrayList<>();//图片
    private OnItemClickListener itemClickListener1;
    private OnItemClickListener itemClickListener2;
    private List<Uri> uriList = new ArrayList<>();
    private List<Integer> imgList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout04, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mTab1 = (RadioButton) view.findViewById(R.id.tab1);
        mTab2 = (RadioButton) view.findViewById(R.id.tab2);
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        adapter = new CommonAdapter<F4>(getActivity(), list, R.layout.fragment_layout04_item) {
            @Override
            public void convert(CommonViewHolder holder, F4 item, int position) {
                holder.setImg(R.id.img, item.getImg());
                holder.setText(R.id.tv_name, item.getName());
            }
        };
        mGridView.setAdapter(adapter);
        mTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                list.addAll(list1);
                adapter.notifyDataSetChanged();
                mGridView.setOnItemClickListener(itemClickListener1);
            }
        });
        mTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                list.addAll(list2);
                adapter.notifyDataSetChanged();
                mGridView.setOnItemClickListener(itemClickListener2);
            }
        });
    }

    private void initData() {
        itemClickListener1 = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = new Fragment_4_Video();
                Bundle bundle = new Bundle();
                bundle.putString("uri", uriList.get(position).toString());
                fragment.setArguments(bundle);
                if (!fragment.isAdded()) {
                    getFragmentManager().beginTransaction().add(R.id.maincontent, fragment).commit();
                } else {
                    getFragmentManager().beginTransaction().show(fragment).commit(
                    );
                    getFragmentManager().beginTransaction().hide(Fragment_4.this).commit();
                }
            }
        };
        itemClickListener2 = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = new Fragment_4_Img();
                Bundle bundle = new Bundle();
                bundle.putInt("img", imgList.get(position));
                fragment.setArguments(bundle);
                if (!fragment.isAdded()) {
                    getFragmentManager().beginTransaction().add(R.id.maincontent, fragment).commit();
                } else {
                    getFragmentManager().beginTransaction().show(fragment).commit(
                    );
                    getFragmentManager().beginTransaction().hide(Fragment_4.this).commit();
                }
            }
        };
        mGridView.setOnItemClickListener(itemClickListener1);
        list.clear();
        imgList.add(R.drawable.weizhang01);
        imgList.add(R.drawable.weizhang02);
        imgList.add(R.drawable.weizhang03);
        imgList.add(R.drawable.weizhang04);
        for (int i = 0; i < imgList.size(); i++) {
            list2.add(new F4("", ContextCompat.getDrawable(getContext(), imgList.get(i))));
        }
        Field[] fields = R.raw.class.getFields();
        for (Field field : fields) {
            String name = field.getName();
            uriList.add(getUri(name));
            list1.add(new F4(name, getThumbnail(getUri(name))));
        }
        list.addAll(list1);
        adapter.notifyDataSetChanged();
    }

    //根据id获取uri
    private Uri getUri(String name) {
        int id = 0;
        try {
            id = R.raw.class.getDeclaredField(name).getInt(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return Uri.parse("android.resource://" + getContext().getPackageName() + "/" + id);
    }

    //根据uri获取视频第一帧
    private Drawable getThumbnail(Uri videoUri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(BaseApplication.getInstance(), videoUri);
        Bitmap bmp = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        return new BitmapDrawable(bmp);
    }
}