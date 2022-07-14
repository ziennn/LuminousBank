package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SendBankConfirmation extends AppCompatActivity {
    ImageView sendmoneyconfirmbackbtn;
    TextView sendaccnumTV, sendamountTV, sendmessageTV;
    //String sendMoney_amount, sendMoney_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_bank_confirmation);

        sendmoneyconfirmbackbtn = findViewById(R.id.sendMoneyConfirmBackButton);
        sendamountTV = findViewById(R.id.confirm_amountTV);
        sendaccnumTV = findViewById(R.id.recipientAccNum);
        sendmessageTV = findViewById(R.id.confirm_messageTV);

        //sendMoney_amount = getIntent().getStringExtra("sendMoney_amount");

        //sendamountTV.setText("PHP"+sendMoney_amount);
        String sendacc = getIntent().getStringExtra("keyaccnum");
        String sendamount = getIntent().getStringExtra("keyamount");
        String sendmessage = getIntent().getStringExtra("keymessage");

        sendaccnumTV.setText(sendacc);
        sendamountTV.setText("PHP "+sendamount+".00");
        sendmessageTV.setText(sendmessage);



        sendmoneyconfirmbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendBankConfirmation.this, SendBank.class);
                startActivity(intent);
            }
        });
    }

    public void callConfirmNextScreen(View view) {
        Intent intent = new Intent(SendBankConfirmation.this, Dashboard.class);
        startActivity(intent);
        Toast.makeText(SendBankConfirmation.this, "Transfer Successfully!", Toast.LENGTH_SHORT).show();

    }
}