package com.example.donatenowapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donatenowapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity {
    EditText edtusername,edtphone,edtaddress,edtBlood;
    TextView txtupdate;
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri mImageUri;
    DatabaseReference databaseReference2;
    String uID;
    CircleImageView imageView;
    ProgressDialog progressDialog;
    StorageReference mStorageRef;
    StorageTask mUploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        edtusername = findViewById(R.id.edtusername);
        imageView = findViewById(R.id.imagevIew);
        edtphone = findViewById(R.id.edtphone);
        progressDialog = new ProgressDialog(UserProfileActivity.this);
        edtBlood = findViewById(R.id.edtBlood);
        edtaddress = findViewById(R.id.edtaddress);
        txtupdate = findViewById(R.id.txtupdate);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        uID = currentUser.getUid();
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("User").child(uID);
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uName = dataSnapshot.child("uName").getValue(String.class);
                String uAddress = dataSnapshot.child("uAddress").getValue(String.class);
                String uContactNo = dataSnapshot.child("uPhone").getValue(String.class);
                String uImage = dataSnapshot.child("uImg").getValue(String.class);
                edtusername.setText(uName);
                edtphone.setText(uContactNo);
                edtaddress.setText(uAddress);
                Picasso.with(UserProfileActivity.this).load(uImage).placeholder(R.mipmap.image_large).into(imageView);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        txtupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference2.child("uName").setValue(edtusername.getText().toString());
                databaseReference2.child("uAddress").setValue(edtaddress.getText().toString());
                databaseReference2.child("uPhone").setValue(edtphone.getText().toString());
                Toast.makeText(UserProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(imageView);
            if(mImageUri!= null)
            {
                progressDialog.setMessage("Please wait ...");
                progressDialog.show();
                mStorageRef = FirebaseStorage.getInstance().getReference("Profile_Images");
                final StorageReference storageReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
                mUploadTask = storageReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                databaseReference2.child("uImg").setValue(uri.toString());
                                progressDialog.dismiss();
                            }
                        });
                    }
                });
            }
        }

    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}