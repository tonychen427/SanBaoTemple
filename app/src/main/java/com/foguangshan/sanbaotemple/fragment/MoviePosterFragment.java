package com.foguangshan.sanbaotemple.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.foguangshan.sanbaotemple.R;
import com.squareup.picasso.Picasso;

/**
 * Created by MacBookPro on 4/6/15.
 */
public class MoviePosterFragment extends Fragment {

    ImageView thumbnailImageView;

    private String videoPosterThumbnail;
    private String posterTitle;

    /**
     * Override method used to initialize the fragment.
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_poster, container, false);

        thumbnailImageView = (ImageView) view.findViewById(R.id.iv_thumbnail);
        Picasso.with(getActivity())
                .load(videoPosterThumbnail)
                .placeholder(R.drawable.xmen_placeholder)
                .into(thumbnailImageView);
        return view;
    }

    /**
     * Show the poster image in the thumbnailImageView widget.
     */
    public void setPoster(String videoPosterThumbnail) {
        this.videoPosterThumbnail = videoPosterThumbnail;
    }

    /**
     * Store the poster title to show it when the thumbanil view is clicked.
     */
    public void setPosterTitle(String posterTitle) {
        this.posterTitle = posterTitle;
    }

    /**
     * Method triggered when the iv_thumbnail widget is clicked. This method shows a toast with the
     * poster information.
     */
//    @OnClick(R.id.iv_thumbnail) void onThubmnailClicked() {
//        Toast.makeText(getActivity(), posterTitle, Toast.LENGTH_SHORT).show();
//    }


}