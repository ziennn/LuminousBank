package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CurrencyConvert extends AppCompatActivity {
    EditText moneyET;
    Button conver_btn;
    Spinner spinner;
    TextView php;

    ArrayAdapter adapter;

    String list, getMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_convert);

        moneyET = findViewById(R.id.value_ET);
        conver_btn = findViewById(R.id.conver_Btn);
        php = findViewById(R.id.phpeso);
        spinner = findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(this,R.array.currencies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                list = parent.getItemAtPosition(position).toString();
                switch (list){
                    //USD
                    case "USD - US Dollar":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 56.37));
                                    //Double store = convertToDouble_et * 56.37;
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //KWD
                    case "KWD - Kuwaiti Dinar":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 183.15));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //BHD
                    case "BHD - Baharaini Dinar":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 149.52));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //OMR
                    case "OMR - Omani Riyal":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 146.41));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //JOD
                    case "JOD - Jordanian Dinar":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 79.37));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //GBP
                    case "GBP - Pound Sterling":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 66.71));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //EUR
                    case "EUR - Euro":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 56.75));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //TRY
                    case "TRY - Turkish Lira":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 3.24));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //QAR
                    case "QAR - Qatari Rial":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 15.34));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //CAD
                    case "CAD - Canadian Dollar":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 43.20));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //AUD
                    case "AUD - Australian Dollar":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 38.20));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //SGD
                    case "SGD - Singapore Dollar":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 40.21));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //SAR
                    case "SAR - Saudi Riyal":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 15.01));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                    //NPR
                    case "NPR - Nepalese Rupee":{
                        conver_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMoney = moneyET.getText().toString();
                                if(getMoney.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Please enter amount to convert", Toast.LENGTH_SHORT).show();
                                }else {
                                    double convertToDouble_et = Double.parseDouble(getMoney);
                                    double store = Double.parseDouble(new DecimalFormat("##.####").format(convertToDouble_et * 0.44));
                                    php.setText(""+store);
                                }
                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void callCurrencyDashboard(View view) {

        Intent intent = new Intent(CurrencyConvert.this, Dashboard.class);
        startActivity(intent);
    }
}