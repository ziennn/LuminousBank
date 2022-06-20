package com.example.luminousbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class Login extends AppCompatActivity {
    Button callSignUp, letTheUserLogin;
    ImageView loginBackBtn;
    ProgressBar progressBar;
    TextInputLayout phoneNumber, password;
    //ProgressBar progressbar;

    //DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://luminousbankdb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        //Hooks
        progressBar = findViewById(R.id.progress_bar);
        loginBackBtn = findViewById(R.id.loginBackButton);
        phoneNumber = findViewById(R.id.login_phone_number);
        password = findViewById(R.id.login_password);

        progressBar.setVisibility(View.GONE);


       loginBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
            }
        });

    }
    //VALIDATE PHONE NUMBER
    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString();

        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }
    //VALIDATE PASSWORD
    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    //Login the user in app
    public void loginUser(View view){
        //Validate Login Info
        if (!validatePhoneNumber() | !validatePassword()) {
            return;
        }
        else{
            isUser();
        }

    }
    private void isUser(){
       /* final String userEnteredPhoneNum = phoneNumber.getEditText().getText().toString().trim();
        final String userEnteredPassword = phoneNumber.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance("https://luminousbankdb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users");
        //DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://luminousbankdb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

        Query checkUser = reference.orderByChild("phoneNumber").equalTo(userEnteredPhoneNum);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredPhoneNum).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){

                        phoneNumber.setError(null);
                        phoneNumber.setErrorEnabled(false);

                        String firstnameFromDB = snapshot.child(userEnteredPhoneNum).child("password").getValue(String.class);
                        String lastnameFromDB = snapshot.child(userEnteredPhoneNum).child("password").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredPhoneNum).child("password").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnteredPhoneNum).child("password").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);

                        intent.putExtra("firstname", firstnameFromDB);
                        intent.putExtra("lastname", lastnameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);

                    }
                    else {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else{
                    phoneNumber.setError("No such User Exist");
                    phoneNumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        final String userEnteredUsername = phoneNumber.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance("https://luminousbankdb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users");
        Query checkUser = reference.orderByChild("phoneNo").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        phoneNumber.setError(null);
                        phoneNumber.setErrorEnabled(false);
                        String firstnameFromDB = snapshot.child(userEnteredUsername).child("firstname").getValue(String.class);
                        String lastnameFromDB = snapshot.child(userEnteredUsername).child("lastname").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                        intent.putExtra("firstname", firstnameFromDB);
                        intent.putExtra("lastname", lastnameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                        progressBar.setVisibility(View.VISIBLE);
                    } else {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                    phoneNumber.setError("No such User exist");
                    phoneNumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    //Login Existing
    public void callSignUpFromLogin(View view) {
        startActivity(new Intent(getApplicationContext(), Register.class));
        //finish();
    }
}