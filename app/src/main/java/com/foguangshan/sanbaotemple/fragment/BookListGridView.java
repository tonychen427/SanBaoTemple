package com.foguangshan.sanbaotemple.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple.adapter.BookGridAdapter;
import com.foguangshan.sanbaotemple.adapter.ImageAdapter;
import com.foguangshan.sanbaotemple.helper.FragmentHelper;
import com.foguangshan.widgetlibrary.pullrefresh.component.PulldownToRefreshHeader;
import com.foguangshan.widgetlibrary.pullrefresh.component.PullupToRefreshFooter;
import com.foguangshan.widgetlibrary.pullrefresh.widget.PullGridView;

/**
 * Created by MacBookPro on 3/25/15.
 */
public class BookListGridView  extends Fragment {
    PullGridView mPullGridView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fgs_books_gridview, container, false);

        mPullGridView = (PullGridView) view.findViewById(R.id.pullGridView);

        mPullGridView.getPullView().setAdapter(new BookGridAdapter(getActivity()));
        mPullGridView.getPullView().setNumColumns(2);

        mPullGridView.setPullHeaderView(new PulldownToRefreshHeader(getActivity(), new PulldownToRefreshHeader.OnRefreshListener() {
            @Override
            public void onRefresh(final PulldownToRefreshHeader pulldownToRefreshHeader) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pulldownToRefreshHeader.complete();
                    }
                }, 2000);
            }
        }));

        mPullGridView.setPullFooterView(new PullupToRefreshFooter(getActivity(), new PullupToRefreshFooter.OnRefreshListener() {
            @Override
            public void onRefresh(final PullupToRefreshFooter pullupToRefreshFooter) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullupToRefreshFooter.complete();
                    }
                }, 2000);
            }
        }));
       mPullGridView.getPullView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(getActivity(),"hello : " + position, Toast.LENGTH_SHORT ).show();
               //FragmentHelper.animationFragment(getActivity(), R.id.frame_container, new BookListGridView(), new BookChaptersFragment(), 10);

           }
       });
        mPullGridView.triggerHeader();

        return view;
    }
}
