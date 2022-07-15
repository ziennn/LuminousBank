package com.example.luminousbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    static final float END_SCALE = 0.7f;
    //private FirebaseAuth mAuth;

    DatabaseReference databaseReference;

    //RecyclerView featuredRecycler;
    TextView availablebalanceholder;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon, banktransferCard, sendmoneyCard, savingsCard, currencyConCard, quizGameCard;
    LinearLayout contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /*//Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);

        featuredRecycler();

    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));*/

        //Hooks
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        availablebalanceholder = findViewById(R.id.available_balance_holder);


        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        banktransferCard = findViewById(R.id.bankTransferCard);
        sendmoneyCard = findViewById(R.id.sendMoneyCard);
        savingsCard = findViewById(R.id.savingsCard);
        currencyConCard = findViewById(R.id.currencyConverterCard);
        quizGameCard = findViewById(R.id.quizGameCard);

        String DepositAmount = getIntent().getStringExtra("keydepositamount");

        availablebalanceholder.setText("PHP "+DepositAmount+".00");

        //open and closes drawer
        navigationDrawer();

        quizGameCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartQuiz.class);
                startActivity(intent);

            }
        });

        currencyConCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CurrencyConvert.class);
                startActivity(intent);

            }
        });

        banktransferCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BankTransferList.class);
                startActivity(intent);

            }
        });

        sendmoneyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MoneySend.class);
                startActivity(intent);

            }
        });

        savingsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Savings.class);
                startActivity(intent);

            }
        });

    }

  /*  private void signOutUser() {
        mAuth.signOut();
        Intent mainAct = new Intent(Dashboard.this, Home.class);
        mainAct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainAct);
        finish();
    }*/


    //Navigation Drawer Functions
    //open and closes drawer method
    private void navigationDrawer() {
        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }


        });
    }

    //stops app from closing when pressing back button for drawer
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }




}