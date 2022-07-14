package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Savings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);
    }

    public void callDeposit(View view) {
        Intent intent = new Intent(this, Deposit.class);
        startActivity(intent);
    }

    public void callWithdraw(View view) {
        Intent intent = new Intent(this, Withdraw.class);
        startActivity(intent);
    }
}