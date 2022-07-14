package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MoneySendConfirmation extends AppCompatActivity {
    ImageView moneysendconfirmbackbtn;
    TextView moneysendphonumTV, moneysendamountTV, moneysendmessageTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_send_confirmation);
        moneysendconfirmbackbtn = findViewById(R.id.MoneyMoneySendConfirmBackButton);
        moneysendamountTV = findViewById(R.id.moneySend_confirm_amountTV);
        moneysendphonumTV = findViewById(R.id.recipientPhoNum);
        moneysendmessageTV = findViewById(R.id.moneySend_confirm_messageTV);

        //sendMoney_amount = getIntent().getStringExtra("sendMoney_amount");

        //sendamountTV.setText("PHP"+sendMoney_amount);
        String MoneySendphono = getIntent().getStringExtra("keyphonum");
        String MoneySendamount = getIntent().getStringExtra("keymoneyamount");
        String MoneySendmessage = getIntent().getStringExtra("keymoneymessage");

        moneysendphonumTV.setText(MoneySendphono);
        moneysendamountTV.setText("PHP "+MoneySendamount+".00");
        moneysendmessageTV.setText(MoneySendmessage);



        moneysendconfirmbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoneySendConfirmation.this, MoneySend.class);
                startActivity(intent);
            }
        });
    }

    public void callConfirmNextScreen(View view) {
        Intent intent = new Intent(MoneySendConfirmation.this, Dashboard.class);
        startActivity(intent);
        Toast.makeText(MoneySendConfirmation.this, "Successfully Send!", Toast.LENGTH_SHORT).show();

    }
}