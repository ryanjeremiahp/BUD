
package com.example.bud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.time.LocalDate;
import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "budget.db";

    public static final String TABLE_NAME = "Transactions";

    public static final String LineItemID_COL1 = "LineItemID";

    public static final String Account_COL2 = "Account";
    public static final String Date_COL3 = "Date";
    public static final String Total_COL4 = "Total";
    public static final String Category_COL5 = "Category";
    public static final String Place_COL6 = "Place";
    public static final String Notes_COL7 = "Notes";
    public static final String Subcategory_COL8 = "Subcategory";

    public static final int totalLocation = 3;
    public static final int categoryLocation = 4;

//todo way to grab strings from strings.xml R.array.account
    public static final String checking = "Checking";
    public static final String savings = "Savings";
    public static final String credit = "Credit";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" +
                LineItemID_COL1 + " integer primary key autoincrement not null," +
                Account_COL2 + " text not null," +
                Date_COL3 + " text not null," +
                Total_COL4 + " real not null," +
                Category_COL5 + " text not null," +
                Place_COL6 + " text not null," +
                Notes_COL7 + " text," +
                Subcategory_COL8 + " text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String account, String date, String total, String category, String place, String notes, String subcategory) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Account_COL2, account);
        contentValues.put(Date_COL3, date);
        contentValues.put(Total_COL4, total);
        contentValues.put(Category_COL5, category);
        contentValues.put(Place_COL6, place);
        contentValues.put(Notes_COL7, notes);
        contentValues.put(Subcategory_COL8, subcategory);

       long result = database.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }



    public Cursor getAllTransactions() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }

    public Cursor getCheckingTransactions() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME
                        + " where " + Account_COL2 + " = '" + checking + "'",
                null);
        return result;
    }

    public Cursor getSavingsTransactions() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME
                        + " where " + Account_COL2 + " = '" + savings + "'",
                null);
        return result;
    }

    public Cursor getCreditTransactions() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME
                        + " where " + Account_COL2 + " = '" + credit + "'",
                null);
        return result;
    }



    public String getCheckingBalance() {
        Cursor transactionsFromAccount = this.getCheckingTransactions();

        double tempTotal = 0;

        while (transactionsFromAccount.moveToNext()) {
            //should return Transaction.total
            tempTotal += transactionsFromAccount.getDouble(totalLocation);
        }

        return String.valueOf(tempTotal);
    }

    public String getSavingsBalance() {
        Cursor transactionsFromAccount = this.getSavingsTransactions();

        double tempTotal = 0;

        while (transactionsFromAccount.moveToNext()) {
            //should return Transaction.total
            tempTotal += transactionsFromAccount.getDouble(3);
        }

        return String.valueOf(tempTotal);
    }

    public String getCreditBalance() {
        Cursor transactionsFromAccount = this.getCreditTransactions();

        double tempTotal = 0;

        while (transactionsFromAccount.moveToNext()) {
            //should return Transaction.total
            tempTotal += transactionsFromAccount.getDouble(3);
        }

        return String.valueOf(tempTotal);
    }


    public String getMonth () {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;

        String monthString = Integer.toString(month);
        if (monthString.length() <2) {
            monthString = '0' + monthString;
        }

        return monthString;
    }

    public String getYear () {
        Calendar calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        return year;
    }



    public Cursor getMTDList() {
        String likeDate = "'" + getMonth() + "/__/" + getYear() + "'";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME +
                        " where Date like " + likeDate
                , null);
        return result;
    }

    public Cursor getYTDList() {
        String likeDate = "'__/__/" + getYear() + "'";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME +
                        " where Date like " + likeDate
                , null);
        return result;
    }


    public String getMTDTotal() {
        Cursor list = this.getMTDList();

        double positiveTotal = 0;
        double negativeTotal = 0;

        while (list.moveToNext()) {
            if (list.getDouble(totalLocation) > 0) {
                positiveTotal += list.getDouble(totalLocation);
            } else {
                negativeTotal += list.getDouble(totalLocation);
            }
        }

        return String.valueOf(positiveTotal) + " " + String.valueOf(negativeTotal);
    }

    public String getYTDTotal() {
        Cursor list = this.getYTDList();

        double positiveTotal = 0;
        double negativeTotal = 0;

        while (list.moveToNext()) {
            if (list.getDouble(totalLocation) > 0) {
                positiveTotal += list.getDouble(totalLocation);
            } else {
                negativeTotal += list.getDouble(totalLocation);
            }
        }

        return String.valueOf(positiveTotal) + " " + String.valueOf(negativeTotal);

    }

    public String getMTDHousing() {
        Cursor list = this.getMTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Housing")) {
                total += list.getDouble(totalLocation);
            }

        }

        return String.valueOf(total);
    }

    public String getYTDHousing() {
        Cursor list = this.getYTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Housing")) {
                total += list.getDouble(totalLocation);
            }

        }

        return String.valueOf(total);

    }

    public String getMTDTransport() {
        Cursor list = this.getMTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Transportation")) {
                total += list.getDouble(totalLocation);
            }

        }

        return String.valueOf(total);
    }

    public String getYTDTransport() {
        Cursor list = this.getYTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Transportation")) {
                total += list.getDouble(totalLocation);
            }

        }
        return String.valueOf(total);
    }

    public String getMTDFood() {
        Cursor list = this.getMTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Food")) {
                total += list.getDouble(totalLocation);
            }
        }
        return String.valueOf(total);
    }

    public String getYTDFood() {
        Cursor list = this.getYTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Food")) {
                total += list.getDouble(totalLocation);
            }
        }
        return String.valueOf(total);
    }

    public String getMTDUtilities() {
        Cursor list = this.getMTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Utilities")) {
                total += list.getDouble(totalLocation);
            }
        }
        return String.valueOf(total);
    }

    public String getYTDUtilities() {
        Cursor list = this.getYTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Utilities")) {
                total += list.getDouble(totalLocation);
            }
        }
        return String.valueOf(total);
    }

    public String getMTDMedical() {
        Cursor list = this.getMTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Medical")) {
                total += list.getDouble(totalLocation);
            }

        }

        return String.valueOf(total);
    }

    public String getYTDMedical() {
        Cursor list = this.getYTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Medical")) {
                total += list.getDouble(totalLocation);
            }

        }

        return String.valueOf(total);
    }

    public String getMTDSavings() {
        Cursor list = this.getMTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Savings")) {
                total += list.getDouble(totalLocation);
            }

        }

        return String.valueOf(total);
    }

    public String getYTDSavings() {
        Cursor list = this.getYTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Savings")) {
                total += list.getDouble(totalLocation);
            }

        }

        return String.valueOf(total);
    }

    public String getMTDPersonal() {
        Cursor list = this.getMTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Personal")) {
                total += list.getDouble(totalLocation);
            }

        }

        return String.valueOf(total);
    }

    public String getYTDPersonal() {
        Cursor list = this.getYTDList();

        double total = 0;

        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals("Personal")) {
                total += list.getDouble(totalLocation);
            }

        }

        return String.valueOf(total);
    }
}