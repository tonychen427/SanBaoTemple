package com.foguangshan.sanbaotemple.helper;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class TypefaceSpan extends MetricAffectingSpan {

    private Typeface mTypeface;
    private float textSize;
    private int textColor;

    public TypefaceSpan(Context context, Typeface typeface, float textSize, int color) {
        this.textSize = textSize;
        this.textColor = color;
        this.mTypeface = typeface;
    }

    @Override
    public void updateMeasureState(TextPaint p) {
        p.setTypeface(mTypeface);
        p.setTextSize(textSize);
        p.setColor(textColor);
        p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    @Override
    public void updateDrawState(TextPaint p) {
        p.setTypeface(mTypeface);
        p.setTextSize(textSize);
        p.setColor(textColor);
        p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
}
