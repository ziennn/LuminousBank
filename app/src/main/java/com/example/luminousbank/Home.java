package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

/*        loginButton = findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callLoginScreen();
            }
        });

    }
    public void callLoginScreen(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);*/
    }

    //Call Login Screen
    public void callLoginScreen(View view) {
        //Add Transition
       Intent intent = new Intent(getApplicationContext(), Login.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.loginBtn), "transition_login");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Home.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
        //startActivity(new Intent(Home.this, Login.class));
        //finish();

    }


    //Call Signup Screen
    public void callSignUpScreen(View view) {
        //Add Transition
        Intent intent = new Intent(getApplicationContext(), Register.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.openNewAccBtn), "transition_signup");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Home.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }

}