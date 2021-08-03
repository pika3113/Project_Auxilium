package com.example.projectaux;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class changepasswordpop extends Activity {

    ImageView change;
    FirebaseAuth fAuth;
    String mail;
    DatabaseReference ref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.changepasswordpop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        change = findViewById(R.id.changepass);
        fAuth = FirebaseAuth.getInstance();
        getWindow().setLayout((int) (width*.8), (int) (height*.25));
        String userid =  FirebaseAuth.getInstance().getCurrentUser().getUid();
        readData(userid);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(changepasswordpop.this, acc_details.class);
                startActivity(intent);
            }

        });

    }

    private void readData(String userid) {
        ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(userid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){

                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        mail = String.valueOf(dataSnapshot.child("email").getValue());
                        fAuth.sendPasswordResetEmail(mail);
                    }
                }
            }
        });

    }
    }

