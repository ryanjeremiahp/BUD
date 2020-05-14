package com.example.bud;

import android.app.Application;

public class GlobalClass extends Application {

    private DatabaseHelper database;


    public DatabaseHelper getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseHelper database) {
        this.database = database;
    }


}
