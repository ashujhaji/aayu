package com.aayu.aayu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aayu.aayu.BuildConfig;
import com.aayu.aayu.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    private RelativeLayout nav_drawer, profile;
    private DrawerLayout drawer;
    private FirebaseRemoteConfig firebaseRemoteConfig;
    private ImageView tip_img;
    private CardView prescription, medicines, doctors, cghs,myPresc, order ;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        nav_drawer = findViewById(R.id.nav_drawer);
        drawer = findViewById(R.id.drawer_layout);
        profile = findViewById(R.id.profile);
        tip_img = findViewById(R.id.tip_img);
        prescription = findViewById(R.id.prescription);
        medicines = findViewById(R.id.medicines);
        doctors = findViewById(R.id.doctors);
        cghs = findViewById(R.id.cghs);
        myPresc = findViewById(R.id.myPresc);
        order = findViewById(R.id.order);

        remoteConfigData();

        nav_drawer.setOnClickListener(this);
        profile.setOnClickListener(this);
        prescription.setOnClickListener(this);
        medicines.setOnClickListener(this);
        doctors.setOnClickListener(this);
        cghs.setOnClickListener(this);
        myPresc.setOnClickListener(this);
        order.setOnClickListener(this);

        //navigation drawer setup
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //end
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nav_drawer:
                drawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            case R.id.prescription:
                startActivity(new Intent(MainActivity.this, PrescriptionActivity.class));
                break;
            case R.id.medicines:
                startActivity(new Intent(MainActivity.this, MedicinesActivity.class));
                break;
            case R.id.doctors:
                startActivity(new Intent(MainActivity.this, DoctorsActivity.class));
                break;
            case R.id.cghs:
                startActivity(new Intent(MainActivity.this, CghsActivity.class));
                break;
            case R.id.myPresc:
                startActivity(new Intent(MainActivity.this, PrescriptionViewActivity.class));
                break;
            case R.id.order:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void remoteConfigData(){
        //remote config data
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        firebaseRemoteConfig.setConfigSettings(configSettings);
        firebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);
        firebaseRemoteConfig.fetch(300).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    firebaseRemoteConfig.activateFetched();
                }
                String img = firebaseRemoteConfig.getString("tips_img");
                Glide.with(MainActivity.this)
                        .load(img)
                        .into(tip_img);
            }
        });
        //end
    }
}
