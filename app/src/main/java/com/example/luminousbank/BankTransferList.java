package com.example.luminousbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class BankTransferList extends AppCompatActivity implements BankAdapter.SelectedBank {
    Toolbar toolbar;
    RecyclerView recyclerView;

    List<BankModel> bankModelList = new ArrayList<>();

    String[] banknames = {"BDO Unibank, Inc.","BPI/BPI Family Savings Bank","United Coconut Planters Bank(UCPB)","RCBC/DiskarTech","Union Bank of the Philippines",
            "Security Bank Corporation", "Metropolitan Bank and Trust Co.", "Philippine National Bank", "China Banking Corporation",
            "East West Banking Corporation", "Philippine Savings Bank","Asia United Bank Corporation","Philippine Bank of Communication","Banko Mabuhay",
            "Bank of Commerce","PayMaya Philippines, Inc.", "Seabank","Sun Savings Bank, Inc.","Veterans Bank","Wealth Development Bank"};

    BankAdapter bankAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_transfer_list);

        recyclerView = findViewById(R.id.recyclerview);
        toolbar = findViewById(R.id.toolbar);

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        for (String s:banknames){
            BankModel bankModel = new BankModel(s);

            bankModelList.add(bankModel);
        }
        bankAdapter = new BankAdapter(bankModelList, this);
        recyclerView.setAdapter(bankAdapter);
    }

    @Override
    public void selectedBank(BankModel bankModel) {

        startActivity(new Intent(BankTransferList.this, SendBank.class).putExtra("data",bankModel));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                bankAdapter.getFilter().filter(newText);
                return true;
            }

        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if(id==R.id.action_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
