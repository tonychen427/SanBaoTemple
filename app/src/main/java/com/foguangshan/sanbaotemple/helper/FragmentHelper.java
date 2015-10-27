package com.foguangshan.sanbaotemple.helper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import com.foguangshan.widgetlibrary.FragmentTransactionExtended;

/**
 * Created by MacBookPro on 2/27/15.
 */
public class FragmentHelper {


    public static void addFragment(Activity activity, int inFrameLayoutId, Fragment fragment) {
        FragmentManager fm =  activity.getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        if (fragment != null) {
            ft.add(inFrameLayoutId, fragment);
            //ft.addToBackStack(null);
            ft.commit();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }

    }

    public static void removeFragment(Activity activity, Fragment fragment) {
        FragmentManager fm = activity.getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        if (fragment != null) {
            ft.remove(fragment);
            ft.commit();
            fm.popBackStack();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            fragment = null;
        }

    }

    public static void replaceFragment(Activity activity, int fragmentContainer, Fragment newFragment) {
        FragmentManager fm = activity.getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        try {
            ft.replace(fragmentContainer, newFragment);
            ft.addToBackStack(null);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void animationFragment(Activity activity, int fragmentContainer, Fragment mFirstFragment, Fragment mSecondFragment, int animType){
        if (activity.getFragmentManager().getBackStackEntryCount()==0) {
            FragmentManager fm = activity.getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            FragmentTransactionExtended fragmentTransactionExtended = new FragmentTransactionExtended(activity, fragmentTransaction, mFirstFragment, mSecondFragment, fragmentContainer);
            fragmentTransactionExtended.addTransition(animType);
            fragmentTransactionExtended.commit();

        }else{
            activity.getFragmentManager().popBackStack();
        }
    }

}