package com.example.bud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddTransactionActivity extends AppCompatActivity {

    private Spinner accountChooser;
    private Spinner categoryChooser;
    private Spinner subcategoryChooser;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);


        accountChooser = findViewById(R.id.AccountChooser);

        List<String> accountList = new ArrayList<>();
        accountList.add("Checking");
        accountList.add("Savings");
        accountList.add("Credit");

        ArrayAdapter<String> accountAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accountList);
        accountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountChooser.setAdapter(accountAdapter);

        accountChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String currentAccount = accountChooser.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        categoryChooser = findViewById(R.id.CategoryChooser);

        List<String> categoryList = new ArrayList<>();
        categoryList.add("-Please Choose-");
        categoryList.add("Housing");
        categoryList.add("Transportation");
        categoryList.add("Food");
        categoryList.add("Utilities");
        categoryList.add("Medical");
        categoryList.add("Savings");
        categoryList.add("Personal");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryChooser.setAdapter(categoryAdapter);

        categoryChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String currentAccount = categoryChooser.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        subcategoryChooser = findViewById(R.id.SubcategoryChooser);

        List<String> subcategoryList = new ArrayList<>();
        categoryList.add("-Please Choose-");
        categoryList.add("Insurance");
        categoryList.add("Rent");
        categoryList.add("Loan Payment");
        categoryList.add("Gas");
        categoryList.add("Maintenance");
        categoryList.add("Fast Food");
        categoryList.add("Grocery");
        categoryList.add("Lifestyle Expense");
        categoryList.add("Recreation/Entertainment");
        categoryList.add("Miscellaneous");


        ArrayAdapter<String> subcategoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subcategoryList);
        subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subcategoryChooser.setAdapter(categoryAdapter);

        subcategoryChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String currentAccount = subcategoryChooser.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}