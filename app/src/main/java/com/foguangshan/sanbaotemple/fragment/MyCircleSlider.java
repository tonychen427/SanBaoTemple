package com.foguangshan.sanbaotemple.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple.adapter.ScreenSlidePagerAdapter;
import com.foguangshan.widgetlibrary.pageIndicator.CirclePageIndicator;
import com.foguangshan.widgetlibrary.pageIndicator.PageIndicator;

/**
 * Created by MacBookPro on 3/13/15.
 */
public class MyCircleSlider  extends Fragment {
    ViewPager mPager;
    PageIndicator mIndicator;
    ScreenSlidePagerAdapter mPagerAdapter;
    public MyCircleSlider(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.simple_circle, container, false);
        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mIndicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator) ;
        mIndicator.setViewPager(mPager);
        return rootView;
    }
}