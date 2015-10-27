package com.foguangshan.sanbaotemple.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.foguangshan.sanbaotemple.fragment.BookListGridView;
import com.foguangshan.sanbaotemple.fragment.HomePage;
import com.foguangshan.sanbaotemple.fragment.MyPageSlider;

/**
 * Created by MacBookPro on 3/27/15.
 */
public class PageSlideTabAdapter  extends FragmentPagerAdapter {

    private final String[] TITLES = {"Chapter 1","Chapter 2","Chapter 3","Chapter 4","Chapter 5"};

    public PageSlideTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new MyPageSlider();
            case 1:
                return new BookListGridView();
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