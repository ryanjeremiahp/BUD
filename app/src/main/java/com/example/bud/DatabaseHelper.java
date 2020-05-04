
package com.example.bud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "budget.db";
    public static final String TABLE_NAME = "transactions.db";
    public static final String LineItemID_COL1 = "LineItemID";
    public static final String Account_COL2 = "Account";
    public static final String Date_COL3 = "Date";
    public static final String Total_COL4 = "Total";
    public static final String Category_COL5 = "Category";
    public static final String Place_COL6 = "Place";
    public static final String Notes_COL7 = "Notes";
    public static final String Subcategory_COL8 = "Subcategory";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create transaction table
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +
                        LineItemID_COL1 + "(integer primary key autoincrement not null," +
                        Account_COL2 + " text not null," +
                        Date_COL3 + " text not null," +
                        Total_COL4 + " real not null," +
                        Category_COL5 + " text not null," +
                        Place_COL6 + " text not null," +
                        Notes_COL7 + " text," +
                        Subcategory_COL8 + "text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}