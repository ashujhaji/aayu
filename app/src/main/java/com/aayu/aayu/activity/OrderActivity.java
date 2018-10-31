package com.aayu.aayu.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aayu.aayu.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * created by ashu jha
 * ddibc 2018
 */

public class OrderActivity extends AppCompatActivity implements  View.OnClickListener{
    private ImageView current_img;
    private TextView current_txt, order_btn;
    private DatabaseReference mRoot, mRef;
    private SharedPreferences mPref;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //set toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        //end

        current_img = findViewById(R.id.current_img);
        current_txt = findViewById(R.id.current_price);
        order_btn = findViewById(R.id.order_btn);

        mPref = getSharedPreferences("data",MODE_PRIVATE);
        mRoot = FirebaseDatabase.getInstance().getReference();

        mRef = mRoot.child("users")
                .child(mPref.getString("uid",""));

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Glide.with(OrderActivity.this)
                            .load(dataSnapshot.child("current_Pres").child("url").getValue().toString())
                            .into(current_img);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        current_txt.setText("Delivery charge"+"  :  "+"Rs.30");

        order_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.order_btn:

                break;
        }
    }
}
