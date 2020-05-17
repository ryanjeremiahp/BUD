package com.example.bud;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    GlobalClass globalClass;
    private DrawerLayout drawer;
    //Wilfredo made these two lines'
    /*private SQLiteDatabase mDatabase;
    private FinanceAdapter mAdapter;*/

    public DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalClass= (GlobalClass) getApplicationContext();
        globalClass.setDatabase(new DatabaseHelper(this));
        database = globalClass.getDatabase();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_closer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TransactionFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_addTransaction);
        }

    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_finances:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FinanceFragment()).commit();
                break;
            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HelpFragment()).commit();
                break;
            case R.id.nav_addTransaction:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TransactionFragment()).commit();
                break;
            case R.id.nav_activityAccounts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AccountsFragment()).commit();
                break;
            case R.id.nav_analysis:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AnalyticsFragment()).commit();

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    public DatabaseHelper getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseHelper database) {
        this.database = database;
    }

}
