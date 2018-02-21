package com.msharytech.msharymacros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.Serializable;
import java.util.Locale;

import com.msharytech.msharymacros.User;

public class movmentLevel extends AppCompatActivity {
    User user = new User();
    public static InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String CurrentLanguage=Locale.getDefault().getDisplayLanguage();

        setContentView(R.layout.activity_movment_level);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8360364255923836/5348548506");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        Button buttonDone = (Button) findViewById(R.id.buttonResults);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarActivtyLevel);
        //if(CurrentLanguage.equals("ar"))
            //seekBar.setRotation(180);
        final TextView ActicityLevelDes= (TextView) findViewById(R.id.textViewActivityDes);
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
                Log.e("VALUE",String.valueOf(seekBar.getProgress()));
                int progress=seekBar.getProgress();
                if(progress<14){
                   seekBar.setProgress(0);
                    user.setActivityLevel(1);
                    ActicityLevelDes.setText(getActivityString(1));
                }
                else if(progress>=15 && progress<35){
                   seekBar.setProgress(24);
                    user.setActivityLevel(2);
                    ActicityLevelDes.setText(getActivityString(2));
                }
               else if(progress>=36 &&progress<=60){
                    seekBar.setProgress(50);
                    user.setActivityLevel(3);
                    ActicityLevelDes.setText(getActivityString(3));
               }
                else if(progress>=61 &&progress<78){
                    seekBar.setProgress(75);
                    user.setActivityLevel(4);
                    ActicityLevelDes.setText(getActivityString(4));
                }
               else if(progress>=79){
                    seekBar.setProgress(100);
                    user.setActivityLevel(5);
                    ActicityLevelDes.setText(getActivityString(5));
               }
            }
        });

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(movmentLevel.this,Results.class);
                i.putExtra("user", (Serializable) user);

               startActivity(i);

            }
        });
    }

    public String getActivityString(int i){
        String ActivityString="";
        switch (i){
            case 1:ActivityString= getString(R.string.case1);
                break;
            case 2:ActivityString=getString(R.string.case2);
                break;
            case 3:ActivityString=getString(R.string.case3);
                break;
            case 4:ActivityString=getString(R.string.case4);
                break;
            case 5:ActivityString=getString(R.string.case5);
                break;
        }
        return ActivityString;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(movmentLevel.this,Datainput.class);
        startActivity(i);
    }
}
