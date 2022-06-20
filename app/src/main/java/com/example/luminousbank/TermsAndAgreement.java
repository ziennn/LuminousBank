package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class TermsAndAgreement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_agreement);
    }

    public void callHomeScreen(View view) {
        /*Intent intent = new Intent(this, Home.class);
        startActivity(intent);*/
        //Add Transition
        Intent intent = new Intent(getApplicationContext(), Home.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.termsBtn), "transition_signup");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }
}