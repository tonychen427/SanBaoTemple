package com.foguangshan.sanbaotemple;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import com.foguangshan.sanbaotemple.adapter.ImageAdapter;
import com.foguangshan.widgetlibrary.pullrefresh.component.PulldownToRefreshHeader;
import com.foguangshan.widgetlibrary.pullrefresh.component.PullupToRefreshFooter;
import com.foguangshan.widgetlibrary.pullrefresh.widget.PullGridView;



/**
 * Created by MacBookPro on 3/25/15.
 */
public class PullRefreshGridActivity extends Activity {
    private PullGridView mPullGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fgs_books_gridview);
        mPullGridView = (PullGridView)findViewById(R.id.pullGridView);

        mPullGridView.getPullView().setAdapter(new ImageAdapter(this, getResources().getStringArray(R.array.urls_small)));
        mPullGridView.getPullView().setNumColumns(3);

        mPullGridView.setPullHeaderView(new PulldownToRefreshHeader(getBaseContext(), new PulldownToRefreshHeader.OnRefreshListener() {
            @Override
            public void onRefresh(final PulldownToRefreshHeader pulldownToRefreshHeader) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pulldownToRefreshHeader.complete();
                    }
                }, 5000);
            }
        }));

        mPullGridView.setPullFooterView(new PullupToRefreshFooter(getBaseContext(), new PullupToRefreshFooter.OnRefreshListener() {
            @Override
            public void onRefresh(final PullupToRefreshFooter pullupToRefreshFooter) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullupToRefreshFooter.complete();
                    }
                }, 5000);
            }
        }));

        mPullGridView.triggerHeader();
    }
}
