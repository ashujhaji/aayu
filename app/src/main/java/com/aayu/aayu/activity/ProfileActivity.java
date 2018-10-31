package com.aayu.aayu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aayu.aayu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * created by ashu jha
 * ddibc 2018
 */

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mRoot, mRef;
    private SharedPreferences mPref;
    private TextView name, dob, mobile, ref, myPresc;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //set toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        //end

        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        mobile = findViewById(R.id.mobile);
        ref = findViewById(R.id.ref);
        myPresc = findViewById(R.id.myPresc);

        mRoot = FirebaseDatabase.getInstance().getReference();

        mPref = getSharedPreferences("data",MODE_PRIVATE);

        myPresc.setOnClickListener(this);

        userData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return super.onNavigateUp();
    }

    private void userData(){
        mRef = mRoot.child("users_list")
                .child(mPref.getString("uid",""));

        Toast.makeText(getApplicationContext(),String.valueOf(mPref.getString("uid","")),Toast.LENGTH_LONG).show();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
//                    name.setText(dataSnapshot.child("name").getValue().toString());
//                    ref.setText(dataSnapshot.getKey());
//                    dob.setText(dataSnapshot.child("dob").getValue().toString());
//                    mobile.setText(dataSnapshot.child("mobile").getValue().toString());
                }
               }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.myPresc:
                startActivity(new Intent(ProfileActivity.this, PrescriptionViewActivity.class));
                break;
        }
    }
}
