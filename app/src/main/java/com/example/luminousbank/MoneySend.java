package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MoneySend extends AppCompatActivity {
    ImageView moneysendbackbtn;
    EditText moneysendphonum, moneysendamount, moneysendmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_send);
        moneysendbackbtn = findViewById(R.id.moneySendBackButton);
        moneysendphonum = findViewById(R.id.moneySend_phoNo);
        moneysendamount = findViewById(R.id.moneySend_amount);
        moneysendmessage = findViewById(R.id.moneySend_message);


        moneysendbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoneySend.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }

    //VALIDATE ACCOUNT NUMBER
    private Boolean validateAccountNum() {
        String val = moneysendphonum.getText().toString();
        if (val.isEmpty()) {
            moneysendphonum.setError("Field cannot be empty");
            return false;
        } else {
            moneysendphonum.setError(null);
            //sendaccnum.setError(false);
            return true;
        }
    }

    //VALIDATE AMOUNT
    private Boolean validateAmount() {
        String val = moneysendamount.getText().toString();
        if (val.isEmpty()) {
            moneysendamount.setError("Field cannot be empty");
            return false;
        } else {
            moneysendamount.setError(null);
            //sendmoneyamount.setErrorEnabled(false);
            return true;
        }
    }

    public void callMoneySendConfirmScreen(View view) {
        if (!validateAccountNum() | !validateAmount()) {
            return;
        }


        String MoneySendphono = moneysendphonum.getText().toString();
        String MoneySendamount = moneysendamount.getText().toString();
        String MoneySendmessage = moneysendmessage.getText().toString();

        Intent intent = new Intent(getApplicationContext(), MoneySendConfirmation.class);
        intent.putExtra("keyphonum",MoneySendphono);
        intent.putExtra("keymoneyamount",MoneySendamount);
        intent.putExtra("keymoneymessage",MoneySendmessage);
        startActivity(intent);


    }

}