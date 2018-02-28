package com.msharytech.msharymacros;

import android.content.Context;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mshary on 2/25/18.
 */

public class Utils {
    public static Context mContext= null;

    public static Context getContext() {

        return mContext;
    }

    public static void setContext(Context context){
        mContext=context;
    }

    public static long getCurrentTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    public static long getTimeStampToDays(Long timestamp){
        long diffSec=getCurrentTimeStamp()-timestamp;
        int day = (int) TimeUnit.MILLISECONDS.toDays(diffSec);
        return day;
    }

    public static String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }


}

