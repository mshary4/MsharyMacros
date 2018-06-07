package com.msharytech.msharymacros;

import android.content.Context;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;

/**
 * Created by Mshary on 7/9/17.
 */

public class App {
    public static boolean Lb = false;
    public static boolean bodyfat = false;

    public static Context mContext;



    public static Context getContext() {

        return mContext;
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public App() {
    }



    public static boolean isLb() {
        return Lb;
    }

    public static void setLb(boolean lb) {
        Lb = lb;
    }

    public static boolean isBodyfat() {
        return bodyfat;
    }


    public boolean isItLB() {
        return isLb();
    }
}

