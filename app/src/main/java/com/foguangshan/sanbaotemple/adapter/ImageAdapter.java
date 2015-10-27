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
import com.foguangshan.sanbaotemple.R;
import com.foguangshan.sanbaotemple._core.AppController;
import com.foguangshan.sanbaotemple.helper.IconicFontHelper;

/**
 * Created by MacBookPro on 3/25/15.
 */
public class ImageAdapter extends BaseAdapter {
    private Activity activity;
    private String[] imageUrls;
    private LayoutInflater inflater;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ImageAdapter(Activity activity, String[] imageUrls){
        this.activity = activity;
        this.imageUrls = imageUrls;
    }

    @Override
    public Object getItem(int position) {
        return imageUrls[position];
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if(convertView == null){
//            viewHolder = new ViewHolder();
//            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_image, null);
//            viewHolder.image = (ImageView) convertView.findViewById(R.id.listItem_image);
//            convertView.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        //ImageLoader.getInstance().load(imageUrls[position], viewHolder.image);
//       // viewHolder.image.setImageDrawable(IconicFontHelper.getTextDrawableIconicFont((Activity)context, R.string.fa_bars, R.color.textColorPrimary_dark, 20));

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.fgs_books_gridview_items, null);

        TextView favoriteText = (TextView) convertView.findViewById(R.id.favorite);
        favoriteText.setText(IconicFontHelper.getStringIconicFont(activity,"Favorite",R.string.fa_star,R.color.gold,30));

        TextView favoriteIcon = (TextView) convertView.findViewById(R.id.share);
        favoriteIcon.setText(IconicFontHelper.getStringIconicFont(activity,"Share",R.string.fa_share,R.color.green,30));



        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.listItem_image);

        thumbNail.setImageUrl(imageUrls[position], imageLoader);

        return convertView;
    }


}