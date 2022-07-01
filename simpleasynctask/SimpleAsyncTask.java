package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> progressTextView;
    private WeakReference<ProgressBar> progressBar;

    public SimpleAsyncTask(TextView tv, TextView ptv, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        progressTextView = new WeakReference<>(ptv);
        progressBar = new WeakReference<>(pb);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Generating a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // multiply it by 200 to make it a bigger number
        int s = n * 200;

        // Get ten percent of random number
        int tenPercentRandomNumber = (int)(0.1 * s);

        // Int sleep time done
        int sleepTime = 0;
        // sleep for the random amount of time
        try {
            for(int i=10; i<=100; i=i+10){
                Thread.sleep(tenPercentRandomNumber);
                sleepTime+=tenPercentRandomNumber;
                publishProgress(i, sleepTime);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Return the string result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//        super.onProgressUpdate(values);
        Log.d("TAG", ""+values[0]);
        progressBar.get().setProgress(values[0]);
        progressTextView.get().setText("Current sleep time: "+values[1]+"ms");
    }
}
