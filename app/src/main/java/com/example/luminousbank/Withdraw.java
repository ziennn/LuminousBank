package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Withdraw extends AppCompatActivity {

    EditText withdraw_amount, deposit_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        withdraw_amount = findViewById(R.id.withdraw_amount);
        deposit_amount = findViewById(R.id.deposit_amount);

    }

    public void callWithdrawBackBtn(View view) {
        Intent intent = new Intent(Withdraw.this, Savings.class);
        startActivity(intent);
    }

    //VALIDATE AMOUNT
    private Boolean validateAmount() {
        String val = withdraw_amount.getText().toString();
        if (val.isEmpty()) {
            withdraw_amount.setError("Field cannot be empty");
            return false;
        } else {
            withdraw_amount.setError(null);
            return true;
        }
    }

    public void callWithdrawDashboard(View view) {

        if (!validateAmount()) {
            return;
        }

        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(intent);
        Toast.makeText(Withdraw.this, "Withdraw Successfully Processed", Toast.LENGTH_SHORT).show();

    }
}