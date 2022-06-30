package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        // switch case for intent action and display toast
        if(intentAction != null) {
            String toastMessage = "unknown intent action";
            int randomNumber = intent.getIntExtra("RANDOM_NUMBER", 0);
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power Connected!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power Disconnected";
                    break;
                case Intent.ACTION_HEADSET_PLUG:
                    toastMessage = "Headset connected or disconnected";
                    break;

                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = "Custom Broadcast Received \n Square of the random number "+randomNumber*randomNumber;
                    break;
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}