/**
 *
 */
package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mad.trafficclient.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_16 extends Fragment {

    private RadioButton mTab1;
    private RadioButton mTab2;
    private RadioButton mTab3;
    private RadioGroup mTab;
    private ViewPager mPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout16, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTab1 = (RadioButton) view.findViewById(R.id.tab1);
        mTab2 = (RadioButton) view.findViewById(R.id.tab2);
        mTab3 = (RadioButton) view.findViewById(R.id.tab3);
        mTab = (RadioGroup) view.findViewById(R.id.tab);
        final List<Integer> tabs = new ArrayList<>();
        tabs.add(R.id.tab1);
        tabs.add(R.id.tab2);
        tabs.add(R.id.tab3);
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment_16_tab1());
        fragments.add(new Fragment_16_tab2());
        fragments.add(new Fragment_16_tab3());

        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
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
}