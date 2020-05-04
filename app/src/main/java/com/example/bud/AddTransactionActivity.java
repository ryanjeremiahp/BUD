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


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);



        //todo This needs to be done for both the category and subcategory spinner
        /*
        ------Category----
        -Please Choose-
        Housing
        Transportation
        Food
        Utilities
        Medical
        Savings
        Personal

        ---Subcategory---
        -Please Choose-
        Insurance
        Rent
        Loan Payment
        Gas
        Maintenance
        Fast Food
        Grocery
        Lifestyle expense
        Recreation/Entertainment
        Miscellaneous


        **/

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



    }
}
