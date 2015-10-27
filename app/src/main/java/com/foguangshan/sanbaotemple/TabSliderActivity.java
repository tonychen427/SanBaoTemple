package com.foguangshan.sanbaotemple;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.foguangshan.sanbaotemple.fragment.HomePage;
import com.foguangshan.widgetlibrary.PagerSlidingTabStrip;


/**
 * Created by MacBookPro on 3/27/15.
 */
public class TabSliderActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tabslider);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getFragmentManager()));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"Chapter 1","Chapter 2","Chapter 3","Chapter 4","Chapter 5"};

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case '0':
                    return new HomePage();
            }
            return new HomePage();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

    }
}
