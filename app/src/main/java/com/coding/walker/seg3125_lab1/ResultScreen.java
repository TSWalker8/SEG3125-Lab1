package com.coding.walker.seg3125_lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity {

    TextView passfail;
    TextView result;
    Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        passfail= findViewById(R.id.PassFail);
        result = findViewById(R.id.Result);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int count = extras.getInt("count");
        int qcount = extras.getInt("qcount");
        double passgrade = extras.getDouble("passgrade");
        String pgpercent = Double.toString(passgrade*100);
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

        restart = findViewById(R.id.Restart);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultScreen.this, WelcomeScreen.class);
                startActivity(intent);
            }
        });



    }
}
