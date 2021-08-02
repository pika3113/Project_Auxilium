package com.example.projectaux;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class fgtpassword extends AppCompatActivity {

    Button send;
    FirebaseAuth fAuth;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fgtpassword);
        send = findViewById(R.id.send);
        email = findViewById(R.id.emailp);
        fAuth = FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail  = email.getText().toString();
                if(TextUtils.isEmpty(mail)){
                    email.setError("Email is required");
                    return;
                }
                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(fgtpassword.this, "Sent.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(fgtpassword.this, Login.class);
                        finish();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(fgtpassword.this, "Failed to send."+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });






    }
}