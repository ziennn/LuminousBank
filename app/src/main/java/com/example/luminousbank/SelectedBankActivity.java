package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedBankActivity extends AppCompatActivity {

    TextView tvBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_bank);
        //getActionBar().hide();


        tvBank = findViewById(R.id.selectedBank);

        Intent intent = getIntent();

        if (intent.getExtras() != null){
            BankModel bankModel = (BankModel) intent.getSerializableExtra("data");

            tvBank.setText(bankModel.getBankName());
        }
    }
}