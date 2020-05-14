package com.example.bud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AccountsActivity extends AppCompatActivity {
    GlobalClass globalClass;
    DatabaseHelper database;

    String checkingBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        globalClass = (GlobalClass) getApplicationContext();
        database = globalClass.getDatabase();
    }
}
