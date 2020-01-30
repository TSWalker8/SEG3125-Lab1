package com.coding.walker.seg3125_lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity {

    TextView passfail;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        double passgrade = .5;
        String pgpercent = Double.toString(passgrade*100);
        passfail= findViewById(R.id.PassFail);
        result = findViewById(R.id.Result);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int count = extras.getInt("count");
        int qcount = extras.getInt("qcount");
        double res = (double) count/qcount;
        double percent = res*100;
        String disp= Double.toString(percent);
        if (res>= passgrade){
            passfail.setText("Congratulations! You have received a passing grade of:");
            result.setText(disp+"%");
        }
        else{
            passfail.setText("Unfortunately you have not passed, you required a " + pgpercent + "% and you received a:");
            result.setText(disp+"%");
        }


    }
}
