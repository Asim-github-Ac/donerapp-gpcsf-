package com.example.donatenowapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donatenowapp.Adopter.AdminAdopter;
import com.example.donatenowapp.Adopter.UserAdopter;
import com.example.donatenowapp.Model.Rating;
import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserHomeActivity extends AppCompatActivity {
    ImageView imageView,imageLogout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Dialog mDialog;
    String uID, uName;
    TextView txttitle,txtcart,txtorder,txtproducts,txtitems,txthall,txthallfood,txtuser,txtfeedback,txtlogin;
    FloatingActionButton floating;
    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
    private List<Restaurant> mUser;
    UserAdopter adminHomeAdopter;
    private DatabaseReference bDatabaseRef,databaseReference3,databaseReference2;
    private ProgressBar mProgressCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        getSupportActionBar().hide();
        txthall = findViewById(R.id.txthall);
        txtcart = findViewById(R.id.txtcart);
        txtitems = findViewById(R.id.txtitems);
        txtorder = findViewById(R.id.txtorder);
        firebaseAuth = FirebaseAuth.getInstance();
        imageLogout = findViewById(R.id.imageLogout);
        txtproducts = findViewById(R.id.txtproducts);
        floating = findViewById(R.id.floating);
        txtfeedback = findViewById(R.id.txtfeedback);
        imageView = findViewById(R.id.imageView);
        txthallfood = findViewById(R.id.txthallfood);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        txttitle = findViewById(R.id.txttitle);
        txttitle.setText("Home");
        txtuser = findViewById(R.id.txtuser);
        txtlogin = findViewById(R.id.txtlogin);
        recyclerView = findViewById(R.id.recyclerView);
        mProgressCircle = findViewById(R.id.progress_circle);

        txtuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null) {
                    startActivity(new Intent(UserHomeActivity.this,UserProfileActivity.class));
                }else {
                    startActivity(new Intent(UserHomeActivity.this,LoginActivity.class));
                }
            }
        });
        mUser = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserHomeActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adminHomeAdopter = new UserAdopter(UserHomeActivity.this,mUser);
        recyclerView.setAdapter(adminHomeAdopter);
        bDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        bDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Restaurant upload = postSnapshot.getValue(Restaurant.class);
                    if(upload.getrStatus().equals("1")) {
                        mUser.add(upload);
                        mProgressCircle.setVisibility(View.INVISIBLE);
                    }
                }
                adminHomeAdopter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserHomeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        txtitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null) {
                    startActivity(new Intent(UserHomeActivity.this,AddItemActivity.class));
                }else {
                    startActivity(new Intent(UserHomeActivity.this,LoginActivity.class));
                }

            }
        });
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomeActivity.this,LoginActivity.class));
            }
        });
        txthall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null) {
                    startActivity(new Intent(UserHomeActivity.this,HallActivity.class));
                }else {
                    startActivity(new Intent(UserHomeActivity.this,LoginActivity.class));
                }

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(navigationView,true);
            }
        });
        txthallfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomeActivity.this,UserHallActivity.class));
            }
        });
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null) {
                    startActivity(new Intent(UserHomeActivity.this, ResturantActivity.class));
                }else {
                    startActivity(new Intent(UserHomeActivity.this,LoginActivity.class));
                }
            }
        });
        txtcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null) {
                    startActivity(new Intent(UserHomeActivity.this,CartActivity.class));
                }else {
                    startActivity(new Intent(UserHomeActivity.this,LoginActivity.class));
                }

            }
        });
        txtorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null) {
                    startActivity(new Intent(UserHomeActivity.this,ShowOrderActivity.class));
                }else {
                    startActivity(new Intent(UserHomeActivity.this,LoginActivity.class));
                }

            }
        });
        txtproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null) {
                    startActivity(new Intent(UserHomeActivity.this,ItemActivity.class));
                }else {
                    startActivity(new Intent(UserHomeActivity.this,LoginActivity.class));
                }

            }
        });
        imageLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(UserHomeActivity.this);
                builder1.setMessage("Write your message here.");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                firebaseAuth.signOut();
                                startActivity(new Intent(UserHomeActivity.this,LoginActivity.class));
                                finish();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            uID = firebaseUser.getUid();
            databaseReference2 = FirebaseDatabase.getInstance().getReference().child("User").child(uID);
            databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    uName = dataSnapshot.child("uName").getValue(String.class);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.feedback_dialogbox);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        txtfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show();
                TextView txtsubmit = mDialog.findViewById(R.id.txtsubmit);
                EditText edtmsg = mDialog.findViewById(R.id.edtmsg);
                TextView txtcancel = mDialog.findViewById(R.id.txtcancel);
                txtcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                txtsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser firebaseUser1 = firebaseAuth.getCurrentUser();
                        if(firebaseUser1!=null){
                            if(edtmsg.getText().toString().isEmpty()){
                                edtmsg.setError("Please enter message");
                            }else {
                                String date = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(new Date());
                                String pID = "null";
                                databaseReference3 = FirebaseDatabase.getInstance().getReference().child("Rating");
                                String uKey = databaseReference3.push().getKey();
                                Rating rating = new Rating(edtmsg.getText().toString(),"",date,uID,uName,pID,uKey);
                                databaseReference3.child(uKey).setValue(rating).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(UserHomeActivity.this, "Feedback submit", Toast.LENGTH_SHORT).show();
                                        mDialog.dismiss();

                                    }
                                });
                            }
                        }else {
                            startActivity(new Intent(UserHomeActivity.this,LoginActivity.class));
                        }


                    }
                });
            }
        });
    }

}