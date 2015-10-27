package com.foguangshan.sanbaotemple.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.foguangshan.modellibrary.Book;
import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple.adapter.BannerAdapter;
import com.foguangshan.sanbaotemple.adapter.BookListAdapter;
import com.foguangshan.sanbaotemple.helper.FragmentHelper;
import com.foguangshan.sanbaotemple.helper.IconicFontHelper;

import com.foguangshan.widgetlibrary.PullListView;
import com.foguangshan.widgetlibrary.ActionBar;

import java.util.List;

/**
 * Created by MacBookPro on 3/16/15.
 */

public class HomePage extends Fragment {

    private PullListView mPullListView;
    private ViewPager viewPager;
    private BannerAdapter  myViewPagerAdapter;
    private ActionBar mActionBar;

    private BookListAdapter myBookListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgs_books_listview, container, false);

//        myViewPagerAdapter = new BannerAdapter(getActivity());
//        myViewPagerAdapter.setDotsLayout((LinearLayout) view.findViewById(R.id.viewPagerCountDots));
//        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
//        viewPager.setAdapter(myViewPagerAdapter);
//        viewPager.setCurrentItem(0);
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                myViewPagerAdapter.updateDotsLayout(position);
//            }
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//            }
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//
//            }
//        });

        myBookListAdapter = new BookListAdapter(getActivity());
        mPullListView = (PullListView) view.findViewById(R.id.plv_data);
        mPullListView.setAdapter(myBookListAdapter);
        mPullListView.setOnRefreshListener(new PullListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myBookListAdapter.getPostDelayed(true,mPullListView);
            }
        });
        mPullListView.setOnGetMoreListener(new PullListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                myBookListAdapter.getPostDelayed(false,mPullListView);
            }
        });
        mPullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View views, int position, long id) {
                mActionBar = (ActionBar) getActivity().findViewById(R.id.actionbar);

                Book item = (Book) myBookListAdapter.getItem(position - 1);


                mActionBar.setTitle(item.getBookTitle());
                mActionBar.setImageDrawable(IconicFontHelper.getTextDrawableIconicFont(getActivity(),R.string.fa_arrow_left,R.color.white,20));
                //FragmentHelper.animationFragment(getActivity(),R.id.frame_container, new HomePage(), new BookChapters().setId(Integer.parseInt(String.valueOf(id))) ,10);

                FragmentHelper.animationFragment(getActivity(), R.id.frame_container, new BookListGridView(), new BookChaptersFragment().setBookId(Integer.parseInt(String.valueOf(id))), 10);

            }
        });
        mPullListView.performRefresh();

        return view;
    }
}
