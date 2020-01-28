package com.coding.walker.seg3125_lab1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionScreen extends AppCompatActivity {

    ArrayList<HashMap<String, Choices>> choiceList;
    ArrayList<HashMap<String, Questions>> questionList;
    ArrayList<String> answerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        choiceList = new ArrayList<HashMap<String, Choices>>();
        questionList = new ArrayList<HashMap<String, Questions>>();
        answerList = new ArrayList<String>();
        new GetInfo().execute();

        Intent intent = getIntent();
        String text = intent.getStringExtra("choice");

    }

    private class GetInfo extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(loadJSONFromAsset());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            // Getting JSON Array node
            JSONArray questions = null;
            try {
                questions = jsonObj.getJSONArray("questions");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            for (int i = 0; i < questions.length(); i++) {
                JSONObject q = null;
                try {
                    q = questions.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String id = null;
                try {
                    id = q.getString("id");
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                String title = null;
                try {
                    title = q.getString("title");
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                Questions question = new Questions(id, title);
                HashMap<String, Questions> h = new HashMap<String, Questions>();
                h.put(id, question);
                questionList.add(h);
            }
            JSONArray choices = null;
            try {
                choices = jsonObj.getJSONArray("choices");
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            for (int i = 0; i < choices.length(); i++) {
                JSONObject c = null;
                try {
                    c = choices.getJSONObject(i);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                String id = null;
                try {
                    id = c.getString("id");
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                String body = null;
                try {
                    body = c.getString("body");
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                String qid = null;
                try {
                    qid = c.getString("questionId");
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                Choices choice = new Choices(id, body, qid);
                HashMap<String, Choices> h = new HashMap<String, Choices>();
                h.put(qid, choice);
                choiceList.add(h);
            }

            return null;
        }

        public String loadJSONFromAsset() {
            String json = null;
            try {
                InputStream is = getAssets().open("db.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
        }

    }
}
