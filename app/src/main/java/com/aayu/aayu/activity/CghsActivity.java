package com.aayu.aayu.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.aayu.aayu.Model.Cghs;
import com.aayu.aayu.Model.Medicines;
import com.aayu.aayu.R;
import com.aayu.aayu.adapters.CghsAdapter;
import com.aayu.aayu.adapters.MedicineAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CghsActivity extends AppCompatActivity {

    private List<Cghs> list = new ArrayList<>();
    private DatabaseReference mroot, mRef;
    private ProgressBar progress;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cghs);

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

        cghsList();
    }

    private void cghsList(){
        progress.setVisibility(View.VISIBLE);
        mRef = mroot.child("cghs");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for ( DataSnapshot child: children){
                    list.add(new Cghs(child.child("field1").getValue().toString(),
                                    child.child("field2").getValue().toString(),
                                    child.child("field3").getValue().toString()));
                }

                getRecyclerView(R.id.recycler,new CghsAdapter(list,CghsActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getRecyclerView(int recyclerview_category, RecyclerView.Adapter adapter) {
        RecyclerView mRecyclerView = findViewById(recyclerview_category);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CghsActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);                                                            //todo : recyclerView method
        mRecyclerView.setAdapter(adapter);

        progress.setVisibility(View.GONE);
    }
}
