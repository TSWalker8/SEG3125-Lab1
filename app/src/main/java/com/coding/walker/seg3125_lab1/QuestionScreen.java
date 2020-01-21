package com.coding.walker.seg3125_lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionScreen extends AppCompatActivity {

    ArrayList<HashMap<String, String>> optionList;
    ArrayList<String> questionList;
    ArrayList<String> answerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
    }
}
