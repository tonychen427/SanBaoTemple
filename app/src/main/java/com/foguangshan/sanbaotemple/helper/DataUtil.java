package com.foguangshan.sanbaotemple.helper;

import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.foguangshan.widgetlibrary.PullListView;

/**
 * Created by MacBookPro on 3/16/15.
 */
public class DataUtil {

    public static void getData(final boolean isRefresh, final ArrayAdapter<String> aa, final PullListView plv) {

        //延迟加载数据，模拟耗时操作
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (isRefresh) {
                    aa.clear();
                }
                int currentCount = aa.getCount();
                for (int i = currentCount; i < currentCount + 20; i++) {
                    aa.add("第" + (i + 1) + "条");
                }

                aa.notifyDataSetChanged();
                plv.refreshComplete();
                plv.getMoreComplete();
            }
        }, 2000);
    }
    public static void getPostDelayed(final boolean isRefresh, final BaseAdapter ba, final PullListView plv){

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                plv.refreshComplete();
                plv.getMoreComplete();
            }
        }, 2000);
    }
}
