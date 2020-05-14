package com.example.bud;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnalyticSubcategoryActivity extends AppCompatActivity {
    GlobalClass globalClass;
    DatabaseHelper database;

    Button insurance;
    Button lodging;
    Button fastFood;
    Button lifestyle;
    Button entertainment;
    Button miscellaneous;

    Button gas;
    Button loan;
    Button insuranceCar;
    Button maintenance;


    Cursor yearList;
    double yearTotalSpent;
    double yearTotalTransportation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytic_subcategory);

        globalClass = (GlobalClass) getApplicationContext();
        database = globalClass.getDatabase();

        initializer();
    }

    public void initializer() {
        insurance = findViewById(R.id.InsuracnceButton);
        lodging = findViewById(R.id.LodgingCostButton);
        fastFood = findViewById(R.id.FastFoodButton);
        lifestyle = findViewById(R.id.LifestyleButton);
        entertainment = findViewById(R.id.EntertainmentButton);
        miscellaneous = findViewById(R.id.MaintenancePercentButton);

        gas = findViewById(R.id.GasPercentButton);
        loan = findViewById(R.id.LoanPercentButton);
        insuranceCar = findViewById(R.id.InsuranceCarButton);
        maintenance = findViewById(R.id.MaintenancePercentButton);

        yearList = database.getYTDList();

        String[] temp2 = database.getTimeTotal(yearList).split(" ", 2);
        yearTotalSpent = Double.parseDouble(temp2[0]);

        String temp = database.getTimeCategory(yearList, "Transportation");
        yearTotalTransportation = Double.parseDouble(temp);
    }

    public void ViewInsurance() {
        insurance.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this year");
                            return;
                        }
                        String subcategoryName = "Insurance";
                        String total = database.getTimeSubcategory(yearList, subcategoryName);

                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = String.format("$%.2f", totalDouble) +
                                "\n" + String.format("%.2f", percentOfSpending) + "% of this year's total spending";
                        showMessage(subcategoryName, message);
                    }
                }
        );

    }
    public void ViewLodging() {
        lodging.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this year");
                            return;
                        }
                        String category1Total = database.getTimeCategory(yearList, "Housing");
                        String category2Total = database.getTimeCategory(yearList, "Utilities");

                        double totalDouble = Double.parseDouble(category1Total) + Double.parseDouble(category2Total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = String.format("$%.2f", totalDouble) +
                                "\n" + String.format("%.2f", percentOfSpending) + "% of this year's total spending";
                        showMessage("Lodging", message);
                    }
                }
        );
    }
    public void ViewFastFood() {
        fastFood.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this year");
                            return;
                        }
                        String subcategoryName = "Eating Out";
                        String total = database.getTimeSubcategory(yearList, subcategoryName);

                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = String.format("$%.2f", totalDouble) +
                                "\n" + String.format("%.2f", percentOfSpending) + "% of this year's total spending";
                        showMessage(subcategoryName, message);
                    }
                }
        );
    }
    public void ViewLifestyle() {
        lifestyle.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this year");
                            return;
                        }
                        String subcategoryName = "Lifestyle";
                        String total = database.getTimeSubcategory(yearList, subcategoryName);

                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = String.format("$%.2f", totalDouble) +
                                "\n" + String.format("%.2f", percentOfSpending) + "% of this year's total spending";
                        showMessage(subcategoryName, message);
                    }
                }
        );
    }
    public void ViewEntertainment() {
        entertainment.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this year");
                            return;
                        }
                        String subcategoryName = "Entertainment";
                        String total = database.getTimeSubcategory(yearList, subcategoryName);

                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = String.format("$%.2f", totalDouble) +
                                "\n" + String.format("%.2f", percentOfSpending) + "% of this year's total spending";
                        showMessage(subcategoryName, message);
                    }
                }
        );
    }
    public void ViewMiscellaneous() {
        miscellaneous.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this year");
                            return;
                        }
                        String subcategoryName = "Miscellaneous";
                        String total = database.getTimeSubcategory(yearList, subcategoryName);

                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = String.format("$%.2f", totalDouble) +
                                "\n" + String.format("%.2f", percentOfSpending) + "% of this year's total spending";
                        showMessage(subcategoryName, message);
                    }
                }
        );
    }


    public void ViewGasOfTransportation() {
        gas.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this year");
                            return;
                        }
                        String subcategoryName = "Gas";
                        String total = database.getTimeSubcategory(yearList, subcategoryName);

                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalTransportation;

                        String message = String.format("$%.2f", totalDouble) +
                                "\n" + String.format("%.2f", percentOfSpending) + "% of this year's total transportation spending";
                        showMessage(subcategoryName, message);
                    }
                }
        );
    }
    public void ViewLoanOfTransportation() {
        loan.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this year");
                            return;
                        }
                        String subcategoryName = "Loan";
                        String total = database.getTimeSubcategory(yearList, subcategoryName);

                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalTransportation;

                        String message = String.format("$%.2f", totalDouble) +
                                "\n" + String.format("%.2f", percentOfSpending) + "% of this year's total transportation spending";
                        showMessage(subcategoryName, message);
                    }
                }
        );
    }
    public void ViewInsuranceOfTransportation() {
        insuranceCar.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        String total = database.getTimeCategorySubcategory(yearList, "Insurance", "Transportation");

                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalTransportation;

                        String message = String.format("$%.2f", totalDouble) +
                                "\n" + String.format("%.2f", percentOfSpending) + "% of this year's total transportation spending";
                        showMessage("Car Insurance", message);
                    }
                }
        );
    }
    public void ViewMaintenanceOfTransportation() {
        maintenance.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this year");
                            return;
                        }
                        String subcategoryName = "Maintenance";
                        String total = database.getTimeSubcategory(yearList, subcategoryName);

                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalTransportation;

                        String message = String.format("$%.2f", totalDouble) +
                                "\n" + String.format("%.2f", percentOfSpending) + "% of this year's total transportation spending";
                        showMessage(subcategoryName, message);
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
