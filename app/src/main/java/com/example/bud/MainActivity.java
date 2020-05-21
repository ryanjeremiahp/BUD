package com.example.bud;

import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    GlobalClass globalClass;
    private DrawerLayout drawer;
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
                break;
            case R.id.nav_AI:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SecondFragment()).commit();
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
