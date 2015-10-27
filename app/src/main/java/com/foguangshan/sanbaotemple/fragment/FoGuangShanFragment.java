package com.foguangshan.sanbaotemple.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple._core.AppController;

/**
 * Created by MacBookPro on 3/18/15.
 */
public class FoGuangShanFragment extends Fragment {

    View mView;
    String mUrl = "";
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    public static FoGuangShanFragment newInstance(String content) {
        FoGuangShanFragment mFoGuangShanFragment = new FoGuangShanFragment();
        mFoGuangShanFragment.mUrl = content;
        return mFoGuangShanFragment;
    };


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
}
