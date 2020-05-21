package com.example.bud;

import android.database.Cursor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class AnalysisActivity extends AppCompatActivity {
    DatabaseHelper database;

    Button viewAllTransactionsButton;
    Button monthTotalSpendingButton, monthHousingButton, monthTransportationButton, monthFoodButton,
    monthUtilitiesButton, monthMedicalButton, monthSavingsButton, monthPersonalButton;
    Button yearTotalSpendingButton, yearHousingButton, yearTransportationButton, yearFoodButton,
     yearUtilitiesButton, yearMedicalButton, yearSavingsButton, yearPersonalButton;

    double monthTotalSpent, yearTotalSpent;
    Cursor monthList, yearList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        database = globalClass.getDatabase();

        initializer();


    }

    public void initializer() {
        viewAllTransactionsButton = findViewById(R.id.AddTransaction);

        monthTotalSpendingButton = findViewById(R.id.totalSpendingMTDButton);
        monthHousingButton = findViewById(R.id.housingMTD);
        monthTransportationButton = findViewById(R.id.transportationMTD);
        monthFoodButton = findViewById(R.id.foodMTD);
        monthUtilitiesButton = findViewById(R.id.utilitiesMTD);
        monthMedicalButton = findViewById(R.id.medicalMTD);
        monthSavingsButton = findViewById(R.id.savingsMTD);
        monthPersonalButton = findViewById(R.id.personalMTD);

        yearTotalSpendingButton = findViewById(R.id.totalSpendingYTDButton);
        yearHousingButton = findViewById(R.id.housingYTD);
        yearTransportationButton = findViewById(R.id.transportationYTD);
        yearFoodButton = findViewById(R.id.foodYTD);
        yearUtilitiesButton = findViewById(R.id.utilitiesYTD);
        yearMedicalButton = findViewById(R.id.medicalYTD);
        yearSavingsButton = findViewById(R.id.savingsYTD);
        yearPersonalButton = findViewById(R.id.personalYTD);

        monthList = database.getMTDList();
        yearList = database.getYTDList();


        String[] temp = database.getTimeTotal(monthList).split(" ", 2);
        monthTotalSpent = Double.parseDouble(temp[0]);
        String[] temp2 = database.getTimeTotal(yearList).split(" ", 2);
        yearTotalSpent = Double.parseDouble(temp2[0]);


    }

    public void ViewAllTransactions(View view) {
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

        showMessage("Transaction List", stringBuffer.toString());
    }


    public void ViewMonthTotalSpending(View view) {
        String[] temp = database.getTimeTotal(monthList).split(" ", 2);
        String message = "Money in: $" + temp[1] + "\n" +
                "Money out: $" + temp[0];
        showMessage("This Month's Total Spending\n", message);
    }


    public void ViewMonthHousing(View view) {
        String categoryName = "Housing";
        String total = database.getTimeCategory(monthList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / monthTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
        showMessage(categoryName + " Month's Spending", message);
    }
    public void ViewMonthTransportation(View view) {
        String categoryName = "Transportation";
        String total = database.getTimeCategory(monthList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / monthTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
        showMessage(categoryName + " Month's Spending", message);
    }
    public void ViewMonthFood(View view) {
        String categoryName = "Food";
        String total = database.getTimeCategory(monthList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / monthTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
        showMessage(categoryName + " Month's Spending", message);
    }
    public void ViewMonthUtilities(View view) {
        String categoryName = "Utilities";
        String total = database.getTimeCategory(monthList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / monthTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
        showMessage(categoryName + " Month's Spending", message);
    }
    public void ViewMonthMedical(View view) {
        String categoryName = "Medical";
        String total = database.getTimeCategory(monthList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / monthTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
        showMessage(categoryName + " Month's Spending", message);
    }
    public void ViewMonthSavings(View view) {
        String categoryName = "Savings";
        String total = database.getTimeCategory(monthList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / monthTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
        showMessage(categoryName + " Month's Spending", message);
    }
    public void ViewMonthPersonal(View view) {
        String categoryName = "Personal";
        String total = database.getTimeCategory(monthList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / monthTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
        showMessage(categoryName + " Month's Spending", message);
    }


    public void ViewYearTotalSpending(View view) {
        if (yearList.getCount() == 0) {
            showMessage("Error", "No Transactions Found");
            return;
        }
        String[] temp = database.getTimeTotal(yearList).split(" ", 2);

        String message = "Money is: $" + temp[1] + "\n" +
                "Money out: $" + temp[0];
        showMessage("This Year's Total Spending\n", message);
    }

    public void ViewYearHousing(View view) {
        String categoryName = "Housing";
        String total = database.getTimeCategory(yearList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / yearTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
        showMessage(categoryName + " Year's Spending", message);
    }
    public void ViewYearTransportation(View view) {
        String categoryName = "Transportation";
        String total = database.getTimeCategory(yearList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / yearTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
        showMessage(categoryName + " Year's Spending", message);
    }
    public void ViewYearFood(View view) {
        String categoryName = "Food";
        String total = database.getTimeCategory(yearList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / yearTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
        showMessage(categoryName + " Year's Spending", message);
    }
    public void ViewYearUtilities(View view) {
        String categoryName = "Utilities";
        String total = database.getTimeCategory(yearList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / yearTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
        showMessage(categoryName + " Year's Spending", message);
    }
    public void ViewYearMedical(View view) {
        String categoryName = "Medical";
        String total = database.getTimeCategory(yearList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / yearTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
        showMessage(categoryName + " Year's Spending", message);
    }
    public void ViewYearSavings(View view) {
        String categoryName = "Savings";
        String total = database.getTimeCategory(yearList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / yearTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
        showMessage(categoryName + " Year's Spending", message);
    }
    public void ViewYearPersonal(View view) {
        String categoryName = "Personal";
        String total = database.getTimeCategory(yearList, categoryName);
        double totalDouble = Double.parseDouble(total);
        double percentOfSpending = totalDouble / yearTotalSpent;

        String message = "$" + total +
                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
        showMessage(categoryName + " Year's Spending", message);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}
