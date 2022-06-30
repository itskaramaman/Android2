package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements
        TextView.OnEditorActionListener, View.OnClickListener {

    private String billAmountString = "";
    private float tipPercent = 0.15f;
    // variables for the user interface controls we want to interact with
    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button percentUpBtn;
    private Button percentDownBtn;
    private TextView tipTextView;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference to UI controls
        billAmountEditText = findViewById(R.id.billAmountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        percentUpBtn = findViewById(R.id.percentUpBtn);
        percentDownBtn = findViewById(R.id.percentDownBtn);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);

        // set the listeners
        billAmountEditText.setOnEditorActionListener(this);
        percentDownBtn.setOnClickListener(this);
        percentUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.percentDownBtn:
                if(tipPercent<=0.01)
                    break;
                tipPercent -= 0.01f;
                calculateAndDisplay();
                break;
            case R.id.percentUpBtn:
                tipPercent += 0.01f;
                calculateAndDisplay();
                break;
        }
    }

    private void calculateAndDisplay() {
        // get the bill amount
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if(billAmountString.equals("")){
            billAmount = 0;
        } else {
            billAmount = Float.parseFloat(billAmountString);
        }

        // calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        // display the result after formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }
}