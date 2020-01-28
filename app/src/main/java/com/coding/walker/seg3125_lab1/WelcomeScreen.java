package com.coding.walker.seg3125_lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class WelcomeScreen extends AppCompatActivity {
    private Spinner options;
    private Button select;
    private String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        addItemsOnSpinner();
        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice= options.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        select= findViewById(R.id.Select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent (WelcomeScreen.this, QuestionScreen.class);
                nextScreen.putExtra("choice", choice);
                WelcomeScreen.this.startActivity(nextScreen);
            }
        });
    }

    public void addItemsOnSpinner() {

        options = findViewById(R.id.Options);
        List<String> list = new ArrayList<String>();
        list.add("Boating License");
        list.add("G1 License");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(dataAdapter);
    }
}
