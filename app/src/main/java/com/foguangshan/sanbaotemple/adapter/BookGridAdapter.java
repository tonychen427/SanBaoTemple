package com.foguangshan.sanbaotemple.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.foguangshan.businessprovider.IBookProvider;
import com.foguangshan.modellibrary.Book;
import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple._core.AppController;
import com.foguangshan.sanbaotemple._core.DaggerSanBaoTempleComponent;
import com.foguangshan.sanbaotemple._core.SanBaoTempleModule;
import com.foguangshan.sanbaotemple.helper.IconicFontHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MacBookPro on 3/26/15.
 */
public class BookGridAdapter extends BaseAdapter {
    private Activity activity;
    private List<Book> mBookList;
    private LayoutInflater inflater;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Inject
    IBookProvider _bookProvider;
    public BookGridAdapter(Activity activity){
        this.activity = activity;
        DaggerSanBaoTempleComponent.builder()
                .sanBaoTempleModule(new SanBaoTempleModule())
                .build()
                .initializeBookGridAdapter(this);
        mBookList = _bookProvider.getSanBaoTempleBooks();
    }
    public BookGridAdapter(Activity activity, List<Book> bookList){
        this.activity = activity;
        this.mBookList = bookList;
    }

    @Override
    public Object getItem(int position) {
        return mBookList.get(position);
    }

    @Override
    public int getCount() {
        return mBookList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Book bookInfo = mBookList.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.fgs_books_gridview_items , parent, false);


        TextView bookContent = (TextView) convertView.findViewById(R.id.bookContent);
        bookContent.setText(bookInfo.getBookTitle());

        TextView favoriteText = (TextView) convertView.findViewById(R.id.favorite);
        favoriteText.setText(IconicFontHelper.getStringIconicFont(activity, "Favorite", R.string.fa_star, R.color.gold, 30));

        TextView favoriteIcon = (TextView) convertView.findViewById(R.id.share);
        favoriteIcon.setText(IconicFontHelper.getStringIconicFont(activity,"Share",R.string.fa_share,R.color.green,30));

       ImageView gridItemImageCircle = (ImageView)convertView.findViewById(R.id.gridItemImageCircle);

        Picasso.with(activity).load("http://www.clientsbox.com"+ bookInfo.getBookImageUrl()).into(gridItemImageCircle);


        //ImageView bookImage = (ImageView) convertView.findViewById(R.id.bookImage);

        //Picasso.with(activity).load("http://www.clientsbox.com"+ bookInfo.getBookImageUrl()).into(bookImage);

//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//
//        NetworkImageView thumbNail = (NetworkImageView) convertView
//                .findViewById(R.id.listItem_image);
//
//        thumbNail.setImageUrl("http://www.clientsbox.com" + bookInfo.getBookImageUrl(), imageLoader);

        return convertView;
    }


}