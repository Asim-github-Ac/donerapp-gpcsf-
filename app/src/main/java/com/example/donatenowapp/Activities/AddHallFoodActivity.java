package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.donatenowapp.Model.HallFood;
import com.example.donatenowapp.Model.Products;
import com.example.donatenowapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddHallFoodActivity extends AppCompatActivity {
    ImageView imageView2;
    EditText edtname,edtquantity,edtduration,edtphone;
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri mImageUri;
    // Database
    StorageReference mStorageRef;
    DatabaseReference mDatabaseRef,databaseReference2;
    StorageTask mUploadTask;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hall_food);
        edtname = findViewById(R.id.edtname);
        edtquantity = findViewById(R.id.edtquantity);
        edtduration = findViewById(R.id.edtduration);
        edtphone = findViewById(R.id.edtphone);
        firebaseAuth = FirebaseAuth.getInstance();
        imageView2 = findViewById(R.id.imageView2);
        progressDialog = new ProgressDialog(this);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(imageView2);
        }

    }
    public void svaeee(View view) {
        if(edtname.getText().toString().isEmpty()){
            edtname.setError("Please enter name");
        }else if(edtphone.getText().toString().isEmpty()){
            edtphone.setError("Please enter phone");
        }else if(edtduration.getText().toString().isEmpty()){
            edtduration.setError("Please enter duration");
        }else {
            mStorageRef = FirebaseStorage.getInstance().getReference("Hall_Food_Images");
            mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Hall_Foods");
            uploadResult();
        }
    }
    private void uploadResult() {
        if(mImageUri!= null)
        {
            progressDialog.setMessage("Please wait ...");
            progressDialog.show();
            final StorageReference storageReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
            mUploadTask = storageReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            String hID = firebaseUser.getUid();
                            String autoId = mDatabaseRef.push().getKey();
                            HallFood hallFood = new HallFood(
                                   edtname.getText().toString(),
                                    edtquantity.getText().toString(),
                                    edtduration.getText().toString(),
                                    edtphone.getText().toString(),
                                    uri.toString(),
                                    hID,
                                    autoId,
                                    "No");
                            Toast.makeText(AddHallFoodActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            mDatabaseRef.child(autoId).setValue(hallFood);
                            progressDialog.dismiss();
                        }
                    });
                }
            });
        }else {
            Toast.makeText(this, "Choose Image", Toast.LENGTH_SHORT).show();
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}