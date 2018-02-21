package com.msharytech.msharymacros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.msharytech.msharymacros.movmentLevel.mInterstitialAd;


public class Results extends AppCompatActivity {
    TextView textviewCarb, textviewrotien, textviewfat, total, intake;
    Button back;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        textviewCarb = (TextView) findViewById(R.id.textViewCarb);
        total = (TextView) findViewById(R.id.textViewTotalCal);
        textviewrotien = (TextView) findViewById(R.id.textViewProtien);
        textviewfat = (TextView) findViewById(R.id.textViewFat);
        intake = (TextView) findViewById(R.id.textViewIntake);
        back = (Button) findViewById(R.id.buttonStartOVer);
        Intent i = getIntent();
        Bundle b = i.getExtras();


        if (b != null) {
            user = (User) b.get("user");

        }
        Calculate calculate = new Calculate().Macros(user);
        total.setText(getString(R.string.YourBodyBurn) + getEmojiByUnicode(0x1F525) + " " + (int) calculate.DailyCaloriesBurned() +" "+ getString(R.string.caldAY));
        intake.setText(getString(R.string.YouShoudEat)+" " + getEmojiByUnicode(0x1F957) + " " + (int) calculate.Macros(user).getCaloriesintake() +" "+ getString(R.string.caldAY));
        textviewCarb.setText(getString(R.string.Carb) +" "+ (int) calculate.getCarb() + " g  " + getEmojiByUnicode(0x1F35E));
        textviewfat.setText(getString(R.string.fat)+" " + (int) calculate.getFat() + " g  " + getEmojiByUnicode(0x1F95C));
        textviewrotien.setText(getString(R.string.protien) + " "+(int) calculate.getProtien() + " g  " + getEmojiByUnicode(0x1F356));
        Log.e("FINAL", String.valueOf(calculate.getCarb()));
        Log.e("FINAL", String.valueOf(calculate.getFat()));
        Log.e("FINAL", String.valueOf(calculate.getProtien()));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, Datainput.class);
                //i.putExtra("user", (Serializable) user);
                startActivity(i);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Results.this, fatinput.class);
        //i.putExtra("user", (Serializable) user);
        startActivity(i);
    }

    public String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}
