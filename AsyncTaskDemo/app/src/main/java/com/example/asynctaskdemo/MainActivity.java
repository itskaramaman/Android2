package com.example.asynctaskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.myTextView);
    }

    public void buttonClick(View view) {
        AsyncTask task = new MyTask().execute();
    }

    private class MyTask extends AsyncTask<String, Integer, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            myTextView.setText(result);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            myTextView.setText("Counter = " + values[0]);
        }

        @Override
        protected String doInBackground(String... strings) {
            int i = 0;
            while (i <= 20) {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "Button Pressed";
        }
    }
}