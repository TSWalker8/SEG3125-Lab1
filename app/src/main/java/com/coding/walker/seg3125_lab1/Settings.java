package com.coding.walker.seg3125_lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.regex.*;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    private Button submit;
    private double passGrade;
    private int numOfQuestion;
    String userPercentage;
    String userNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        submit= findViewById(R.id.PercentageButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent (Settings.this, WelcomeScreen.class);
                userPercentage = ((EditText)findViewById(R.id.percentageInput)).getText().toString();
                userNumber = ((EditText)findViewById(R.id.numQuestion)).getText().toString();
                if(InputAreGood())
                {
                    Bundle b = new Bundle();
                    b.putDouble("passgrade", (Double.parseDouble(userPercentage) / 100));
                    b.putInt("qnum",Integer.parseInt(userNumber));
                    nextScreen.putExtras(b);
                    startActivity(nextScreen);
                }

            }
        });
    }

    private Boolean InputAreGood()
    {
        Boolean result = true;
        if (userPercentage.isEmpty() || userNumber.isEmpty()){
            AlertDialog alertDialog = new AlertDialog.Builder(Settings.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("You did not input anything");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return result = false;
        }
        if (userPercentage.matches("^[a-zA-Z]*$") || (userNumber.matches("^[a-zA-Z]*$"))){
            AlertDialog alertDialog = new AlertDialog.Builder(Settings.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("You attempted to input a character");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return result = false;
        }
        if((50>Double.parseDouble(userPercentage)) || ((Double.parseDouble(userPercentage)>100)))
        {
            AlertDialog alertDialog = new AlertDialog.Builder(Settings.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("The percentage should be in [50,100].");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            result = false;
        }
        if((1>Double.parseDouble(userNumber)) || ((Double.parseDouble(userNumber)>10)))
        {
            AlertDialog alertDialog = new AlertDialog.Builder(Settings.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("The number of questions should be in [1,10].");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            result = false;
        }
        return result;
    }

}
