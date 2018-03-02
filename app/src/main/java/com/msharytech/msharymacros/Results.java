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

import static com.msharytech.msharymacros.Utils.getEmojiByUnicode;



public class Results extends AppCompatActivity {
    TextView textviewCarb, textviewrotien, textviewfat, total, intake;
    Button back;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

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
        RealmManager.getInstance().save(user,User.class);
        Result result = new Calculate().Macros(user);
        total.setText(getString(R.string.YourBodyBurn) + getEmojiByUnicode(0x1F525) + " " + (int) result.getCalBurned() +" "+ getString(R.string.caldAY));
        intake.setText(getString(R.string.YouShoudEat)+" " + getEmojiByUnicode(0x1F957) + " " + (int) result.getIntake() +" "+ getString(R.string.caldAY));
        textviewCarb.setText(getString(R.string.Carb) +" "+ (int) result.getCarb() + " g  " + getEmojiByUnicode(0x1F35E));
        textviewfat.setText(getString(R.string.fat)+" " + (int) result.getFat() + " g  " + getEmojiByUnicode(0x1F95C));
        textviewrotien.setText(getString(R.string.protien) + " "+(int) result.getProtein()+ " g  " + getEmojiByUnicode(0x1F356));
        Log.e("FINAL", String.valueOf(result.getCarb()));
        Log.e("FINAL", String.valueOf(result.getFat()));
        Log.e("FINAL", String.valueOf(result.getProtein()));
        try {
            RealmManager.getInstance().save(result,Result.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, ChooserActivity.class);
                //i.putExtra("user", (Serializable) user);
                startActivity(i);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Results.this, Datainput.class);
        //i.putExtra("user", (Serializable) user);
        startActivity(i);
    }


}
