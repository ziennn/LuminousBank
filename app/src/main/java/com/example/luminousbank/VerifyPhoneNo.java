package com.example.luminousbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.luminousbank.Database.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNo extends AppCompatActivity {

    ImageView backBtn;
    TextView titleText;

    //Get data variables
    Button verify_btn;
    ProgressBar progressBar;
    PinView pinFromUser;
    String codeBySystem;
    TextView otpDescriptionText;

    String firstname, lastname, phoneNo, email, password;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    // [NEWLY Updated code  --  START declare_mAuth]
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_no);

        //Hooks for animation
        backBtn = findViewById(R.id.SignupBackButton);
        titleText = findViewById(R.id.Signup_title_text);
        progressBar = findViewById(R.id.progress_bar);
        otpDescriptionText = findViewById(R.id.otp_description_text);

        progressBar.setVisibility(View.GONE);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Hooks for getting data
        verify_btn = findViewById(R.id.verify_btn);
        pinFromUser = findViewById(R.id.pin_view);

        //Get all the data from Intent
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        phoneNo = getIntent().getStringExtra("phoneNo");
        //whatToDO = getIntent().getStringExtra("whatToDO");

        otpDescriptionText.setText("Enter One Time Password Sent On +63"+phoneNo);

        sendVerificationCodeToUser(phoneNo);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = pinFromUser.getText().toString();

                if (code.isEmpty() || code.length() < 6) {
                    pinFromUser.setError("Wrong OTP...");
                    pinFromUser.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        });
    }
    private void sendVerificationCodeToUser(String phoneNo) {
        // [START start_phone_auth]
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth) //mAuth is defined on top
                .setPhoneNumber("+63" + phoneNo)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        // [END start_phone_auth]
       /* PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+63" + phoneNo,
            60,
            TimeUnit.SECONDS,
            (Activity) TaskExecutors.MAIN_THREAD,
            mCallbacks);*/
    }
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyPhoneNo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String codeByUser){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, codeByUser);
        signInTheUserByCredentials(credential);
    }
    private void signInTheUserByCredentials (PhoneAuthCredential credential){

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Verification completed successfully here Either
                            // store the data or do whatever desire
                            // if (whatToDO.equals("updateData")) {
                            // updateOldUsersData();
                            //} else if (whatToDO.equals("createNewUser")) {
                            //storeNewUsersData();
                            //}
                            Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            Toast.makeText(VerifyPhoneNo.this, "Verification Completed!", Toast.LENGTH_SHORT).show();
                        } else {
                            //if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(VerifyPhoneNo.this, "Verification Not Completed! Try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //}
                });

    }
    /*  private void storeNewUsersData(){
          FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://luminousbankdb-default-rtdb.asia-southeast1.firebasedatabase.app");
          DatabaseReference reference = rootNode.getReference("Users");

          //Create helperclass reference and store data using firebase
          UserHelperClass addNewUser = new UserHelperClass(firstname, lastname, email, password, phoneNo);
          reference.child(phoneNo).setValue(addNewUser);

          //We will also create a Session here in next videos to keep the user logged In

          startActivity(new Intent(getApplicationContext(), UserProfile.class));
          finish();
      }*/
      public void callNextScreenFromOTP(View view) {
          String code = pinFromUser.getText().toString();
          if (!code.isEmpty()){
              verifyCode(code);
          }

      }
    public void goToHomeFromOTP(View view){

    }
}
