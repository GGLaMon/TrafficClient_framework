package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.mad.trafficclient.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_6 extends Fragment {
    private ViewPager mPager;
    private RadioGroup mTab;
    private List<Fragment> fragmentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout06, container, false);
        initView(view);
        initViewPager();
        if (getArguments()!=null){
            mPager.setCurrentItem(getArguments().getInt("index",0));
        }
        return view;
    }

    private void initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_6_1());
        fragmentList.add(new Fragment_6_2());
        fragmentList.add(new Fragment_6_3());
        fragmentList.add(new Fragment_6_4());
        fragmentList.add(new Fragment_6_5());
        fragmentList.add(new Fragment_6_6());

        final List<Integer> tabs = new ArrayList<>();
        tabs.add(R.id.tab1);
        tabs.add(R.id.tab2);
        tabs.add(R.id.tab3);
        tabs.add(R.id.tab4);
        tabs.add(R.id.tab5);
        tabs.add(R.id.tab6);

        mPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTab.check(tabs.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mPager.setCurrentItem(tabs.indexOf(checkedId));
            }
        });
    }

    private void initView(View view) {
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mTab = (RadioGroup) view.findViewById(R.id.tab);
    }
}
