package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Withdraw extends AppCompatActivity {

    EditText withdrawamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        withdrawamount = findViewById(R.id.withdraw_amount);

    }

    public void callWithdrawBackBtn(View view) {
        Intent intent = new Intent(Withdraw.this, Savings.class);
        startActivity(intent);
    }

    //VALIDATE AMOUNT
    private Boolean validateAmount() {
        String val = withdrawamount.getText().toString();
        if (val.isEmpty()) {
            withdrawamount.setError("Field cannot be empty");
            return false;
        } else {
            withdrawamount.setError(null);
            return true;
        }
    }

    public void callWithdrawDashboard(View view) {

        if (!validateAmount()) {
            return;
        }

        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(intent);
    }
}