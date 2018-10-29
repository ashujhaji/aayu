package com.aayu.aayu.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aayu.aayu.Model.Medicines;
import com.aayu.aayu.R;
import com.aayu.aayu.adapters.MedicineAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MedicinesActivity extends AppCompatActivity {
    private List<Medicines> medicines = new ArrayList<>();
    private DatabaseReference mroot, mRef;
    private ProgressBar progress;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        //set toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        //end

        mroot = FirebaseDatabase.getInstance().getReference();

        progress = findViewById(R.id.progress);

        medicinesList();

    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return super.onNavigateUp();
    }

    private void medicinesList(){
        progress.setVisibility(View.VISIBLE);
        mRef = mroot.child("medicines");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for ( DataSnapshot child: children){
                    medicines.add(new Medicines(child.getKey(), child.child("status").getValue().toString()));
                }

                getRecyclerView(R.id.recycler,new MedicineAdapter(medicines,MedicinesActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getRecyclerView(int recyclerview_category, RecyclerView.Adapter adapter) {
        RecyclerView mRecyclerView = findViewById(recyclerview_category);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MedicinesActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);                                                            //todo : recyclerView method
        mRecyclerView.setAdapter(adapter);

        progress.setVisibility(View.GONE);
    }
}
