package com.foguangshan.sanbaotemple.fragment;

import android.app.Activity;
import android.content.Context;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple.adapter.BookChapterAdapter;
import com.foguangshan.widgetlibrary.PagerSlidingTabStrip;

/**
 * Created by MacBookPro on 3/30/15.
 */
public class BookChapters extends Fragment {

    int mId;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fgs_chapters, container, false);

        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new BookChapterAdapter(getActivity() ,mId));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        return view;
    }
    public Fragment setId(int id) {
        mId = id;
        return this;
    }
}
