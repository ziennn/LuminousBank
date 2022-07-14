package com.example.luminousbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class ForgetPassword extends AppCompatActivity {
    //variables
     ImageView screenIcon;
     TextView title, description;
     TextInputLayout phoneNumberText;
     CountryCodePicker countryCodePicker;
     Button fpnextBtn;
     ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        //Hooks
        screenIcon = findViewById(R.id.forget_password_icon);
        title = findViewById(R.id.forget_password_title);
        description = findViewById(R.id.forget_password_description);
        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumberText = findViewById(R.id.forget_password_number);
        progressBar = findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.GONE);

    }
    /*Call THE OTP Screen and
    pass phone Number
    for verification*/
    private boolean validatePhoneNumber() {
        String val = phoneNumberText.getEditText().getText().toString();

        if (val.isEmpty()) {
            phoneNumberText.setError("Enter valid phone number");
            return false;
        } else {
            phoneNumberText.setError(null);
            phoneNumberText.setErrorEnabled(false);
            return true;
        }
    }

    public void callBackScreenFromForgetPassword(View view) {
        Intent intent = new Intent(ForgetPassword.this, Login.class);
        startActivity(intent);
    }

    public void forgetNextBtn(View view) {
        //Check the internet connection
        if (!isConnected(this)) {
            showCustomDialog();
        }
        //Validate Login Info
        if (!validatePhoneNumber()) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        //Get complete phone number
        final String phoneNo = phoneNumberText.getEditText().getText().toString().trim();
        //String code = countryCodePicker.getFullNumber();

        Query checkUser = FirebaseDatabase.getInstance("https://luminousbankdb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users").orderByChild("phoneNo").equalTo(phoneNo);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    phoneNumberText.setError(null);
                    phoneNumberText.setErrorEnabled(false);

                    Intent intent = new Intent(getApplicationContext(), SetNewPassword.class);
                    intent.putExtra("phoneNo", phoneNo);
                    //intent.putExtra("code", code);
                    intent.putExtra("whatToDo","updateData");
                    startActivity(intent);
                    finish();

                    progressBar.setVisibility(View.VISIBLE);

                }else {
                    progressBar.setVisibility(View.GONE);
                    phoneNumberText.setError("No such user exist!");
                    phoneNumberText.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ForgetPassword.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }

    private boolean isConnected(ForgetPassword forgetPassword) {
        ConnectivityManager connectivityManager = (ConnectivityManager) forgetPassword.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConn != null && wifiConn.isConnected() || mobileConn != null && mobileConn.isConnected())) {
            return true;
        }
        else{
            return false;
            }
        }


    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPassword.this);
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
}
