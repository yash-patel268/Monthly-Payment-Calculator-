package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    //Creating variables for the button, editText, and textView which would be assigned when app is created.
    //There is also Doubles, Strings, and Integers which will hold the values of the inputs
    private Button submitInfo;
    private Double mortAmt, intRate;
    private String freq;
    private Integer amort;
    private EditText mortAmtInput, intRateInput, freqInput, amortInput;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Once the app is created the main_page.xml will load
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        //The appropriate id is assigned to each variable
        mortAmtInput = findViewById(R.id.principleAmountInput);
        intRateInput = findViewById(R.id.interestRateInput);
        freqInput = findViewById(R.id.paymentFrequencyInput);
        amortInput = findViewById(R.id.amortizationInput);
        error = findViewById(R.id.errText);
        submitInfo = findViewById(R.id.submitButton);

        //An event is added to the button which will allow users to submit
        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Will check that all the inputs are not empty and if so will produce error message
                if(mortAmtInput.getText().toString().isEmpty() || intRateInput.getText().toString().isEmpty() || amortInput.getText().toString().isEmpty()){
                    error.setText("Please enter values in all boxes");
                //If the inputs are not empty the values will be passed to variables to be sent to the next view
                } else {
                    mortAmt = Double.valueOf(mortAmtInput.getText().toString());
                    intRate = Double.valueOf(intRateInput.getText().toString());
                    freq = freqInput.getText().toString();
                    amort = Integer.valueOf(amortInput.getText().toString());
                    System.out.println(mortAmt.getClass());
                    System.out.println(intRate);
                    System.out.println(freq);
                    System.out.println(amort);
                    viewPayment(mortAmt, intRate, freq, amort);
                }
            }
        });
    }

    //This a custom function which will be in charge of creating intent which will change view and pass variables to the view to be used
    public void viewPayment(Double mort, Double rate, String times, Integer months){
        Intent intent = new Intent(this, PaymentInfo.class);
        intent.putExtra("mort", mort);
        intent.putExtra("rate", rate);
        intent.putExtra("freq", times);
        intent.putExtra("month", months);
        startActivity(intent);
    }
}