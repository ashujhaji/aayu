package com.aayu.aayu.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aayu.aayu.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

/**
 * created by ashu jha
 * ddibc 2018
 */

public class PrescriptionActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout upload_pres;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri imageUri;
    private ImageView img_preview;
    private FloatingActionButton upload_done;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private SharedPreferences mPref;
    private DatabaseReference mRoot, mRef;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        //set toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        //end

        upload_pres = findViewById(R.id.upload_pres);
        img_preview = findViewById(R.id.img_preview);
        upload_done = findViewById(R.id.upload_done);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mRoot = FirebaseDatabase.getInstance().getReference();

        mPref = getSharedPreferences("data",MODE_PRIVATE);

        upload_pres.setOnClickListener(this);
        upload_done.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.upload_pres:
                chooseImage();
                break;
            case R.id.upload_done:
                uploadImage();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //show image
            upload_pres.setVisibility(View.GONE);
            imageUri = data.getData();
            Glide.with(PrescriptionActivity.this)
                    .load(imageUri)
//                    .apply(new RequestOptions().override(1000, 400))
                    .into(img_preview);
            upload_done.setVisibility(View.VISIBLE);
            //end
        }
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

    private void chooseImage() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "select image"), PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {
        if (imageUri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            mRef = mRoot.child("users")
                    .child(mPref.getString("uid",""));

            final StorageReference ref = storageReference.child("prescription/"+mPref.getString("uid","")).child(createTransactionID());
            ref.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.d("img_uri", uri.toString());
                            mRef.child("prescriptions").child(taskSnapshot.getMetadata().getName()).child("url").setValue(uri.toString());
                            mRef.child("prescriptions").child(taskSnapshot.getMetadata().getName()).child("delivery_stat").setValue("ordered");
                            mRef.child("current_Pres").child("url").setValue(uri.toString());
                                }
                            });
                            progressDialog.dismiss();
                            Intent intent = new Intent(PrescriptionActivity.this, MainActivity.class);
                            startActivityForResult(intent, 1);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.setMessage("Uploading");
                        }
                    });
        }
    }

    public String createTransactionID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
