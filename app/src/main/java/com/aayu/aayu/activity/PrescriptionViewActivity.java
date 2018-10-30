package com.aayu.aayu.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.aayu.aayu.Model.Prescriptions;
import com.aayu.aayu.R;
import com.aayu.aayu.adapters.PresViewPagerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionViewActivity extends AppCompatActivity {

    private DatabaseReference mRoot, mRef;
    private ViewPager view_pager;
    private SharedPreferences mPref;
    private List<Prescriptions> list = new ArrayList<>();
    private PresViewPagerAdapter adapter;
    private ProgressBar progress;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_view);

        //set toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        //end

        mRoot = FirebaseDatabase.getInstance().getReference();
        mPref = getSharedPreferences("data",MODE_PRIVATE);

        view_pager = findViewById(R.id.view_pager);
        progress = findViewById(R.id.progress);

        adapter= new PresViewPagerAdapter(getSupportFragmentManager(), list);

        prescriptions();
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
    private void prescriptions(){
        mRef = mRoot.child("users")
                .child(mPref.getString("uid",""))
                .child("prescriptions");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children){
                    list.add(new Prescriptions(child.getKey(),
                            child.child("url").getValue().toString(),
                            child.child("delivery_stat").getValue().toString()));
                }
                view_pager.setAdapter(adapter);
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
