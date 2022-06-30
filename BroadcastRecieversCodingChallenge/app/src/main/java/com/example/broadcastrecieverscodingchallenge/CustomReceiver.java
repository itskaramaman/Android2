package com.example.broadcastrecieverscodingchallenge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "Broadcast received");
        String intentAction = intent.getAction();

        // check if intent action is not empty
        if(intentAction!=null){
            Toast.makeText(context, "Headset plugged or unplugged", Toast.LENGTH_SHORT).show();
        }
    }
}