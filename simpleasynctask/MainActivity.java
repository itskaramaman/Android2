package com.example.simpleasynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TextView where we will show results
    private TextView mTextView;
    private TextView progressTextView;
    private ProgressBar progressBar;
    private static final String TEXT_STATE = "currentText";
    private static final String PROGRESS_TEXT_STATE = "currentSleepTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the textview
        mTextView = findViewById(R.id.textView1);
        progressTextView = findViewById(R.id.progressTextView);
        progressBar = findViewById(R.id.progressBar);

        // Restore TextView if there is a savedInstanceState
        if(savedInstanceState!=null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            progressTextView.setText(savedInstanceState.getString(PROGRESS_TEXT_STATE));
        }
    }

    public void startTask(View view) {
        // Put a message in the text view
        mTextView.setText(R.string.napping);

        // Start the AsyncTask
        new SimpleAsyncTask(mTextView, progressTextView, progressBar).execute();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
        outState.putString(PROGRESS_TEXT_STATE, progressTextView.getText().toString());
    }
}