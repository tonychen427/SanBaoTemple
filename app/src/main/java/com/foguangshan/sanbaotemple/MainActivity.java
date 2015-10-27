package com.foguangshan.sanbaotemple;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.foguangshan.sanbaotemple.fragment.BookListGridView;
import com.foguangshan.sanbaotemple.fragment.HomePage;
import com.foguangshan.sanbaotemple.fragment.MyPageSlider;
import com.foguangshan.sanbaotemple.helper.FragmentHelper;
import com.foguangshan.sanbaotemple.helper.IconicFontHelper;
import com.foguangshan.sanbaotemple.helper.TextDrawable;
import com.foguangshan.widgetlibrary.ActionBar;
import com.foguangshan.widgetlibrary.SlideMenu;
import com.foguangshan.widgetlibrary.SlideMenuInterface;

public class MainActivity extends FragmentActivity implements SlideMenuInterface.OnSlideMenuItemClickListener {

    private SlideMenu slidemenu;
    private ActionBar actionBar;
    private TextDrawable iconic_bar;

    private Activity mActivity = this;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fgs_main_activity);

        mContext = this;

        InitializeViews();
        InitializeActionBar();
        InitializeSlidemenu();


        if (isConnected()) {
            Toast.makeText(mContext, "You are conncted", Toast.LENGTH_LONG).show();
            FragmentHelper.addFragment(mActivity, R.id.frame_container, new HomePage());
        } else {
            Toast.makeText(mContext, "You are not conncted", Toast.LENGTH_LONG).show();
        }
    }

    private void InitializeViews(){
        actionBar = (ActionBar) findViewById(R.id.actionbar);
        slidemenu = (SlideMenu) findViewById(R.id.slideMenu);
        iconic_bar = IconicFontHelper.getTextDrawableIconicFont(mActivity, R.string.fa_bars, R.color.white, 20);
    }

    private void InitializeActionBar(){

        actionBar.setHomeAction(HomeAction());
        actionBar.setHomeBtnClickListener(OnClickHomeListener());
        actionBar.setTitle("Home");
        actionBar.setImageDrawable(iconic_bar);
        actionBar.addIconAction(ListAllChapters());
        //actionBar.setHomeAction(new ActionBar.IntentAction(this, createIntent(this)));
        //actionBar.addIconAction(FlipGridListView());
        //actionBar.addIconAction(new ActionBar.IntentAction(this, createShareIntent(), IconicFontHelper.getTextDrawableIconicFont(this, R.string.fa_share, R.color.white, 20)));
    }

    private void InitializeSlidemenu(){
        slidemenu.init(this, R.menu.slide, this, 333);
        // this can set the menu to initially shown instead of hidden
        // slidemenu.setAsShown();
        slidemenu.setHeaderImage(getYADLogo());
    }

    private ActionBar.IntentAction HomeAction(){
        Intent i = new Intent(mContext, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        ActionBar.IntentAction ia = new ActionBar.IntentAction(mContext,i);
        return ia;
    }

    private View.OnClickListener OnClickHomeListener(){
        View.OnClickListener myClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imgName = view.getTag().toString();
                //Toast.makeText(mContext,imgName,Toast.LENGTH_LONG).show();

                if (imgName == "Home") {
                    slidemenu.show();
                } else {
                    actionBar.setTitle("Home");
                    actionBar.setImageDrawable(iconic_bar);
                    FragmentHelper.animationFragment(mActivity, R.id.frame_container, new HomePage(), new MyPageSlider(), 11);
                }

            }
        };
        return  myClick;
    }

    private ActionBar.Action ListAllChapters(){
        ActionBar.Action myAction = new ActionBar.Action(){

            @Override
            public int getDrawable() {
                return 0;
            }

            @Override
            public Drawable getIconicDrawable() {
                return IconicFontHelper.getTextDrawableIconicFont((Activity) mContext, R.string.fa_list, R.color.white, 20);
            }

            @Override
            public void performAction(View view) {

            }
        };
        return myAction;
    }

    @Override
    public void onSlideMenuItemClick(int itemId) {
        //startActivity(new Intent(mContext,MyPageActivity.class));
        //startActivity(new Intent(mContext, MyUIPageView.class));
        //startActivity(new Intent(mContext, GridViewPageActivity.class));
        //startActivity(new Intent(mContext,PullRefreshGridActivity.class));
        //startActivity(new Intent(mContext,TabSliderActivity.class));
        //startActivity(new Intent(mContext,ParallexActivity.class));
        //startActivity(new Intent(mContext, DragPanelActivity.class));
        System.out.print(itemId);
    }

    public Drawable getYADLogo() {
        Drawable dr = getResources().getDrawable(R.drawable.logo);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable yadLogo = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 300, 300, true));
        return yadLogo;
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
//    private Intent createShareIntent() {
//        final Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_INTENT, "Testing");
//        intent.putExtra(Intent.EXTRA_TEXT, "Shared from the ActionBar widget.");
//        return Intent.createChooser(intent, "Share");
//    }
//
//
//
//    private ActionBar.Action FlipGridListView() {
//        ActionBar.Action myAction = new ActionBar.Action() {
//            @Override
//            public int getDrawable() {
//                return 0;
//            }
//
//            @Override
//            public Drawable getIconicDrawable() {
//                return IconicFontHelper.getTextDrawableIconicFont((Activity) mContext, R.string.fa_th, R.color.textColorPrimary_dark, 20);
//            }
//
//            @Override
//            public void performAction(View view) {
//                //Toast.makeText(mContext,"Flip Grid View",Toast.LENGTH_LONG).show();
//                FragmentHelper.animationFragment(mActivity, R.id.frame_container, new HomePage(), new BookListGridView(), 13);
//            }
//        };
//        return myAction;
//    }

}
