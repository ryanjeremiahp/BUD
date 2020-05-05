package com.example.bud;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnalysisActivity extends AppCompatActivity {
    DatabaseHelper database;

    Button viewAllTransactionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        viewAllTransactionsButton = findViewById(R.id.AddTransaction);

        ViewAllTransactions();
    }

    public void ViewAllTransactions() {
        viewAllTransactionsButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor result = database.getAllTransactions();
                        if (result.getCount() == 0) {
                            showMessage("Error", "No Transactions Found");
                            return;
                        }

                        StringBuffer stringBuffer = new StringBuffer();
                        while (result.moveToNext()) {
                            stringBuffer.append("LineItem: " + result.getString(0) + "\n");
                            stringBuffer.append("Account: " + result.getString(1) + "\n");
                            stringBuffer.append("Total: " + result.getString(2) + "\n");
                            stringBuffer.append("Category: " + result.getString(3) + "\n");
                            stringBuffer.append("Place: " + result.getString(4) + "\n");
                            stringBuffer.append("Notes: " + result.getString(5) + "\n");
                            stringBuffer.append("Subcategory: " + result.getString(6) + "\n\n");
                        }

                        showMessage("TransactionList", stringBuffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
