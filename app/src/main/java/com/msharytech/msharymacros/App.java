package com.msharytech.msharymacros;
import android.content.Context;

import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Mshary on 7/9/17.
 */

public class App {
    public static boolean Lb=false;
    public static boolean bodyfat=false;
    InterstitialAd mInterstitialAd;
    public static Context mContext;



    public static Context getContext() {

        return mContext;
    }

    public static void setContext(Context context){
        mContext=context;
    }
    public App() {
    }

    public static boolean isItLB(){
        return Lb;
    }



    public static boolean isBodyfat(){
        return bodyfat;
    }


    public InterstitialAd getmInterstitialAd() {
        return mInterstitialAd;
    }

    public void setmInterstitialAd(InterstitialAd mInterstitialAd) {
        this.mInterstitialAd = mInterstitialAd;
    }
}
