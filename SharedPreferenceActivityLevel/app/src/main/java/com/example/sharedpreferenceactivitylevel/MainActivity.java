package com.example.sharedpreferenceactivitylevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etMajor, etId;
    private TextView txvName, txvMajor, txvId;

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
    }

    public void saveData(View view) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
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
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

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
}