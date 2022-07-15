package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SendBank extends AppCompatActivity {
    ImageView sendmoneybackbtn;
    EditText sendaccnum, sendmoneyamount, sendmoneymessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_bank);

        sendmoneybackbtn = findViewById(R.id.sendMoneyBackButton);
        sendaccnum = findViewById(R.id.sendMoney_accNo);
        sendmoneyamount = findViewById(R.id.sendMoney_amount);
        sendmoneymessage = findViewById(R.id.sendMoney_message);


        sendmoneybackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendBank.this, BankTransferList.class);
                startActivity(intent);
            }
        });

    }

    //VALIDATE ACCOUNT NUMBER
    private Boolean validateAccountNum() {
        String val = sendaccnum.getText().toString();
        if (val.isEmpty()) {
            sendaccnum.setError("Field cannot be empty");
            return false;
        } else {
            sendaccnum.setError(null);
            //sendaccnum.setError(false);
            return true;
        }
    }

    //VALIDATE AMOUNT
    private Boolean validateAmount() {
        String val = sendmoneyamount.getText().toString();
        if (val.isEmpty()) {
            sendmoneyamount.setError("Field cannot be empty");
            return false;
        } else {
            sendmoneyamount.setError(null);
            //sendmoneyamount.setErrorEnabled(false);
            return true;
        }
    }

    public void callSendMoneyConfirmScreen(View view) {
        if (!validateAccountNum() | !validateAmount()) {
            return;
        }


        String sendacc = sendaccnum.getText().toString();
        String sendamount = sendmoneyamount.getText().toString();
        String sendmessage = sendmoneymessage.getText().toString();

        Intent intent = new Intent(getApplicationContext(), SendBankConfirmation.class);
        intent.putExtra("keyaccnum",sendacc);
        intent.putExtra("keyamount",sendamount);
        intent.putExtra("keymessage",sendmessage);
        startActivity(intent);


    }
}