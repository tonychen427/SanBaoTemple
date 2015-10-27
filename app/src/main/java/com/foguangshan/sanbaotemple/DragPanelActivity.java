package com.foguangshan.sanbaotemple;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.foguangshan.sanbaotemple.fragment.MoviePosterFragment;
import com.foguangshan.widgetlibrary.draggable.DraggableListener;
import com.foguangshan.widgetlibrary.draggable.DraggablePanel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;

/**
 * Created by MacBookPro on 4/6/15.
 */
public class DragPanelActivity extends FragmentActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyC1rMU-mkhoyTvBIdTnYU0dss0tU9vtK48";
    private static final String VIDEO_KEY = "ugRUx6MYbo";
    private static final String VIDEO_POSTER_THUMBNAIL =
            "http://4.bp.blogspot.com/-BT6IshdVsoA/UjfnTo_TkBI/AAAAAAAAMWk/JvDCYCoFRlQ/s1600/"
                    + "xmenDOFP.wobbly.1.jpg";
    private static final String SECOND_VIDEO_POSTER_THUMBNAIL =
            "http://media.comicbook.com/wp-content/uploads/2013/07/x-men-days-of-future-past"
                    + "-wolverine-poster.jpg";
    private static final String VIDEO_POSTER_TITLE = "X-Men: Days of Future Past";

    ImageView thumbnailImageView;
    DraggablePanel draggablePanel;

    private YouTubePlayer youtubePlayer;
    //private YouTubePlayerSupportFragment youtubeFragment;

    private YouTubePlayerFragment youtubeFragment;
    /**
     * Initialize the Activity with some injected data.
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_sample);
        thumbnailImageView = (ImageView) findViewById(R.id.iv_thumbnail);
        draggablePanel = (DraggablePanel) findViewById(R.id.draggable_panel);

        initializeYoutubeFragment();
        initializeDraggablePanel();
        hookDraggablePanelListeners();
    }

    /**
     * Method triggered when the iv_thumbnail widget is clicked. This method maximize the
     * com.foguangshan.widgetlibrary.draggable.DraggablePanel.
     */
//    @OnClick(R.id.iv_thumbnail) void onContainerClicked() {
//        draggablePanel.maximize();
//    }



    /**
     * Initialize the YouTubeSupportFrament attached as top fragment to the com.foguangshan.widgetlibrary.draggable.DraggablePanel widget and
     * reproduce the YouTube video represented with a YouTube url.
     */
    private void initializeYoutubeFragment() {
        //youtubeFragment = new YouTubePlayerSupportFragment();
        youtubeFragment = YouTubePlayerFragment.newInstance();
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

    /**
     * Initialize and configure the com.foguangshan.widgetlibrary.draggable.DraggablePanel widget with two fragments and some attributes.
     */
    private void initializeDraggablePanel() {
        draggablePanel.setFragmentManager(getFragmentManager());
        draggablePanel.setTopFragment(youtubeFragment);
        MoviePosterFragment moviePosterFragment = new MoviePosterFragment();
        moviePosterFragment.setPoster(VIDEO_POSTER_THUMBNAIL);
        moviePosterFragment.setPosterTitle(VIDEO_POSTER_TITLE);
        draggablePanel.setBottomFragment(moviePosterFragment);
        draggablePanel.initializeView();
        Picasso.with(this)
                .load(SECOND_VIDEO_POSTER_THUMBNAIL)
                .placeholder(R.drawable.xmen_placeholder)
                .into(thumbnailImageView);
    }

    /**
     * Hook the com.foguangshan.widgetlibrary.draggable.DraggableListener to com.foguangshan.widgetlibrary.draggable.DraggablePanel to pause or resume the video when the
     * DragglabePanel is maximized or closed.
     */
    private void hookDraggablePanelListeners() {
        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override public void onMaximized() {
                playVideo();
            }

            @Override public void onMinimized() {
                //Empty
            }

            @Override public void onClosedToLeft() {
                pauseVideo();
            }

            @Override public void onClosedToRight() {
                pauseVideo();
            }
        });
    }

    /**
     * Pause the video reproduced in the YouTubePlayer.
     */
    private void pauseVideo() {
        if (youtubePlayer.isPlaying()) {
            youtubePlayer.pause();
        }
    }

    /**
     * Resume the video reproduced in the YouTubePlayer.
     */
    private void playVideo() {
        if (!youtubePlayer.isPlaying()) {
            youtubePlayer.play();
        }
    }
}
