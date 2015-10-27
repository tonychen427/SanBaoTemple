package com.foguangshan.sanbaotemple.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple.adapter.ScreenSlidePagerAdapter;
import com.foguangshan.widgetlibrary.pageIndicator.PageIndicator;
import android.app.Fragment;

/**
 * Created by MacBookPro on 3/12/15.
 */
public class MyPageSlider extends Fragment{
    ViewPager mPager;
    PageIndicator mIndicator;
    ScreenSlidePagerAdapter mPagerAdapter;
    public MyPageSlider(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.simple_tab, container, false);
//        mPager = (ViewPager) rootView.findViewById(R.id.pager);
//        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
//        mPager.setAdapter(mPagerAdapter);
//
//        mIndicator = (TabPageIndicator) rootView.findViewById(R.id.indicator) ;
//        mIndicator.setViewPager(mPager);
        return rootView;
    }
}
