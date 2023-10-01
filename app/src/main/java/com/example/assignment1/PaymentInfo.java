package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.NumberFormat;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class PaymentInfo extends AppCompatActivity {
    //Creating variables for the button and textView which would be assigned when app is created.
    private Button returnHome;
    private TextView paymentAmtView, mortAmtView, rateView, freqView, amortView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Once the app is created the info_page.xml will load
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_page);

        //Creating intent which will be used to receive the data sent from the home page
        Intent receivedIntent = getIntent();
        //Receive principle amount data and convert to double
        Double receivedData1 = receivedIntent.getDoubleExtra("mort", 0);
        //Receive annual interest rate, convert to double divide by 100 to convert to decimal and then divide by 12 for monthly rates
        Double receivedData2 = (receivedIntent.getDoubleExtra("rate", 0)/100/12);
        //Receive payment frequency
        String receivedData3 = receivedIntent.getStringExtra("freq");
        //Receive years which will be converted to integer and multiplied by 12 for months
        Integer receivedData4 = (receivedIntent.getIntExtra("month", 0)*12);

        //Calculate monthly payment through formula
        double payAmt = (receivedData1 * receivedData2) / (1 - Math.pow(1 + receivedData2, -receivedData4));
        //Convert to concurrency format
        String payAmtStr = NumberFormat.getCurrencyInstance().format(payAmt);

        System.out.println(receivedData1);
        System.out.println(receivedData2);
        System.out.println(receivedData3);
        System.out.println(receivedData4);
        System.out.println(payAmt);

        //The appropriate id is assigned to each variable
        paymentAmtView = findViewById(R.id.paymentAmountView);
        mortAmtView = findViewById(R.id.mortgageAmountView);
        rateView = findViewById(R.id.interestRateView);
        freqView = findViewById(R.id.paymentFrequencyView);
        amortView = findViewById(R.id.amortizationView);
        returnHome = findViewById(R.id.backButton);

        //The textView are then assigned a value to display
        paymentAmtView.setText(payAmtStr);
        mortAmtView.setText("$" + String.valueOf(receivedData1));
        rateView.setText(String.valueOf(receivedData2)+"%");
        freqView.setText(receivedData3);
        amortView.setText(String.valueOf(receivedData4 + " months"));


        //An event is added to the button which will allow users to return to home page
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });
    }

    //Custome function which will be in charge of creating intent to switch views
    public void returnHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
