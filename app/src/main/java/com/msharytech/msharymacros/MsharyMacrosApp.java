package com.msharytech.msharymacros;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Pedro on 15/9/2015.
 */
public class MsharyMacrosApp extends Application {

    private static Context mContext;
    final static RealmConfiguration relamconfg = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Realm.init(this);
//        RealmConfiguration relamconfg = new RealmConfiguration.Builder().build();
//        Realm.deleteRealm(relamconfg); // Clean slate
//        Realm.setDefaultConfiguration(relamconfg); // Make this Realm the default
    }

    public static Context getContext() {
        return mContext;
    }

}
