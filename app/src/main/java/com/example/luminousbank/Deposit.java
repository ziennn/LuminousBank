package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luminousbank.Database.UserHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class Deposit extends AppCompatActivity {

    EditText depositamount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        depositamount = findViewById(R.id.deposit_amount);



        }

    public void callDepositBackBtn(View view) {
        Intent intent = new Intent(Deposit.this, Savings.class);
        startActivity(intent);
    }

    //VALIDATE AMOUNT
    private Boolean validateAmount() {
        String val = depositamount.getText().toString();
        if (val.isEmpty()) {
            depositamount.setError("Field cannot be empty");
            return false;
        } else {
            depositamount.setError(null);
            return true;
        }
    }
    public void callDepositDashboard(View view) {
        if (!validateAmount()) {
            return;
        }

        String DepositAmount = depositamount.getText().toString();

        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        intent.putExtra("keydepositamount",DepositAmount);
        startActivity(intent);
        Toast.makeText(Deposit.this, "Deposit Successfully Processed", Toast.LENGTH_SHORT).show();


    }
}
