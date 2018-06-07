package com.msharytech.msharymacros;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class ChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        Utils.setContext(getApplicationContext());
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        ImageButton imageButtonDataInput = (ImageButton) findViewById(R.id.imageButtonCalc);
        ImageButton imageButtonHistory = (ImageButton) findViewById(R.id.imageButtonHistory);
        ImageButton imageButtonInfo = (ImageButton) findViewById(R.id.imageButtonInfo);


        imageButtonDataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChooserActivity.this, Datainput.class);
                startActivity(intent);
            }
        });

        imageButtonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChooserActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        imageButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(ChooserActivity.this, InfoActivity.class);
                startActivity(intent); //fixme add some info
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_choser, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        switch (item.getItemId()) {
            case R.id.action_contact:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"mshary@iharbi.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Macros calculator Feedback");

//need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

                break;
            case R.id.action_rate:
                new AlertDialog.Builder(ChooserActivity.this)
                        .setTitle("Thank you")
                        .setMessage("you will move to Play Store to rate the App")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("market://details?id=" + App.getContext().getPackageName())));
                                } catch (android.content.ActivityNotFoundException e) {
                                    startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("http://play.google.com/store/apps/details?id=" + App.getContext().getPackageName())));
                                }
                            }
                        }).setNegativeButton(R.string.later, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setIcon(android.R.drawable.checkbox_on_background)
                        .show();
                break;


            default:


        }
        return super.onOptionsItemSelected(item);
    }
}
