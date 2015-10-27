package com.foguangshan.sanbaotemple.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.foguangshan.businessprovider.IBookProvider;
import com.foguangshan.modellibrary.Banner;
import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple._core.AppController;
import com.foguangshan.sanbaotemple._core.DaggerSanBaoTempleComponent;
import com.foguangshan.sanbaotemple._core.SanBaoTempleModule;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MacBookPro on 3/20/15.
 */
public class BannerAdapter extends PagerAdapter {
    private Activity mActivity;
    private int dotsCount;
    private TextView[] dots;

    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private List<Banner> mBannerList;

    private int Id;

    @Inject
    IBookProvider _bookProvider;


    public BannerAdapter(Activity activity , int id){
        DaggerSanBaoTempleComponent.builder()
                .sanBaoTempleModule(new SanBaoTempleModule())
                .build()
                .initializeBannerListAdapter(this);
        this.mBannerList = _bookProvider.getBannerListProvider();
        this.Id = id;
        this.mActivity = activity;
    }

    public BannerAdapter(Activity activity, List<Banner> bannerList){
        this.mActivity = activity;
        this.mBannerList = bannerList;
    }
    public BannerAdapter(Activity activity) {
        DaggerSanBaoTempleComponent.builder()
                .sanBaoTempleModule(new SanBaoTempleModule())
                .build()
                .initializeBannerListAdapter(this);
        this.mBannerList = _bookProvider.getBannerListProvider();
        this.mActivity =  activity;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBannerList.get(position).getBannerTitle();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater)  mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = layoutInflater.inflate(R.layout.fgs_banner_items, container, false);

//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//        NetworkImageView thumbNail = (NetworkImageView) mView
//                .findViewById(R.id.thumbnail);
//
//        // thumbNail.setImageUrl("http://api.androidhive.info/json/movies/" + (position + 1) +".jpg", imageLoader);
//        thumbNail.setImageUrl(mBannerList.get(position).getBannerImageUrl(), imageLoader);

        ImageView mImageView = (ImageView) mView.findViewById(R.id.adsImageView);
        Picasso.with(mActivity).load(mBannerList.get(position).getBannerImageUrl()).into(mImageView);

        ((ViewPager) container).addView(mView);

        return mView;
    }

    @Override
    public int getCount() {
        return mBannerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((View) obj);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        ((ViewPager) container).removeView(view);
    }

    public void setDotsLayout (LinearLayout mDotLayout){

        LinearLayout dotsLayout = mDotLayout;
        dotsCount = mBannerList.size();
        dots = new TextView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new TextView(mActivity);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(mActivity.getResources().getColor(android.R.color.darker_gray));
            dotsLayout.addView(dots[i]);
        }

        dots[0].setTextColor(mActivity.getResources().getColor(R.color.blue));
    }
    public void updateDotsLayout (int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setTextColor(mActivity.getResources().getColor(android.R.color.darker_gray));
        }
        dots[position].setTextColor(mActivity.getResources().getColor(R.color.blue));
    }
}
