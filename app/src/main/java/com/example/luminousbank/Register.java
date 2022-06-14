package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    ImageView backBtn;
    Button next, login;
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Hooks
        backBtn = findViewById(R.id.SignupBackButton);
        next = findViewById(R.id.Signup_next_btn);
        login = findViewById(R.id.Signup_login_btn);
        titleText = findViewById(R.id.Signup_title_text);

        //Back to Welcome Screen (Home)
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Home.class);
                startActivity(intent);
            }
        });
    }

    public void callNextSignupScreen(View view){
        Intent intent = new Intent(getApplicationContext(),Register2.class);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View,String>(backBtn,"transition_back_btn");
        pairs[1] = new Pair<View,String>(next,"transition_next_btn");
        pairs[2] = new Pair<View,String>(login,"transition_login_btn");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_text");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Register.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }
    //Login Existing
    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), Login1.class));
        finish();
    }

}