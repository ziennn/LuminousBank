package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Login1 extends AppCompatActivity {
    ImageView loginBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login1);

        loginBackBtn = findViewById(R.id.loginBackButton);

        loginBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login1.this, Home.class);
                startActivity(intent);
            }
        });

    }
    //Login Existing
    public void callSignUpFromLogin(View view) {
        startActivity(new Intent(getApplicationContext(), Register.class));
        finish();
    }
}