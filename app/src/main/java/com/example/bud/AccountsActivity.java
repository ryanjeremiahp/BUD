package com.example.bud;

import android.database.Cursor;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountsActivity extends AppCompatActivity {
    GlobalClass globalClass;
    DatabaseHelper database;

    Button viewAllChecking, viewAllSavings, viewAllCredit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        globalClass = (GlobalClass) getApplicationContext();
        database = globalClass.getDatabase();

        initializer();
    }

    public void initializer() {
        viewAllChecking = findViewById(R.id.viewAllCheckingButton);
        viewAllChecking.setText(getCheckingBalance());

        viewAllSavings = findViewById(R.id.viewAllSavingsButton);
        viewAllSavings.setText(getSavingsBalance());

        viewAllCredit=findViewById(R.id.viewAllCreditButton);
        viewAllCredit.setText(getCheckingBalance());
    }

    public void getCheckingTransactions(View view) {
        Cursor list = database.getTransactionsAccount(database.getChecking());

        StringBuffer buffer = new StringBuffer();
        while (list.moveToNext()) {
            buffer.append(list.getString(5) + ":\t" + moneyFormat(list.getDouble(3)) + "\t" + list.getString(2) + "\n");
        }
        showMessage("Transaction Details", buffer.toString());
    }
    public void getSavingsTransactions(View view) {
        Cursor list = database.getTransactionsAccount(database.getSavings());

        StringBuffer buffer = new StringBuffer();
        while (list.moveToNext()) {
            buffer.append(list.getString(5) + ":\t" + moneyFormat(list.getDouble(3)) + "\t" + list.getString(2) + "\n");
        }
        showMessage("Transaction Details", buffer.toString());
    }
    public void getCreditTransactions(View view) {
        Cursor list = database.getTransactionsAccount(database.getCredit());

        StringBuffer buffer = new StringBuffer();
        while (list.moveToNext()) {
            buffer.append(list.getString(5) + ":\t" + moneyFormat(list.getDouble(3)) + "\t" + list.getString(2) + "\n");
        }
        showMessage("Transaction Details", buffer.toString());
    }


    public String getCheckingBalance() {
        Cursor list = database.getTransactionsAccount(database.getChecking());
        double total = 0;
        while (list.moveToNext()) {
            total += list.getDouble(database.getTotalLocation());
        }
        return moneyFormat(total);
    }
    public String getSavingsBalance() {
        Cursor list = database.getTransactionsAccount(database.getSavings());
        double total = 0;
        while (list.moveToNext()) {
            total += list.getDouble(database.getTotalLocation());
        }
        return moneyFormat(total);
    }
    public String getCreditBalance() {
        Cursor list = database.getTransactionsAccount(database.getCredit());
        double total = 0;
        while (list.moveToNext()) {
            total += list.getDouble(database.getTotalLocation());
        }
        return moneyFormat(total);
    }

    public String moneyFormat(double total) {
        return String.format("$.2f", total);
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
