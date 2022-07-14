package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Withdraw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
    }

    public void callWithdrawBackBtn(View view) {
        Intent intent = new Intent(Withdraw.this, Savings.class);
        startActivity(intent);
    }
}