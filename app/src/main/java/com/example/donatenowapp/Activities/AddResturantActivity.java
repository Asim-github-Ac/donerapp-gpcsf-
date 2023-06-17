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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class AddResturantActivity extends AppCompatActivity {
    ImageView imageView;
    EditText edtname,edtaddress,edtdes,edtwebUrl,edtPhone;
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri mImageUri;
    // Database
    StorageReference mStorageRef;
    DatabaseReference mDatabaseRef;
    StorageTask mUploadTask;
    FirebaseAuth firebaseAuthe;
    ProgressDialog progressDialog;
    String uID;
    String longi,lati;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resturant);
        imageView = findViewById(R.id.imageView);
        edtname = findViewById(R.id.edtname);
        edtaddress = findViewById(R.id.edtaddress);
        edtdes = findViewById(R.id.edtdes);
        edtwebUrl = findViewById(R.id.edtwebUrl);
        edtPhone = findViewById(R.id.edtPhone);
        progressDialog = new ProgressDialog(this);
        firebaseAuthe= FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuthe.getCurrentUser();
        uID = firebaseUser.getUid();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        Places.initialize(getApplicationContext(),"AIzaSyCc9MOpE2wArUTUcA67RHFfpI-BfIHrDCs");
        edtaddress.setFocusable(false);
        edtaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,
                        Place.Field.LAT_LNG, Place.Field.NAME);
                String country = "PK";
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,
                        fieldList)
                        .setCountry(country)
                        .build(AddResturantActivity.this);

                startActivityForResult(intent,100);
            }
        });

    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(imageView);
        }
        if (requestCode == 100 && resultCode == RESULT_OK){
            Place place = Autocomplete.getPlaceFromIntent(data);
            edtaddress.setText(place.getAddress());
            LatLng queriedLocation = place.getLatLng();
            longi = String.valueOf(queriedLocation.longitude);
            lati = String.valueOf(queriedLocation.latitude);

        }else if(resultCode == AutocompleteActivity.RESULT_ERROR){
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getApplicationContext(), status.getStatusMessage()
                    ,Toast.LENGTH_SHORT).show();
        }
    }

    public void submit(View view) {
        mStorageRef = FirebaseStorage.getInstance().getReference("Images");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Restaurant");
        uploadResult();
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
                            Restaurant restaurant = new Restaurant(
                                    edtname.getText().toString(),
                                    edtaddress.getText().toString(),
                                    edtdes.getText().toString(),
                                    edtwebUrl.getText().toString(),
                                    edtPhone.getText().toString(),
                                    uri.toString(),
                                    uID,
                                    "0",
                                    longi,
                                    lati
                            );
                            mDatabaseRef.child(uID).setValue(restaurant).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(AddResturantActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                    });
                }
            });
        }else {
            Toast.makeText(this, "Choose Image", Toast.LENGTH_SHORT).show();
        }
    }
}