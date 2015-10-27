package com.foguangshan.sanbaotemple.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple.adapter.BookChapterAdapter;
import com.foguangshan.widgetlibrary.PagerSlidingTabStrip;
import com.foguangshan.widgetlibrary.draggable.DraggablePanel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;

/**
 * Created by MacBookPro on 4/7/15.
 */
public class BookChaptersFragment extends Fragment {


    private static final String YOUTUBE_API_KEY = "AIzaSyC1rMU-mkhoyTvBIdTnYU0dss0tU9vtK48";
    private static final String VIDEO_KEY = "gsjtg7m1MMM";
    private static final String VIDEO_POSTER_THUMBNAIL =
            "http://4.bp.blogspot.com/-BT6IshdVsoA/UjfnTo_TkBI/AAAAAAAAMWk/JvDCYCoFRlQ/s1600/xmenDOFP.wobbly.1.jpg";
    private static final String SECOND_VIDEO_POSTER_THUMBNAIL =
            "http://media.comicbook.com/wp-content/uploads/2013/07/x-men-days-of-future-past-wolverine-poster.jpg";
    private static final String VIDEO_POSTER_TITLE = "X-Men: Days of Future Past";


    private ImageView thumbnailImageView;
    private DraggablePanel draggablePanel;

    private YouTubePlayer youtubePlayer;
    private YouTubePlayerFragment youtubeFragment;


    private ViewPager mViewPager;
    private PagerSlidingTabStrip mSlidingTabs;

    private int mBookId;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.activity_youtube_sample2, container, false);

        thumbnailImageView = (ImageView) mView.findViewById(R.id.iv_thumbnail);
        draggablePanel = (DraggablePanel) mView.findViewById(R.id.draggable_panel);
        mViewPager = (ViewPager) mView.findViewById(R.id.pager);
        mSlidingTabs = (PagerSlidingTabStrip) mView.findViewById(R.id.tabs);

        //initializeYoutubeFragment();
        //initializeDraggablePanel();
        initializeAdapter(mBookId);

        return mView;
    }

    private void initializeYoutubeFragment() {
        youtubeFragment = new YouTubePlayerFragment();

        youtubeFragment.setRetainInstance(true);
        youtubeFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                          YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    youtubePlayer = player;
                    youtubePlayer.loadVideo(VIDEO_KEY);
                    youtubePlayer.setShowFullscreenButton(true);
                }
            }

            @Override public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                          YouTubeInitializationResult error) {
            }
        });
    }
    private void initializeDraggablePanel() {
        draggablePanel.setFragmentManager(getFragmentManager());
        draggablePanel.setTopFragment(youtubeFragment);
        draggablePanel.setTopViewHeight(300);
        MoviePosterFragment moviePosterFragment = new MoviePosterFragment();
        moviePosterFragment.setPoster(VIDEO_POSTER_THUMBNAIL);
        moviePosterFragment.setPosterTitle(VIDEO_POSTER_TITLE);
        draggablePanel.setBottomFragment(moviePosterFragment);
        draggablePanel.initializeView();
        draggablePanel.setVisibility(View.GONE);
        draggablePanel.closeToRight();
        draggablePanel.minimize();
    }

    private void initializeAdapter(int bookId) {
        mViewPager.setAdapter(new BookChapterAdapter(getActivity(), bookId, youtubeFragment, youtubePlayer, draggablePanel));
        mSlidingTabs.setViewPager(mViewPager);
    }

    public Fragment setBookId(int id) {
        mBookId = id;
        return this;
    }
}
