package com.msharytech.msharymacros;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.android.gms.ads.reward.RewardedVideoAd;

import java.util.Calendar;
import java.util.GregorianCalendar;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;


/**
 * Created by Mshary on 4/5/17.
 */

public class Datainput extends AppCompatActivity {
    boolean DoneFat = false;
    TextInputLayout layoutFat;
    public static InterstitialAd mInterstitialAd;
    boolean flagch = false;
    EditText editTextAge, editTextWeight, editTextHight, editTextBodayfat;
    RadioButton radioButtonF, radioButtonM;
    CheckBox checkBoxNonFat;
    ImageView FatPic;
    TextView per, weightTX, ageTX, hightTX;
    User user = new User();

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
        final ToggleSwitch systemUnit = (ToggleSwitch) findViewById(R.id.systemUnitSwitch);
        editTextAge = (EditText) findViewById(R.id.input_age);
        editTextWeight = (EditText) findViewById(R.id.input_weight);
        editTextHight = (EditText) findViewById(R.id.input_Height);
        radioButtonF = (RadioButton) findViewById(R.id.radioButtonFemale);
        radioButtonM = (RadioButton) findViewById(R.id.radioButtonMale);
        editTextBodayfat = (EditText) findViewById(R.id.input_bodyfat);
        checkBoxNonFat = (CheckBox) findViewById(R.id.checkBoxnNonFat);
        FatPic = (ImageView) findViewById(R.id.imageView4);
        per = (TextView) findViewById(R.id.textView3);
        weightTX = (TextView) findViewById(R.id.textView4);
        ageTX = (TextView) findViewById(R.id.textView5);
        hightTX = (TextView) findViewById(R.id.textView6);


        systemUnit.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                if (position == 0) {
                    weightTX.setText("Kg");
                    hightTX.setText("cm");


                } else if (position == 1) {
                    weightTX.setText("lb");
                    hightTX.setText("feet");
                }
            }
        });
        checkBoxNonFat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextBodayfat.setVisibility(View.INVISIBLE);
                    layoutFat.setVisibility(View.INVISIBLE);
                    FatPic.setVisibility(View.INVISIBLE);
                    per.setVisibility(View.INVISIBLE);

                } else if (!isChecked) {
                    editTextBodayfat.setVisibility(View.VISIBLE);
                    layoutFat.setVisibility(View.VISIBLE);
                    FatPic.setVisibility(View.VISIBLE);
                    per.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonResults.setOnClickListener(new View.OnClickListener() {


            public static final String TAG = "TAG";

            @Override
            public void onClick(View v) {

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

                    user.setWeight(Double.parseDouble(editTextWeight.getText().toString()));
                    user.setHeight(Double.parseDouble(editTextHight.getText().toString()));
                    user.setAge(Double.parseDouble(editTextAge.getText().toString()));

                    if (!checkBoxNonFat.isChecked()) {
                        user.setBodyfat(Double.parseDouble(editTextBodayfat.getText().toString()));
                    }
                    RealmManager.getInstance().save(user, User.class);


                    if (systemUnit.getCheckedTogglePosition() == 1) {
                        user.setWeight(0.45359237 * Double.parseDouble(editTextWeight.getText().toString()));
                        user.setHeight(30.48 * Double.parseDouble(editTextHight.getText().toString()));

                    } else {
                        user.setWeight(Double.parseDouble(editTextWeight.getText().toString()));
                        user.setHeight(Double.parseDouble(editTextHight.getText().toString()));

                    }


                    if(!checkBoxNonFat.isChecked()){
                        DoneFat=true;
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

                editTextAge.setText(String.valueOf((int) user.getAge()));
                editTextWeight.setText(String.valueOf(user.getWeight()));
                if (user.getBodyfat() > 0) {
                    checkBoxNonFat.setChecked(false);
                    editTextBodayfat.setVisibility(View.VISIBLE);
                    layoutFat.setVisibility(View.VISIBLE);
                    FatPic.setVisibility(View.VISIBLE);
                    per.setVisibility(View.VISIBLE);
                    editTextBodayfat.setText(String.valueOf( user.getBodyfat()));
                } else {
                    checkBoxNonFat.setChecked(true);
                    editTextBodayfat.setVisibility(View.INVISIBLE);
                    layoutFat.setVisibility(View.INVISIBLE);
                    FatPic.setVisibility(View.INVISIBLE);
                    per.setVisibility(View.INVISIBLE);
                }

                editTextHight.setText(String.valueOf(user.getHeight()));
                String gender = user.getGender();
                if (gender.equalsIgnoreCase("M")) {
                    radioButtonM.toggle();
                } else if (gender.equalsIgnoreCase("F")) {
                    radioButtonF.toggle();
                }


                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    private int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);

    }


}
