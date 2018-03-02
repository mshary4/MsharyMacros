package com.msharytech.msharymacros;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Welcome extends AppCompatActivity {
    public static Context mainContext;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor EditShared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView state;
        Button done;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        App.setContext(getApplicationContext());


        final CheckBox checkBoxAgian = (CheckBox) findViewById(R.id.checkBoxAgian);
        done = (Button) findViewById(R.id.doneButton);
         sharedPreferences = getPreferences(MODE_PRIVATE);
          EditShared = sharedPreferences.edit();

        if (Visted()) {
            Intent intent = new Intent();
            intent.setClass(Welcome.this, ChooserActivity.class);
            startActivity(intent);

        }

        checkBoxAgian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxAgian.isChecked()) {
                    EditShared.putBoolean("visted", true);
                    EditShared.apply();
                } else if (!checkBoxAgian.isChecked()) {
                    EditShared.putBoolean("visted", false);
                    EditShared.apply();
                }
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Welcome.this, ChooserActivity.class);
                startActivity(intent);


            }
        });

    }








    public boolean Visted(){
        return sharedPreferences.getBoolean("visted",false);
    }


}


