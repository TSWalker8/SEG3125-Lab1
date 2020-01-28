package com.coding.walker.seg3125_lab1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionScreen extends AppCompatActivity {

    ArrayList<Choices> choiceList;
    ArrayList <Questions> questionList;
    ArrayList<String> answerList;
    TextView title;
    TextView question;
    Button buttona, buttonb, buttonc;
    String text;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        choiceList = new ArrayList<Choices>();
        questionList = new ArrayList<Questions>();
        answerList = new ArrayList<String>();
        Intent intent = getIntent();
        text = intent.getStringExtra("choice");
        System.out.println(text);
        title = findViewById(R.id.Title);
        title.setText(text);
        question= findViewById(R.id.Question);
        buttona= findViewById(R.id.Buttona);
        buttonb=findViewById(R.id.Buttonb);
        buttonc= findViewById(R.id.Buttonc);
        new GetInfo().execute();

    }

    public class GetInfo extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            i=0;
            System.out.println(text.equals("Boating License"));
            if (text.equals("Boating License")){
                    System.out.println("Hello");
                    question.setText(questionList.get(i).getTitle());
                    buttona.setText(choiceList.get(i).getBody());
                    buttonb.setText(choiceList.get(i+1).getBody());
                    buttonc.setText(choiceList.get(i+2).getBody());
                    buttona.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            i++;
                            question.setText(questionList.get(i).getTitle());
                            buttona.setText(choiceList.get(i).getBody());
                            buttonb.setText(choiceList.get(i+1).getBody());
                            buttonc.setText(choiceList.get(i+2).getBody());
                        }
                    });
                    buttonb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            i++;
                            question.setText(questionList.get(i).getTitle());
                            buttona.setText(choiceList.get(i).getBody());
                            buttonb.setText(choiceList.get(i+1).getBody());
                            buttonc.setText(choiceList.get(i+2).getBody());
                        }
                    });
                    buttonc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            i++;
                            question.setText(questionList.get(i).getTitle());
                            buttona.setText(choiceList.get(i).getBody());
                            buttonb.setText(choiceList.get(i+1).getBody());
                            buttonc.setText(choiceList.get(i+2).getBody());
                        }
                    });
            }

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
                    System.out.println(id);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                String title = null;
                try {
                    title = q.getString("title");
                    System.out.println(title);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                Questions question = new Questions(id, title);
                System.out.println("CHECK");
                System.out.println(question.getTitle());
                questionList.add(question);
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
                choiceList.add(choice);
            }
            System.out.println("END CHECK");
            System.out.println(questionList.get(1).getTitle());
            return null;
        }

        public String loadJSONFromAsset() {
            System.out.println("HELLO");
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
