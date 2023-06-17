package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donatenowapp.Model.Constant;
import com.example.donatenowapp.Model.Order;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProcedToCheckoutActivity extends AppCompatActivity {
    EditText edtcontact,edtaddress,edtName;
    FirebaseAuth mAuth;
    String current_user_id;
    private DatabaseReference mDatabaseRef,mdatabase,udatabase,databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proced_to_checkout);
        edtcontact = findViewById(R.id.edtcontact);
        edtaddress  = findViewById(R.id.edtaddress);
        edtName = findViewById(R.id.edtName);
        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("User").child(current_user_id);
        udatabase = FirebaseDatabase.getInstance().getReference().child("CustomerOrder");
    }
    public void placeorder(View view) {
        if(edtName.getText().toString().isEmpty()){
            edtName.setError("Please enter your name");
        }else {
            if(edtcontact.getText().toString().isEmpty()){
                edtcontact.setError("Please enter your phone number");
            }else {
                if(edtaddress.getText().toString().isEmpty()){
                    edtaddress.setError("Please enter your address");
                }else {
                    String autoid = udatabase.push().getKey();
                    Order customerOrder = new Order(edtName.getText().toString(),edtcontact.getText().toString(),edtaddress.getText().toString(),CartActivity.uCardID,current_user_id,autoid, Constant.rID);
                    udatabase.child(autoid).setValue(customerOrder);
                    databaseReference2.child("uCardID").setValue(autoid);
                    Toast.makeText(ProcedToCheckoutActivity.this, "Order Place successful", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        }
    }
}