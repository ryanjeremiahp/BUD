
package com.example.bud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

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
    public static final int subcategoryLocation = 7;

    //todo Find way to take category names from strings.xml R.array.account
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

        return result != -1;

    }


    public Cursor getAllTransactions() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }

    public Cursor getTransactionsAccount(String account) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME

                        + " where " + Account_COL2 + " = '" + account + "'",
                null);
        return result;
    }

    public String getBalance(String account, int categoryLocation) {
        Cursor transactionsFromAccount = this.getTransactionsAccount(account);

        double tempTotal = 0;
        while (transactionsFromAccount.moveToNext()) {
            tempTotal += transactionsFromAccount.getDouble(categoryLocation);
        }
        return String.valueOf(tempTotal);
    }


    public String getMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;

        String monthString = Integer.toString(month);
        if (monthString.length() < 2) {
            monthString = '0' + monthString;
        }

        return monthString;
    }

    public String getYear() {
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

    public String getTimeTotal(Cursor list) {
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

    public String getTimeCategory(Cursor list, String categoryName) {
        double total = 0;
        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals(categoryName)) {
                total += list.getDouble(totalLocation);
            }
        }
        return String.valueOf(total);
    }

    public String getTimeSubcategory(Cursor list, String subcategoryName) {
        double total = 0;
        while (list.moveToNext()) {
            if (list.getString(subcategoryLocation).equals(subcategoryName)) {
                total += list.getDouble(totalLocation);
            }
        }
        return String.valueOf(total);
    }

    public String getTimeCategorySubcategory(Cursor list, String category, String subcategory) {
        double total = 0;
        while (list.moveToNext()) {
            if (list.getString(categoryLocation).equals(category)) {
                if (list.getString(subcategoryLocation).equals(subcategory)) {
                    total += list.getDouble(totalLocation);
                }
            }
        }
        return String.valueOf(total);
    }


    public static int getTotalLocation() {
        return totalLocation;
    }

    public static int getCategoryLocation() {
        return categoryLocation;
    }

    public static int getSubcategoryLocation() {
        return subcategoryLocation;
    }

    public static String getChecking() {
        return checking;
    }

    public static String getSavings() {
        return savings;
    }

    public static String getCredit() {
        return credit;
    }
}