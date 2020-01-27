package com.coding.walker.seg3125_lab1;

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

    ArrayList<HashMap<String, String>> optionList;
    ArrayList<String> questionList;
    ArrayList<String> answerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        optionList= new ArrayList<HashMap<String, String>>();
        questionList= new ArrayList<String>();
        answerList= new ArrayList<String>();
        new GetInfo().execute();
    }

    private class GetInfo extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute(){
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
            JSONArray questions = jsonObj.getJSONArray("questions");


            for (int i = 0; i < questions.length(); i++) {
                JSONObject q = questions.getJSONObject(i);
                String id = q.getString("id");
                String title = q.getString("title");
            }
            JSONArray choices = jsonObj.getJSONArray("choices");
            for (int i = 0; i < choices.length(); i++) {
                JSONObject c = choices.getJSONObject(i);
                String id = c.getString("id");
                String body = c.getString("body");
                String qid= c.getString("questionId");
            }
                // Phone node is JSON Object
                JSONObject phone = c.getJSONObject("phone");
                String mobile = phone.getString("mobile");
                String home = phone.getString("home");
                String office = phone.getString("office");

                // tmp hash map for single contact
                HashMap<String, String> contact = new HashMap<>();

                // adding each child node to HashMap key => value
                contact.put("id", id);
                contact.put("name", name);
                contact.put("email", email);
                contact.put("mobile", mobile);

                // adding contact to contact list
                contactList.add(contact);
            }
        } catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Json parsing error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
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
    {

    }

}
