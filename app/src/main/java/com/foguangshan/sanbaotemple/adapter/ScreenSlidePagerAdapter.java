package com.foguangshan.sanbaotemple.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple.fragment.TestFragment;
import com.foguangshan.widgetlibrary.pageIndicator.IconPagerAdapter;

/**
 * Created by MacBookPro on 3/13/15.
 */
public class ScreenSlidePagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    protected final String[] CONTENT = new String[] { "This", "Is", "A", "Test", };
    protected final int[] ICONS = new int[] {
            R.drawable.yad,
            R.drawable.yad,
            R.drawable.yad,
            R.drawable.yad
    };

    protected int numb;
    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      //  return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
        return null;
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[index % ICONS.length];
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }

}