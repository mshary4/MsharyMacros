package com.msharytech.msharymacros;

import android.content.Intent;
import android.support.v7.app.ActionBar;
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



        ImageButton imageButtonDataInput= (ImageButton) findViewById(R.id.imageButtonCalc);
        ImageButton imageButtonHistory= (ImageButton) findViewById(R.id.imageButtonHistory);
        ImageButton imageButtonInfo= (ImageButton) findViewById(R.id.imageButtonInfo);


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

        imageButtonHistory.setOnClickListener(new View.OnClickListener() {
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
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        switch (item.getItemId()) {
            case R.id.action_rate:
                //fixme rate fun
                break;

            case R.id.action_contact:
                //fixme contact dev
                break;

            default:



        }
        return super.onOptionsItemSelected(item);
    }
}
