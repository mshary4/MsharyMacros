package com.msharytech.msharymacros;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.Serializable;
import java.util.Locale;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.msharytech.msharymacros.User;


public class movmentLevel extends AppCompatActivity implements RewardedVideoAdListener {
    User user = new User();
    private RewardedVideoAd mRewardedVideoAd = Datainput.mRewardedVideoAd;
    SharedPreferences.Editor sP = Welcome.EditShared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String CurrentLanguage = Locale.getDefault().getDisplayLanguage();

        setContentView(R.layout.activity_movment_level);
        Button buttonDone = (Button) findViewById(R.id.buttonResults);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarActivtyLevel);
        final TextView movmentLevel = (TextView) findViewById(R.id.textViewMovmentLevel);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        final TextView ActicityLevelDes = (TextView) findViewById(R.id.textViewActivityDes);
        ActicityLevelDes.setText(getActivityString(1));
        Intent i = getIntent();
        Bundle b = i.getExtras();


        if (b != null) {
            user = (User) b.get("user");


        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


//
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("VALUE", String.valueOf(seekBar.getProgress()));
                int progress = seekBar.getProgress();
                if (progress < 14) {
                    seekBar.setProgress(0);
                    user.setActivityLevel(1);
                    movmentLevel.setText("1");
                    ActicityLevelDes.setText(getActivityString(1));
                } else if (progress >= 15 && progress < 35) {
                    seekBar.setProgress(24);
                    user.setActivityLevel(2);
                    movmentLevel.setText("2");
                    ActicityLevelDes.setText(getActivityString(2));
                } else if (progress >= 36 && progress <= 60) {
                    seekBar.setProgress(50);
                    user.setActivityLevel(3);
                    movmentLevel.setText("3");
                    ActicityLevelDes.setText(getActivityString(3));
                } else if (progress >= 61 && progress < 78) {
                    seekBar.setProgress(75);
                    user.setActivityLevel(4);
                    movmentLevel.setText("4");
                    ActicityLevelDes.setText(getActivityString(4));
                } else if (progress >= 79) {
                    seekBar.setProgress(100);
                    user.setActivityLevel(5);
                    movmentLevel.setText("5");
                    ActicityLevelDes.setText(getActivityString(5));
                }
            }
        });

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int reward=getReward();
                if ((getReward() <= 0)) {
                    if (mRewardedVideoAd.isLoaded()) {
                        mRewardedVideoAd.show();
                    }else {

                        sP.putInt("results",--reward);
                        sP.apply();
                        Intent i = new Intent(movmentLevel.this, Results.class);
                        i.putExtra("user", (Serializable) user);
                        startActivity(i);

                    }
                } else {
                    sP.putInt("results",--reward);
                    sP.apply();
                    Intent i = new Intent(movmentLevel.this, Results.class);
                    i.putExtra("user", (Serializable) user);
                    startActivity(i);
                }

            }
        });
    }

    public String getActivityString(int i) {
        String ActivityString = "";
        switch (i) {
            case 1:
                ActivityString = getString(R.string.case1);
                break;
            case 2:
                ActivityString = getString(R.string.case2);
                break;
            case 3:
                ActivityString = getString(R.string.case3);
                break;
            case 4:
                ActivityString = getString(R.string.case4);
                break;
            case 5:
                ActivityString = getString(R.string.case5);
                break;
        }
        return ActivityString;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(movmentLevel.this, Datainput.class);
        startActivity(i);
    }


    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        int reward=rewardItem.getAmount();
        sP.putInt("results", --reward);
        sP.apply();
        Intent i = new Intent(movmentLevel.this, Results.class);
        i.putExtra("user", (Serializable) user);
        startActivity(i);
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    public int getReward() {
        return Welcome.sharedPreferences.getInt("results", 0);
    }
}
