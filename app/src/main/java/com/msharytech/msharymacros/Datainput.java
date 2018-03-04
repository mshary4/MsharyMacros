package com.msharytech.msharymacros;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Created by Mshary on 4/5/17.
 */

public class Datainput extends AppCompatActivity {
    boolean DoneFat = true;
    TextInputLayout layoutFat;
    public static InterstitialAd mInterstitialAd;
    boolean flagch = false;
    EditText editTextAge, editTextWeight, editTextHight, editTextBodayfat;
    RadioButton radioButtonF, radioButtonM;
    CheckBox checkBoxNonFat;
    ImageView FatPic;
    TextView per;

    public static RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        Animation emerge = AnimationUtils.loadAnimation(this, R.anim.emerge);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        layoutFat = (TextInputLayout) findViewById(R.id.input_layout_bodyfat);
        mInterstitialAd = new InterstitialAd(this);

        Button buttonResults = (Button) findViewById(R.id.buttonResults);
        editTextAge = (EditText) findViewById(R.id.input_age);
        editTextWeight = (EditText) findViewById(R.id.input_weight);
        editTextHight = (EditText) findViewById(R.id.input_Height);
        radioButtonF = (RadioButton) findViewById(R.id.radioButtonFemale);
        radioButtonM = (RadioButton) findViewById(R.id.radioButtonMale);
        editTextBodayfat = (EditText) findViewById(R.id.input_bodyfat);
        checkBoxNonFat = (CheckBox) findViewById(R.id.checkBoxnNonFat);
        FatPic = (ImageView) findViewById(R.id.imageView4);
        per = (TextView) findViewById(R.id.textView3);


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
                        user.setGender("F");
                    } else {
                        user.setGender("M");
                    }
                    user.setAge(Double.parseDouble(editTextAge.getText().toString()));
                    user.setWeight(Double.parseDouble(editTextWeight.getText().toString()));
                    user.setHeight(Double.parseDouble(editTextHight.getText().toString()));


                    if (DoneFat) {
                        if (!editTextBodayfat.getText().toString().isEmpty()) {
                            user.setBodyfat(Double.parseDouble(editTextBodayfat.getText().toString()));
                            Intent i = new Intent(Datainput.this, movmentLevel.class);
                            loadRewardedVideoAd(user);
                            i.putExtra("user", user);
                            startActivity(i);
                        }
                    } else {
                        Intent i = new Intent(Datainput.this, fatinput.class);
                        loadRewardedVideoAd(user);
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
        Intent i = new Intent(Datainput.this, ChooserActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        switch (item.getItemId()) {
            case R.id.action_history:
                User user = User.getuser();
                if (user == null) {
                    Toast.makeText(Datainput.this, "No data found", Toast.LENGTH_SHORT).show();
                    return false;
                }

                editTextAge.setText(String.valueOf(user.getAge()));
                editTextWeight.setText(String.valueOf(user.getWeight()));
                editTextBodayfat.setText(String.valueOf(user.getBodyfat()));
                editTextHight.setText(String.valueOf(user.getHeight()));

                String gender = user.getGender();
                if (gender.equals("M") || gender.equals("m")) {
                    radioButtonM.toggle();
                } else if (gender.equals("F") || gender.equals("f")) {
                    radioButtonF.toggle();
                }
                editTextBodayfat.setVisibility(View.VISIBLE);
                layoutFat.setVisibility(View.VISIBLE);
                FatPic.setVisibility(View.VISIBLE);
                per.setVisibility(View.VISIBLE);
                checkBoxNonFat.setChecked(false);
                DoneFat = true;
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void loadRewardedVideoAd(User user) {
        int g = 1;
        if (user.getGender().equals("M") || user.getGender().equals("m")) {
            g = AdRequest.GENDER_MALE;
        } else if (user.getGender().equals("F") || user.getGender().equals("f")) {
            g = AdRequest.GENDER_FEMALE;
        }
        //test
        //ca-app-pub-3940256099942544/5224354917
        // mine ca-app-pub-8360364255923836/2358165066
        mRewardedVideoAd.loadAd("ca-app-pub-8360364255923836/2358165066",
                new AdRequest.Builder().setGender(g).setBirthday(new GregorianCalendar((int) (getYear() - user.getAge()), 1, 1).getTime()).build());
    }


    private int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);

    }






}
