package com.foguangshan.sanbaotemple.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;

import com.foguangshan.sanbaotemple.R;

/**
 * Created by MacBookPro on 3/20/15.
 */
public class IconicFontHelper {
    public static TextDrawable getTextDrawableIconicFont (Activity activity, int iconic_font , int iconic_color, int iconic_size) {
        Typeface font = Typeface.createFromAsset(activity.getAssets(), "fontawesomeWebfont.ttf");
        TextDrawable iconicFont = new TextDrawable(activity);
        iconicFont.setTypeface(font);
        iconicFont.setTextSize(iconic_size);
        iconicFont.setText(activity.getString(iconic_font));
        iconicFont.setTextColor(activity.getResources().getColor(iconic_color));
        return iconicFont;
    }

    public static SpannableString getStringIconicFont (Activity activity, String title, int iconic_font , int iconic_color, int iconic_size){
        Typeface font = Typeface.createFromAsset(activity.getAssets(), "fontawesomeWebfont.ttf");
        SpannableString s = new SpannableString(activity.getResources().getString(iconic_font) + " " + title);
        s.setSpan(
                new TypefaceSpan(activity, font, iconic_size, activity.getResources().getColor(iconic_color))
                , 0
                , s.length()
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;
    }
}
