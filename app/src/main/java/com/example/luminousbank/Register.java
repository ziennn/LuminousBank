package com.example.luminousbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luminousbank.Database.UserHelperClass;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    FirebaseAuth auth;

    ImageView backBtn;
    Button next, login, regBtn;
    TextView titleText;

    TextInputLayout regfirstname, reglastname, regemail, regphonenum, regpassword;

    FirebaseDatabase roodNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Hooks for transition
        backBtn = findViewById(R.id.SignupBackButton);
        next = findViewById(R.id.Signup_next_btn);
        login = findViewById(R.id.Signup_login_btn);
        regBtn = findViewById(R.id.reg_btn);
        titleText = findViewById(R.id.Signup_title_text);

        //Hooks to all xml elements in activity_sign_up.xml
        regfirstname = findViewById(R.id.firstName);
        reglastname = findViewById(R.id.lastName);
        regemail = findViewById(R.id.email);
        regphonenum = findViewById(R.id.phoneNo);
        regpassword = findViewById(R.id.password);


        //Back to Welcome Screen (Home)
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Home.class);
                startActivity(intent);
            }
        });

    }
    /*
     Validation Functions
  */
    //First Name
    private Boolean validateFirstName() {
        String val = regfirstname.getEditText().getText().toString();

        if (val.isEmpty()) {
            regfirstname.setError("Field cannot be empty");
            return false;
        }
        else {
            regfirstname.setError(null);
            regfirstname.setErrorEnabled(false);
            return true;
        }
    }
    //Last Name
    private Boolean validateLastName(){
        String val = reglastname.getEditText().getText().toString();

        if (val.isEmpty()) {
            reglastname.setError("Field cannot be empty");
            return false;
        }
        else {
            reglastname.setError(null);
            reglastname.setErrorEnabled(false);
            return true;
        }

    }
    //Email
    private boolean validateEmail(){
        String val = regemail.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            regemail.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            regemail.setError("Invalid Email!");
            return false;
        } else {
            regemail.setError(null);
            regemail.setErrorEnabled(false);
            return true;
        }
    }
    //Phone Number
    private boolean validatePhoneNumber() {
        String val = regphonenum.getEditText().getText().toString();

        if (val.isEmpty()) {
            regphonenum.setError("Field cannot be empty");
            return false;
        } else {
            regphonenum.setError(null);
            regphonenum.setErrorEnabled(false);
            return true;
        }
    }
    //Password
    private boolean validatePassword() {
        String val = regpassword.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regpassword.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            regpassword.setError("Password should contain 8 characters!");
            return false;
        } else {
            regpassword.setError(null);
            regpassword.setErrorEnabled(false);
            return true;
        }
    }


    public void registerUser(View view){
        roodNode = FirebaseDatabase.getInstance("https://luminousbankdb-default-rtdb.asia-southeast1.firebasedatabase.app/");
        reference = roodNode.getReference("Users");


        //Check the internet connection
        if (!isConnected(this)) {
            showCustomDialog();
        }
        //Get all the data from Intent

        //Validate Fields
        if (!validateFirstName() | !validateLastName() | !validateEmail() | !validatePhoneNumber() | !validatePassword()) {
            return;
        }// Validation succeeded and now move to the next screen to verify phone number

        //Get all the the values in String
        double balance = 1000;
        String firstname = regfirstname.getEditText().getText().toString();
        String lastname = reglastname.getEditText().getText().toString();
        String email = regemail.getEditText().getText().toString();
        String phoneNo = regphonenum.getEditText().getText().toString();
        String password = regpassword.getEditText().getText().toString();


        Intent intent = new Intent(getApplicationContext(), VerifyPhoneNo.class);
        intent.putExtra("firstname", firstname);
        intent.putExtra("lastName", lastname);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("phoneNo", phoneNo);
        intent.putExtra("whatToDo","createNewUser");
        startActivity(intent);

        UserHelperClass helperClass = new UserHelperClass(firstname, lastname, email, phoneNo, password);
        reference.child(phoneNo).setValue(helperClass);


        //Intent intent = new Intent(getApplicationContext(),VerifyPhoneNo.class);

        //Pass all fields to the next activity
       /* intent.putExtra("firstname", firstname);
        intent.putExtra("lastName", lastname);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("phoneNo", phoneNo);*/
        //intent.putExtra("whatToDO", "createNewUser"); // This is to identify that which action should OTP perform after verification.

   /*     Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View,String>(backBtn,"transition_back_btn");
        pairs[1] = new Pair<View,String>(next,"transition_next_btn");
        pairs[2] = new Pair<View,String>(login,"transition_login_btn");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_text");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Register.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }*/

    }

    //INTERNET CONNECTIVITY

    private boolean isConnected(Register register) {
        ConnectivityManager connectivityManager = (ConnectivityManager) register.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConn != null && wifiConn.isConnected() || mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
        builder.setMessage("Please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Login Existing
    public void callLoginFromSignUp(View view) {
        //Intent intent = new Intent(getApplicationContext(), Login.class);
        //startActivity(intent);
        //startActivity(new Intent(getApplicationContext(), Login.class));
        //finish();
        startActivity(new Intent(Register.this, Login.class));
    }

}