package com.foguangshan.sanbaotemple.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.foguangshan.businessprovider.IBookProvider;
import com.foguangshan.modellibrary.Book;
import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple._core.AppController;
import com.foguangshan.sanbaotemple._core.DaggerSanBaoTempleComponent;
import com.foguangshan.sanbaotemple._core.SanBaoTempleModule;
import com.foguangshan.sanbaotemple.fragment.HomePage;
import com.foguangshan.sanbaotemple.fragment.MyPageSlider;
import com.foguangshan.sanbaotemple.helper.FragmentHelper;
import com.foguangshan.sanbaotemple.helper.IconicFontHelper;
import com.foguangshan.widgetlibrary.PullListView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MacBookPro on 3/20/15.
 */
public class BookListAdapter extends BaseAdapter {
    @Inject
    IBookProvider _bookProvider;

    private Activity activity;
    private LayoutInflater inflater;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private List<Book> mBookList;

    public BookListAdapter(){

    }
    public BookListAdapter (Activity activity){
        this.activity = activity;
        DaggerSanBaoTempleComponent.builder()
                .sanBaoTempleModule(new SanBaoTempleModule())
                .build()
                .initializeBookListAdapter(this);
       // mBookList = _bookProvider.getBookListProvider();
         mBookList = _bookProvider.getSanBaoTempleBooks();
    }

    public BookListAdapter (Activity activity, List<Book> bookList){
        this.activity = activity;
        this.mBookList = bookList;
    }

    @Override
    public int getCount() {
        return mBookList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mBookList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

//        if (inflater == null)
//            inflater = (LayoutInflater) activity
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        if (convertView == null)
//            convertView = inflater.inflate(R.layout.list_row, null);
//
//
//        TextView title = (TextView) convertView.findViewById(R.id.title);
//        TextView rating = (TextView) convertView.findViewById(R.id.rating);
//        TextView genre = (TextView) convertView.findViewById(R.id.genre);
//        final TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
//
//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//
//        NetworkImageView thumbNail = (NetworkImageView) convertView
//                .findViewById(R.id.thumbnail);
//
//
//        thumbNail.setImageUrl(mBookList.get(position).getBookUrl(), imageLoader);
//        title.setText(mBookList.get(position).getBookTitle());
//        rating.setText(mBookList.get(position).getBookRating());
//        genre.setText(mBookList.get(position).getBookDescription());
//        year.setText(mBookList.get(position).getBookRating());
//
//        year.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(activity, "Like : " + year.getText(), Toast.LENGTH_LONG).show();
//            }
//        });

        Book mBook = mBookList.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.fgs_books_listview_items, null);

        TextView bookTitle = (TextView) convertView.findViewById(R.id.bookTitle);
      //  TextView title = (TextView) convertView.findViewById(R.id.name);
      //  TextView rating = (TextView) convertView.findViewById(R.id.timestamp);
        TextView genre = (TextView) convertView.findViewById(R.id.txtStatusMsg);
        final TextView year = (TextView) convertView.findViewById(R.id.txtUrl);
        //RatingBar mRatingBar  = (RatingBar) convertView.findViewById(R.id.ratingBar);

        //mRatingBar.setRating(Integer.parseInt(mBook.getBookRating()));


        TextView favoriteIcon = (TextView) convertView.findViewById(R.id.favorite);
        TextView commentsIcon = (TextView) convertView.findViewById(R.id.Comments);
        TextView shareIcon = (TextView) convertView.findViewById(R.id.share);

        favoriteIcon.setText(IconicFontHelper.getStringIconicFont(activity,"Favorite",R.string.fa_star,R.color.gold,30));
        commentsIcon.setText(IconicFontHelper.getStringIconicFont(activity,"Comments",R.string.fa_comment,R.color.blue,30));
        shareIcon.setText(IconicFontHelper.getStringIconicFont(activity,"Share",R.string.fa_share,R.color.green,30));

        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Favorite +1",Toast.LENGTH_SHORT).show();
            }
        });
        commentsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(activity,"Comment " + mBookList.get(position).getId(),Toast.LENGTH_SHORT).show();
                FragmentHelper.animationFragment(activity, R.id.frame_container, new HomePage(), new MyPageSlider(), 10);

            }
        });
        shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Share "  + mBookList.get(position).getId() ,Toast.LENGTH_SHORT).show();
            }
        });

//       if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//
//        NetworkImageView thumbNail = (NetworkImageView) convertView
//                .findViewById(R.id.profilePic);
//
//        thumbNail.setImageUrl(mBookList.get(position).getPublisherImageUrl(), imageLoader);

//        NetworkImageView bookImageUrl = (NetworkImageView) convertView
//                .findViewById(R.id.bookImageUrl);
//        bookImageUrl.setImageUrl(mBookList.get(position).getBookImageUrl(), imageLoader);


  //      ImageView mProfilePhoto = (ImageView) convertView.findViewById(R.id.profilePhoto);
 //       Picasso.with(activity).load("http://www.clientsbox.com"+mBookList.get(position).getPublisherImageUrl()).into(mProfilePhoto);
        ImageView mBookImage = (ImageView) convertView.findViewById(R.id.bookContentPhoto);
        Picasso.with(activity).load("http://www.clientsbox.com"+ mBookList.get(position).getBookImageUrl()).into(mBookImage);

        bookTitle.setText(mBookList.get(position).getBookTitle());
    //    title.setText(mBookList.get(position).getPublisherName());
    //    rating.setText(mBookList.get(position).getPublisherDate());
        genre.setText(mBookList.get(position).getBookDescription());
        year.setText(mBookList.get(position).getBookUrl());

        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Like : " + year.getText(), Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
    public void getPostDelayed(final boolean isRefresh, final PullListView plv){


       new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (isRefresh) {
                    mBookList.clear();
                    mBookList = _bookProvider.getSanBaoTempleBooks();
                }
                plv.refreshComplete();
                plv.getMoreComplete();
            }
        }, 2000);

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                if (isRefresh) {
//                    mBookList.clear();
//                    mBookList = _bookProvider.getBookListProvider();
//                }
//                plv.refreshComplete();
//                plv.getMoreComplete();
//            }
//        }, 2000);
    }
}
