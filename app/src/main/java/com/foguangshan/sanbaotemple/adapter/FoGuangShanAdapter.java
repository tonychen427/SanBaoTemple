package com.foguangshan.sanbaotemple.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple._core.AppController;
import com.foguangshan.widgetlibrary.pageIndicator.IconPagerAdapter;

/**
 * Created by MacBookPro on 3/18/15.
 */
public class FoGuangShanAdapter extends FragmentPagerAdapter implements IconPagerAdapter  {
    private LayoutInflater inflater;
    private Activity activity;

    protected final String[] CONTENT = new String[] { "" +
              "http://newmultitech.com/Mockup/temple/001.jpg"
            , "http://newmultitech.com/Mockup/temple/002.jpg"
            , "http://newmultitech.com/Mockup/temple/003.jpg"
            , "http://newmultitech.com/Mockup/temple/004.jpg" };


    public FoGuangShanAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       // FoGuangShanFragment myFragment = new FoGuangShanFragment();
       // myFragment.mUrl = CONTENT[position % CONTENT.length];
       // return myFragment;
        return new FoGuangShanFragment().setUrl(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getIconResId(int index) {
        return 0;
    }

    @Override
    public int getCount() {
        return 4;
    }


    public static class FoGuangShanFragment extends Fragment {

        View mView;
        String mUrl = "";
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            mView = inflater.inflate(R.layout.foguangshanslider, container, false);

            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
            NetworkImageView thumbNail = (NetworkImageView) mView
                    .findViewById(R.id.thumbnail);

            // thumbNail.setImageUrl("http://api.androidhive.info/json/movies/" + (position + 1) +".jpg", imageLoader);
            thumbNail.setImageUrl(mUrl, imageLoader);
            return mView;
        }

        public Fragment setUrl (String url){
            mUrl = url;
            return this;
        }
    }
}
