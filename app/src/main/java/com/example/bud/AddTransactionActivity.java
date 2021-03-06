package com.example.bud;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddTransactionActivity extends AppCompatActivity {

    DatabaseHelper database;
    GlobalClass globalClass;

    private EditText dateEntered, totalEntered, placeEntered, notesEntered;
    private Spinner accountChooser, categoryChooser, subcategoryChooser;
    private Button addTransaction;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        globalClass = (GlobalClass) getApplicationContext();
        database = globalClass.getDatabase();


        dateEntered = findViewById(R.id.DateEntered);
        totalEntered = findViewById(R.id.TotalEntered);
        placeEntered = findViewById(R.id.PlaceEntered);
        notesEntered = findViewById(R.id.NotesEntered);

        accountChooser = findViewById(R.id.AccountChooser);
        /*ArrayAdapter<CharSequence> accountAdapter =
                ArrayAdapter.createFromResource(this, R.array.accountChoices, android.R.layout.simple_spinner_item);
        accountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountChooser.setAdapter(accountAdapter);*/


        categoryChooser = findViewById(R.id.CategoryChooser);
      /*  ArrayAdapter<CharSequence> categoryAdapter =
                ArrayAdapter.createFromResource(this, R.array.categoryChoices, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryChooser.setAdapter(categoryAdapter);*/


        subcategoryChooser = findViewById(R.id.SubcategoryChooser);
        /*ArrayAdapter<CharSequence> subcategoryAdapter =
                ArrayAdapter.createFromResource(this, R.array.subcategoryChoices, android.R.layout.simple_spinner_item);
        subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subcategoryChooser.setAdapter(subcategoryAdapter);*/


        addTransaction = findViewById(R.id.AddTransaction);
    }

    public void addTransaction (View view) {
        boolean isAdded = database.insertData(
                accountChooser.toString(),
                dateEntered.getText().toString(),
                totalEntered.getText().toString(),
                categoryChooser.toString(),
                placeEntered.getText().toString(),
                notesEntered.getText().toString(),
                subcategoryChooser.toString());
        if (isAdded) {
            Toast.makeText(AddTransactionActivity.this, "Transaction has been added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AddTransactionActivity.this, "Transaction could not be added", Toast.LENGTH_LONG).show();

        }
       globalClass.setDatabase(database);

/*
        Intent intent = new Intent(this, MainActivity.class);
        EditText editTextDate = findViewById(R.id.DateEntered);
        String tempDate = dateEntered.getText().toString();
        intent.putExtra("extraAddTrans", tempDate);
        intent.putExtra("extraAddTrans", "blah");
        startActivity(intent)*/;

    }
}