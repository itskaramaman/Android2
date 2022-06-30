package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.converter.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
    }

    public void convertCurrency(View view){
        if(!activityMainBinding.dollarText.getText().toString().equals("")) {
            float dollarValue = Float.parseFloat(activityMainBinding.dollarText.getText().toString());
            float euroValue = dollarValue * 0.85F;
            activityMainBinding.textView.setText(String.format(Locale.ENGLISH, "%.2f", euroValue));
        } else {
            activityMainBinding.textView.setText("No Value");
        }
    }
}