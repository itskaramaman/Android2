package com.example.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CustomReceiver mReceiver;
    private TextView txvRandomNumber;
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceiver = new CustomReceiver();
        txvRandomNumber = findViewById(R.id.txvRandomNumber);

        // Initialize the intent filter
        IntentFilter filter = new IntentFilter();

        // Add action for power connected and power disconnected
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        // Register the receiver using the activity context;
        this.registerReceiver(mReceiver, filter);

        // Initialize LocalBroadcastManager
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,
                new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    @Override
    protected void onDestroy() {
        // Unregister the receiver
        this.unregisterReceiver(mReceiver);

        // Unregister from LocalBroadcastManager
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        Random random = new Random();
        int num = random.nextInt(20)+1;
        txvRandomNumber.setText(String.valueOf(num));
        customBroadcastIntent.putExtra("RANDOM_NUMBER", num);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}