package com.example.donatenowapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donatenowapp.Model.User;
import com.example.donatenowapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText edtemail,edtpassword;
    FirebaseAuth firebaseAuthe;
    ProgressDialog logProgressDialog;
    DatabaseReference mDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);
        logProgressDialog =new ProgressDialog(this);
        firebaseAuthe= FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    }
    public void login(View view) {
        if(edtemail.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
        }else {
            if(edtpassword.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            }else {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(!edtemail.getText().toString().matches(emailPattern))
                {
                    Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }else {
                    if(edtpassword.getText().toString().length() >= 6){
                        logProgressDialog.setMessage("Please wait....");
                        logProgressDialog.setCanceledOnTouchOutside(false);
                        logProgressDialog.show();
                        Query query = mDatabaseRef.child("User").orderByChild("uEmail").equalTo(edtemail.getText().toString().trim());
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // dataSnapshot is the "issue" node with all children with id 0
                                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                                        // do something with the individual "issues"
                                        User usersBean = user.getValue(User.class);
                                        if (usersBean.getuType().equals("User")) {
                                            firebaseAuthe.signInWithEmailAndPassword(edtemail.getText().toString(), edtpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(LoginActivity.this, "User Login", Toast.LENGTH_SHORT).show();
                                                        logProgressDialog.dismiss();
                                                        startActivity(new Intent(LoginActivity.this, UserHomeActivity.class));
                                                        finishAffinity();
                                                    }else{
                                                        Toast.makeText(LoginActivity.this, "Wong Password", Toast.LENGTH_SHORT).show();
                                                        logProgressDialog.dismiss();
                                                    }
                                                }
                                            });
                                        }else if (usersBean.getuType().equals("Admin")) {
                                            firebaseAuthe.signInWithEmailAndPassword(edtemail.getText().toString(), edtpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(LoginActivity.this, "Admin Login", Toast.LENGTH_SHORT).show();
                                                        logProgressDialog.dismiss();
                                                        startActivity(new Intent(LoginActivity.this, AdminHomeActivity.class));
                                                        finishAffinity();
                                                    }else{
                                                        Toast.makeText(LoginActivity.this, "Wong Password", Toast.LENGTH_SHORT).show();
                                                        logProgressDialog.dismiss();
                                                    }
                                                }
                                            });
                                        }else{
                                            Toast.makeText(LoginActivity.this, "User not exits", Toast.LENGTH_LONG).show();
                                            logProgressDialog.dismiss();
                                        }

                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "User not exits", Toast.LENGTH_LONG).show();
                                    logProgressDialog.dismiss();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                logProgressDialog.dismiss();
                            }
                        });
                    }else {
                        Toast.makeText(this, "Password length greater then 5", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    public void forget(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }
    public void register(View view) {
        startActivity(new Intent(this, UserRegisterActivity.class));
    }
}