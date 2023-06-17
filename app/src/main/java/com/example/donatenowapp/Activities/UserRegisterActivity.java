package com.example.donatenowapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donatenowapp.Model.User;
import com.example.donatenowapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegisterActivity extends AppCompatActivity {
    EditText edtName,edtEmail,edtphone,edtaddress,edtpassword;
    // DataBase
    FirebaseAuth firebaseAuthe;
    DatabaseReference mdatabase;
    ProgressDialog logProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        getSupportActionBar().hide();
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtphone = findViewById(R.id.edtphone);
        edtaddress = findViewById(R.id.edtaddress);
        edtpassword = findViewById(R.id.edtpassword);
        firebaseAuthe= FirebaseAuth.getInstance();
        logProgressDialog =new ProgressDialog(this);

    }

    public void registered(View view) {
        if(edtName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
        }else {
            if(edtEmail.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            }else {
                if(edtphone.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                }else {
                    if(edtaddress.getText().toString().isEmpty()){
                        Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT).show();
                    }else {
                        if(edtpassword.getText().toString().isEmpty()){
                            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
                        }else {
                            Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
                            Matcher ms = ps.matcher(edtName.getText().toString());
                            boolean bs = ms.matches();
                            if (bs == false) {
                                Toast.makeText(this, "Please enter only Alphabets", Toast.LENGTH_SHORT).show();
                            }else {
                                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                                if(!edtEmail.getText().toString().matches(emailPattern))
                                {
                                    Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                                }else {
                                    if (edtphone.getText().toString().length() >=11){
                                        if(edtpassword.getText().toString().length() >= 6){
                                            mdatabase = FirebaseDatabase.getInstance().getReference().child("User");
                                            register();
                                        }else {
                                            Toast.makeText(this, "Password length greater then 5", Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void register(){
        logProgressDialog.setTitle("User Register");
        logProgressDialog.setMessage("Please wait....");
        logProgressDialog.setCanceledOnTouchOutside(false);
        logProgressDialog.show();
        firebaseAuthe.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = firebaseAuthe.getCurrentUser();
                    String autoID = firebaseUser.getUid();
                    User user = new User(
                            edtName.getText().toString(),
                            edtEmail.getText().toString(),
                            edtphone.getText().toString(),
                            edtaddress.getText().toString(),
                            edtpassword.getText().toString(),
                            "null",
                            autoID,
                            "User",
                            "null");
                    mdatabase.child(autoID).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(UserRegisterActivity.this, "User Register Successfully", Toast.LENGTH_SHORT).show();
                            logProgressDialog.dismiss();
                            finish();
                        }
                    });
                }else {
                    logProgressDialog.dismiss();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                logProgressDialog.dismiss();
                Toast.makeText(UserRegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}