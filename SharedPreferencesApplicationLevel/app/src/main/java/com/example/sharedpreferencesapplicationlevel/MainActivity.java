package com.example.sharedpreferencesapplicationlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etMajor, etId;
    private TextView txvName, txvMajor, txvId;
    private Switch pageColorSwitch;
    private LinearLayout pageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etMajor = findViewById(R.id.etMajor);
        etId = findViewById(R.id.etId);

        txvName = findViewById(R.id.txvName);
        txvMajor = findViewById(R.id.txvMajor);
        txvId = findViewById(R.id.txvID);

        pageLayout = findViewById(R.id.pageLayout);
        pageColorSwitch = findViewById(R.id.pageColorSwitch);
        pageColorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                setPageColor(isChecked);
            }
        });

        // Load data from Activity level shared preferences
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean("yellow", false);
        pageColorSwitch.setChecked(isChecked);

    }

    // save data to Activity level shared preferences
    public void setPageColor(boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("yellow", isChecked);
        editor.apply();

        pageLayout.setBackgroundColor(isChecked ? Color.YELLOW : Color.WHITE);
    }

    public void saveData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // save the data as key value pairs
        editor.putString("name", etName.getText().toString());
        editor.putString("major", etMajor.getText().toString());
        editor.putString("id", etId.getText().toString());

        editor.apply(); // you can use editor.commit() as well
        /*
        editor.commit returns a boolean indicating whether data was saved successfully or not.
        Also editor.commit() saves data synchronously where editor.apply() saves the data asynchronously (runs in the background)
        For this simple app it does not makes a difference
         */
    }

    public void loadData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref_file" ,Context.MODE_PRIVATE);

        // retrieve the data from the shared preferences file
        // the second parameters are the default values you need to provide

        String name = sharedPreferences.getString("name", "Name is not available");
        String major = sharedPreferences.getString("major", "Major is not available");
        String id = sharedPreferences.getString("id", "Student ID is not available");

        // use the retrieved values to update the text views on the screen
        txvName.setText(name);
        txvMajor.setText(major);
        txvId.setText(id);
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}