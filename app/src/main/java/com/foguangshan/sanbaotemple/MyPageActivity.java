

package com.foguangshan.sanbaotemple;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.foguangshan.businessprovider.IBookProvider;
import com.foguangshan.sanbaotemple._core.DaggerSanBaoTempleComponent;
import com.foguangshan.sanbaotemple._core.SanBaoTempleModule;
import com.foguangshan.widgetlibrary.pageIndicator.CirclePageIndicator;
import com.foguangshan.widgetlibrary.pageIndicator.PageIndicator;
import com.foguangshan.sanbaotemple.adapter.TestFragmentAdapter;
import java.util.Random;

import javax.inject.Inject;

/**
 * Created by MacBookPro on 3/12/15.
 */
public class MyPageActivity  extends FragmentActivity {
    private static final Random RANDOM = new Random();
    TestFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;

    @Inject
    IBookProvider _bookProvider;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSanBaoTempleComponent.builder()
                .sanBaoTempleModule(new SanBaoTempleModule())
                .build()
                .initializeMyPageActivity(this);

        int data = _bookProvider.getProviderData();
        setContentView(R.layout.simple_circle);

        mAdapter = new TestFragmentAdapter(getFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.random:
                final int page = RANDOM.nextInt(mAdapter.getCount());
                Toast.makeText(this, "Changing to page " + page, Toast.LENGTH_SHORT);
                mPager.setCurrentItem(page);
                return true;

            case R.id.add_page:
                if (mAdapter.getCount() < 10) {
                    mAdapter.setCount(mAdapter.getCount() + 1);
                    mIndicator.notifyDataSetChanged();
                }
                return true;

            case R.id.remove_page:
                if (mAdapter.getCount() > 1) {
                    mAdapter.setCount(mAdapter.getCount() - 1);
                    mIndicator.notifyDataSetChanged();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
