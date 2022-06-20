package com.example.luminousbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.luminousbank.Database.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class Register3 extends AppCompatActivity {


    //Variables
    ImageView backBtn;
    Button next;
    TextView titleText;
    ProgressBar progressBar;

    PinView pinFromUser;
    String codeBySystem;
    TextView otpDescriptionText;
    String firstname, lastname, phoneNo, email, password, whatToDO;

    // [NEWLY Updated code  --  START declare_mAuth]
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register3);


        //Hooks for Transition
        backBtn = findViewById(R.id.SignupBackButton);
        next = findViewById(R.id.Signup_next_btn);
        titleText = findViewById(R.id.Signup_title_text);
        progressBar = findViewById(R.id.progress_bar);

        pinFromUser = findViewById(R.id.pin_view);
        otpDescriptionText = findViewById(R.id.otp_description_text);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        //Get all the data from Intent

        firstname = getIntent().getStringExtra("signupfirstname");
        lastname = getIntent().getStringExtra("signuplastname");
        email = getIntent().getStringExtra("signupemail");
        password = getIntent().getStringExtra("signuppassword");
        phoneNo = getIntent().getStringExtra("phoneNo");
        whatToDO = getIntent().getStringExtra("whatToDO");


        otpDescriptionText.setText("Enter One Time Password Sent Onn"+phoneNo);

        sendVerificationCodeToUser(phoneNo);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register3.this, Register.class);
                startActivity(intent);
            }
        });

    }


    //callVerifyOTPScreen in Tuts
    public void call3rdSignupScreen (View view){
        //Validate Fields


        Intent intent = new Intent(getApplicationContext(), Register3.class);

        //Add Transition and call next activity
        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(titleText, "transition_title_text");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Register3.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }


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
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        pinFromUser.setText(code);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(Register3.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Verification completed successfully here Either
                            // store the data or verify the old user
                            if (whatToDO.equals("updateData")) {
                                updateOldUsersData();
                            } else if (whatToDO.equals("createNewUser")) {
                                storeNewUsersData();
                            }

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(Register3.this, "Verification Not Completed! Try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void storeNewUsersData() {

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");

        //Create helperclass reference and store data using firebase
        UserHelperClass addNewUser = new UserHelperClass(firstname, lastname, email, phoneNo, password);
        reference.child(phoneNo).setValue(addNewUser);

        //We will also create a Session here in next videos to keep the user logged In

        startActivity(new Intent(getApplicationContext(), Dashboard.class));
        finish();
    }

    private void updateOldUsersData(){

    }

    //First check the call and then redirect user accordingly to the Profile or to Set New Password Screen
    //CALL NEXT SCREEN AFTER OTP
    public void callNextScreenFromOTP (View view){
        String code = pinFromUser.getText().toString();
        if (!code.isEmpty()) {
            verifyCode(code);
        }
    }

    public void goToHomeFromOTP(View view){

    }
}