package com.example.broadcastrecieverscodingchallenge;

import static android.content.Intent.ACTION_HEADSET_PLUG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private CustomReceiver customReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customReceiver = new CustomReceiver();

        // Initialize the intent filter
        IntentFilter filter = new IntentFilter();

        // Add action for headset plug in
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        // Register the receiver
        this.registerReceiver(customReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        // Unregister the custom receiver
        this.unregisterReceiver(customReceiver);
        super.onDestroy();
    }
}