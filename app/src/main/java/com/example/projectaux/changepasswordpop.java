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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepasswordpop extends Activity {

    ImageView change;
    EditText changetext;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.changepasswordpop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        change = findViewById(R.id.changepass);
        changetext = findViewById(R.id.changepasset);
        fAuth = FirebaseAuth.getInstance();

        getWindow().setLayout((int) (width*.8), (int) (height*.25));


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = changetext.getText().toString();
                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(changepasswordpop.this, "Email required.", Toast.LENGTH_SHORT).show();
                    return;
                }
                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(changepasswordpop.this, "Sent", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(changepasswordpop.this, acc_details.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(changepasswordpop.this, "Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });













            }

        });

    }
}
