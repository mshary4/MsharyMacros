package com.msharytech.msharymacros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.msharytech.msharymacros.Utils.getEmojiByUnicode;

public class ResultFromHistory extends AppCompatActivity {
    TextView textviewCarb, textviewrotien, textviewfat, total, intake;
    Button back;
    String resultid;
    Result result = new Result();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_from_history);
        textviewCarb = (TextView) findViewById(R.id.textViewCarb);
        total = (TextView) findViewById(R.id.textViewTotalCal);
        textviewrotien = (TextView) findViewById(R.id.textViewProtien);
        textviewfat = (TextView) findViewById(R.id.textViewFat);
        intake = (TextView) findViewById(R.id.textViewIntake);
        back = (Button) findViewById(R.id.buttonStartOVer);
        Intent i = getIntent();
        Bundle b = i.getExtras();


        if (b != null) {
            resultid = (String) b.get("result");
            result=Result.getResultByid(resultid);

        }
        total.setText(getString(R.string.YourBodyBurn) + getEmojiByUnicode(0x1F525) + " " + (int) result.getIntake() +" "+ getString(R.string.caldAY));
        intake.setText(getString(R.string.YouShoudEat)+" " + getEmojiByUnicode(0x1F957) + " " + (int) result.getCalBurned() +" "+ getString(R.string.caldAY));
        textviewCarb.setText(getString(R.string.Carb) +" "+ (int) result.getCarb() + " g  " + getEmojiByUnicode(0x1F35E));
        textviewfat.setText(getString(R.string.fat)+" " + (int) result.getFat() + " g  " + getEmojiByUnicode(0x1F95C));
        textviewrotien.setText(getString(R.string.protien) + " "+(int) result.getProtein()+ " g  " + getEmojiByUnicode(0x1F356));
        Log.e("FINAL", String.valueOf(result.getCarb()));
        Log.e("FINAL", String.valueOf(result.getFat()));
        Log.e("FINAL", String.valueOf(result.getProtein()));


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultFromHistory.this, HistoryActivity.class);
                //i.putExtra("user", (Serializable) user);
                startActivity(i);
            }
        });
    }
}
