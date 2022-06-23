package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SendMoneyConfirmation extends AppCompatActivity {
    ImageView sendmoneyconfirmbackbtn;
    TextView sendamountTV, sendmessageTV;
    //String sendMoney_amount, sendMoney_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money_confirmation);

        sendmoneyconfirmbackbtn = findViewById(R.id.sendMoneyConfirmBackButton);
        sendamountTV = findViewById(R.id.confirm_amountTV);
        sendmessageTV = findViewById(R.id.confirm_messageTV);

        //sendMoney_amount = getIntent().getStringExtra("sendMoney_amount");

        //sendamountTV.setText("PHP"+sendMoney_amount);
        String sendamount = getIntent().getStringExtra("keyamount");
        String sendmessage = getIntent().getStringExtra("keymessage");

        sendamountTV.setText("PHP "+sendamount+".00");
        sendmessageTV.setText(sendmessage);



        sendmoneyconfirmbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendMoneyConfirmation.this, SendMoney.class);
                startActivity(intent);
            }
        });
    }

    public void callConfirmNextScreen(View view) {
    }
}