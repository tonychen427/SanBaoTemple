package com.foguangshan.sanbaotemple.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.foguangshan.businessprovider.IBookProvider;
import com.foguangshan.modellibrary.BookChapter;
import com.foguangshan.modellibrary.Component;
import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple._core.DaggerSanBaoTempleComponent;
import com.foguangshan.sanbaotemple._core.SanBaoTempleModule;
import com.foguangshan.sanbaotemple.fragment.MoviePosterFragment;
import com.foguangshan.widgetlibrary.draggable.DraggablePanel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MacBookPro on 3/30/15.
 */
public class BookChapterAdapter  extends PagerAdapter {


    public Activity mActivity;
    public int mItemId;

    public List<BookChapter> mBookChapterList;
    public TextView mTextView;
    public YouTubeThumbnailView mYouTubeThumbnailView;
    public YouTubeThumbnailLoader mYouTubeThumbnailLoader;
    public String API_Key = "AIzaSyC1rMU-mkhoyTvBIdTnYU0dss0tU9vtK48";

    public LayoutInflater mLayoutInflater;
    public View mView;
    public LinearLayout mLinearLayout;

    public YouTubePlayer mYoutubePlayer;
    public YouTubePlayerFragment mYouTubePlayFragment;
    public DraggablePanel mDraggablePanel;

    @Inject
    IBookProvider _bookProvider;
    public BookChapterAdapter (Activity activity, int itemId){
        DaggerSanBaoTempleComponent.builder()
                .sanBaoTempleModule(new SanBaoTempleModule())
                .build()
                .initializeBookChapterAdapter(this);
        this.mItemId = itemId;
        this.mBookChapterList = _bookProvider.getBookChapterListProvider(mItemId);
        this.mActivity = activity;
    }

    public BookChapterAdapter (Activity activity, int itemId, YouTubePlayerFragment youtubeFragment,YouTubePlayer youtubePlayer, DraggablePanel draggablePanel){

        this(activity,itemId);
        mYouTubePlayFragment = youtubeFragment;
        mDraggablePanel = draggablePanel;
        mYoutubePlayer = youtubePlayer;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBookChapterList.get(position).getTitle();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

        BookChapter mBookChapter = mBookChapterList.get(position);

        mLayoutInflater = (LayoutInflater)  mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mLayoutInflater.inflate(R.layout.fgs_chapters_components, container, false);
        mLinearLayout = (LinearLayout) mView.findViewById(R.id.bookChapters);
        mLinearLayout.setId(position+1);

        final List<Component> mComponent = mBookChapter.getComponentList();

        for (int i = 0; i < mComponent.size() ; i++){
            final String mValue = mComponent.get(i).getValue();
            Component.ComponentType mComponentType = mComponent.get(i).getComponentType();
            switch (mComponentType){
                case TextView:
                {

                    mTextView = new TextView(mActivity);
                    mTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    mTextView.setText(mValue);
                    // textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
                    mTextView.setPadding(24, 20, 24, 20);// in pixels (left, top, right, bottom)
                    mTextView.setLineSpacing(1, 1);
                    mTextView.setTextSize(16);
                    mLinearLayout.addView(mTextView);

                    break;

                }
                case ImageView:
                {
                    ImageView mImageView = new ImageView(mActivity);
                    mImageView.setLayoutParams(
                            new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                    mImageView.setAdjustViewBounds(true);


                    //mImageView.setPadding(10, 10, 10, 10);

                    Picasso.with(mActivity).load("http://www.clientsbox.com"+ mValue).into(mImageView);

                    mLinearLayout.addView(mImageView);
                    break;
                }
                case YouTube:
                {
                    mYouTubeThumbnailView = new YouTubeThumbnailView(mActivity);
                    mYouTubeThumbnailView.setLayoutParams(
                            new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                    mYouTubeThumbnailView.setAdjustViewBounds(true);
                    mYouTubeThumbnailView.setPadding(10, 10, 10, 10);
                    mYouTubeThumbnailView.initialize(API_Key, new YouTubeThumbnailView.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                            mYouTubeThumbnailLoader = youTubeThumbnailLoader;
                            mYouTubeThumbnailLoader.setVideo(mValue);
                        }

                        @Override
                        public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                            Toast.makeText(mActivity, "YouTubeThumbnailView.onInitializationFailure()", Toast.LENGTH_LONG).show();
                        }
                    });
                    mYouTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(mActivity,mValue,Toast.LENGTH_SHORT).show();
                            mDraggablePanel.setVisibility(View.VISIBLE);
                            mDraggablePanel.maximize();
                            //mYouTubePlayFragment.onDestroy();
                            //mYouTubePlayFragment = new YouTubePlayerFragment();
                            mYouTubePlayFragment.initialize(API_Key, new YouTubePlayer.OnInitializedListener() {
                                @Override public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                              YouTubePlayer player, boolean wasRestored) {
                                    //if (!wasRestored) {
                                        //mYoutubePlayer = player;
                                        player.loadVideo(mValue);
                                       // mYoutubePlayer.setShowFullscreenButton(true);
                                   // }

                                    Toast.makeText(mActivity,"success - replace : " + mValue, Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                                    Toast.makeText(mActivity,"false - replace : " + mValue, Toast.LENGTH_LONG).show();
                                }
                            });
                            //mDraggablePanel.setTopFragment(mYouTubePlayFragment);


                        }
                    });
                    mLinearLayout.addView(mYouTubeThumbnailView);
                    break;
                }
            }
        }

        ((ViewPager) container).addView(mView);

        return mView;
    }
    @Override
    public int getCount() {
        return mBookChapterList.size();
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
}
