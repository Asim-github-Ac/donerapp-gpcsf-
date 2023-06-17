package com.example.donatenowapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donatenowapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edtemail;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().hide();
        edtemail = findViewById(R.id.edtemail);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void closed(View view) {
        finish();
    }
    public void submit(View view) {
        if(edtemail.getText().toString().isEmpty()){
            edtemail.setError("Please enter email");
        }else {
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            firebaseAuth.sendPasswordResetEmail(edtemail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        Toast.makeText(ChangePasswordActivity.this,"Password reset email sent",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(ChangePasswordActivity.this,"Error in sending ",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}