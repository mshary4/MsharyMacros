package msharytech.msharymacros;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.Serializable;


/**
 * Created by Mshary on 4/5/17.
 */

public class Datainput extends AppCompatActivity {
    boolean DoneFat = true;
    TextInputLayout layoutFat;
    public static InterstitialAd mInterstitialAd;
    boolean flagch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);
        Animation emerge = AnimationUtils.loadAnimation(this, R.anim.emerge);


        layoutFat = (TextInputLayout) findViewById(R.id.input_layout_bodyfat);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("\n" +
                "ca-app-pub-8360364255923836~4430218506");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        Button buttonResults = (Button) findViewById(R.id.buttonResults);
        final EditText editTextAge = (EditText) findViewById(R.id.input_age);
        final EditText editTextWeight = (EditText) findViewById(R.id.input_weight);
        final EditText editTextHight = (EditText) findViewById(R.id.input_Height);
        final RadioButton radioButtonF = (RadioButton) findViewById(R.id.radioButtonFemale);
        final RadioButton radioButtonM = (RadioButton) findViewById(R.id.radioButtonMale);
        final EditText editTextBodayfat = (EditText) findViewById(R.id.input_bodyfat);
        final CheckBox checkBoxNonFat = (CheckBox) findViewById(R.id.checkBoxnNonFat);
        final ImageView FatPic = (ImageView) findViewById(R.id.imageView4);
        final TextView per = (TextView) findViewById(R.id.textView3);


        checkBoxNonFat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextBodayfat.setVisibility(View.INVISIBLE);
                    layoutFat.setVisibility(View.INVISIBLE);
                    FatPic.setVisibility(View.INVISIBLE);
                    per.setVisibility(View.INVISIBLE);
                    DoneFat = false;
                } else if (!isChecked) {
                    editTextBodayfat.setVisibility(View.VISIBLE);
                    layoutFat.setVisibility(View.VISIBLE);
                    FatPic.setVisibility(View.VISIBLE);
                    per.setVisibility(View.VISIBLE);
                    DoneFat = true;
                }
            }
        });

        buttonResults.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                User user = new User();
                flagch = false;
                if (TextUtils.isEmpty(editTextAge.getText().toString())) {
                    editTextAge.setError(getString(R.string.errorEmpty));
                    flagch = true;
                }
                if (TextUtils.isEmpty(editTextWeight.getText().toString())) {
                    editTextWeight.setError(getString(R.string.errorEmpty));
                    flagch = true;
                }
                if (TextUtils.isEmpty(editTextHight.getText().toString())) {
                    editTextHight.setError(getString(R.string.errorEmpty));
                    flagch = true;
                }
                if (DoneFat) {
                    if (TextUtils.isEmpty(editTextBodayfat.getText().toString())) {
                        editTextBodayfat.setError(getString(R.string.errorEmpty));
                        flagch = true;
                    }
                }
                if (!radioButtonF.isChecked() && !radioButtonM.isChecked()) {
                    Toast.makeText(Datainput.this, getString(R.string.errorGender), Toast.LENGTH_LONG).show();
                    flagch = true;
                }

                if (!flagch) {
                    if (radioButtonF.isChecked()) {
                        user.setGender('F');
                    } else {
                        user.setGender('M');
                    }
                    user.setAge(Double.parseDouble(editTextAge.getText().toString()));
                    user.setWeight(Double.parseDouble(editTextWeight.getText().toString()));
                    user.setHeight(Double.parseDouble(editTextHight.getText().toString()));


                    if (DoneFat) {
                        if (!editTextBodayfat.getText().toString().isEmpty()) {
                            user.setBodyfat(Double.parseDouble(editTextBodayfat.getText().toString()));
                            Intent i = new Intent(Datainput.this, movmentLevel.class);
                            i.putExtra("user", user);
                            startActivity(i);
                        }
                    } else {
                        Intent i = new Intent(Datainput.this, fatinput.class);
                        i.putExtra("user", user);
                        if (radioButtonF.isChecked()) {
                            i.putExtra("G", 'F');
                            startActivity(i);
                        } else if (radioButtonM.isChecked()) {
                            i.putExtra("G", 'M');
                            startActivity(i);
                        }

                    }
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Datainput.this, Welcome.class);
        startActivity(i);
    }
}