package msharytech.msharymacros;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.Serializable;


import static msharytech.msharymacros.movmentLevel.mInterstitialAd;

public class fatinput extends AppCompatActivity {
    User user = new User();
    char c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fatinput);
        Button doneFat = (Button) findViewById(R.id.buttonFatDone);
        final EditText fatPer = (EditText) findViewById(R.id.input_fatper);
        ImageView imageView = (ImageView) findViewById(R.id.imageView6);

        Intent i = getIntent();
        Bundle b = i.getExtras();


        if (b != null) {
            user = (User) b.get("user");


        }


        if (b != null) {
            c = (char) b.get("G");
        }


        if (c == 'M') {
            imageView.setImageResource(R.mipmap.bodyfatmen);
        } else if (c == 'F') {
            imageView.setImageResource(R.mipmap.bodyfatwomen);
        }


        doneFat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(fatPer.getText().toString())) {
                    user.setBodyfat(Double.parseDouble(fatPer.getText().toString()));
                    Intent i = new Intent(fatinput.this, movmentLevel.class);
                    Log.e("TEST1", fatPer.getText().toString());
                    i.putExtra("user", (Serializable) user);
                    startActivity(i);
                } else fatPer.setError(getString(R.string.errorEmpty));
            }
        });
    }

}
