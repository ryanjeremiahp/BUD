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

    Button monthTotalSpendingButton;
    Button monthHousingButton;
    Button monthTransportationButton;
    Button monthFoodButton;
    Button monthUtilitiesButton;
    Button monthMedicalButton;
    Button monthSavingsButton;
    Button monthPersonalButton;

    Button yearTotalSpendingButton;
    Button yearHousingButton;
    Button yearTransportationButton;
    Button yearFoodButton;
    Button yearUtilitiesButton;
    Button yearMedicalButton;
    Button yearSavingsButton;
    Button yearPersonalButton;

    String savings = "Savings";
    String checking = "Checking";
    String credit = "Credit";

    double monthTotalSpent;
    double yearTotalSpent;

    Cursor monthList;
    Cursor yearList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);


        initializer();

        ViewMonthTotalSpending();
        ViewMonthHousing();
        ViewMonthTransportation();
        ViewMonthFood();
        ViewMonthUtilities();
        ViewMonthMedical();
        ViewMonthSavings();
        ViewMonthPersonal();

        ViewYearTotalSpending();
        ViewYearHousing();
        ViewYearTransportation();
        ViewYearFood();
        ViewYearUtilities();
        ViewYearMedical();
        ViewYearSavings();
        ViewYearPersonal();
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


    public void ViewMonthTotalSpending() {
        monthTotalSpendingButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (monthList.getCount() == 0) {
                            showMessage("Error", "No Transactions Found");
                            return;
                        }
                        String[] temp = database.getTimeTotal(monthList).split(" ", 2);

                        String message = "Money is: $" + temp[1] + "\n" +
                                "Money out: $" + temp[0];
                        showMessage("This Month's Total Spending\n", message);
                    }
                }
        );
    }

    public void ViewMonthHousing() {
        monthHousingButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (monthList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Housing";
                        String total = database.getTimeCategory(monthList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / monthTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
                        showMessage(categoryName + " Month's Spending", message);

                    }
                }
        );
    }
    public void ViewMonthTransportation() {
        monthTransportationButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (monthList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Transportation";
                        String total = database.getTimeCategory(monthList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / monthTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
                        showMessage(categoryName + " Month's Spending", message);
                    }
                }
        );

    }
    public void ViewMonthFood() {
        monthFoodButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (monthList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Food";
                        String total = database.getTimeCategory(monthList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / monthTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
                        showMessage(categoryName + " Month's Spending", message);

                    }
                }
        );
    }
    public void ViewMonthUtilities() {
        monthUtilitiesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (monthList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Utilities";
                        String total = database.getTimeCategory(monthList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / monthTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
                        showMessage(categoryName + " Month's Spending", message);

                    }
                }
        );
    }
    public void ViewMonthMedical() {
        monthMedicalButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (monthList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Medical";
                        String total = database.getTimeCategory(monthList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / monthTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
                        showMessage(categoryName + " Month's Spending", message);

                    }
                }
        );
    }
    public void ViewMonthSavings() {
        monthSavingsButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (monthList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Savings";
                        String total = database.getTimeCategory(monthList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / monthTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
                        showMessage(categoryName + " Month's Spending", message);

                    }
                }
        );
    }
    public void ViewMonthPersonal() {
        monthPersonalButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (monthList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Personal";
                        String total = database.getTimeCategory(monthList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / monthTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this months total spending";
                        showMessage(categoryName + " Month's Spending", message);

                    }
                }
        );
    }


    public void ViewYearTotalSpending() {
        yearTotalSpendingButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Error", "No Transactions Found");
                            return;
                        }
                        String[] temp = database.getTimeTotal(yearList).split(" ", 2);

                        String message = "Money is: $" + temp[1] + "\n" +
                                "Money out: $" + temp[0];
                        showMessage("This Year's Total Spending\n", message);
                    }
                }
        );
    }

    public void ViewYearHousing() {
        yearHousingButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Housing";
                        String total = database.getTimeCategory(yearList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
                        showMessage(categoryName + " Year's Spending", message);

                    }
                }
        );
    }
    public void ViewYearTransportation() {
        yearTransportationButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Transportation";
                        String total = database.getTimeCategory(yearList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
                        showMessage(categoryName + " Year's Spending", message);

                    }
                }
        );
    }
    public void ViewYearFood() {
        yearFoodButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Food";
                        String total = database.getTimeCategory(yearList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
                        showMessage(categoryName + " Year's Spending", message);

                    }
                }
        );
    }
    public void ViewYearUtilities() {
        yearUtilitiesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Utilities";
                        String total = database.getTimeCategory(yearList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
                        showMessage(categoryName + " Year's Spending", message);

                    }
                }
        );
    }
    public void ViewYearMedical() {
        yearMedicalButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Medical";
                        String total = database.getTimeCategory(yearList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
                        showMessage(categoryName + " Year's Spending", message);

                    }
                }
        );
    }
    public void ViewYearSavings() {
        yearSavingsButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Savings";
                        String total = database.getTimeCategory(yearList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
                        showMessage(categoryName + " Year's Spending", message);

                    }
                }
        );
    }
    public void ViewYearPersonal() {
        yearPersonalButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (yearList.getCount() == 0) {
                            showMessage("Welp", "There was no spending in this category this month");
                            return;
                        }
                        String categoryName = "Personal";
                        String total = database.getTimeCategory(yearList, categoryName);
                        double totalDouble = Double.parseDouble(total);
                        double percentOfSpending = totalDouble / yearTotalSpent;

                        String message = "$" + total +
                                "\n"+String.format("%.2f", percentOfSpending)+"% of this year's total spending";
                        showMessage(categoryName + " Year's Spending", message);

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
