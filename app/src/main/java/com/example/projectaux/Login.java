package com.example.projectaux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectaux.databinding.ActivityHomepgBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Scanner;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button login;
    ProgressBar pbar;
    FirebaseAuth fAuth;
    ImageView back;
    String usera;
    TextView fgtpassword, noacc;

    ActivityHomepgBinding binding;
    SharedPreferences sharedPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        pbar = findViewById(R.id.pbar);
        login = findViewById(R.id.login);
        back = findViewById(R.id.back);
        fAuth = FirebaseAuth.getInstance();
        fgtpassword = findViewById(R.id.fgtpasswd);
        noacc = findViewById(R.id.noacc);

        fgtpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, fgtpassword.class);
                startActivity(intent);
            }
        });
        noacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aemail = email.getText().toString().trim();
                String apassword = password.getText().toString().trim();

                if(TextUtils.isEmpty(aemail)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(apassword)){
                    password.setError("Password is required");
                    return;
                }
                pbar.setAlpha(1);

                //auth


                fAuth.signInWithEmailAndPassword(aemail,apassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Login.this, "Signed in",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplication(),Homepg.class));
                            email.setText("");
                            password.setText("");
                            pbar.setAlpha(0);
                            binding = ActivityHomepgBinding.inflate(getLayoutInflater());
                            setContentView(binding.getRoot());





                        }else{
                            Toast.makeText(Login.this, "Error, " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            email.setText("");
                            password.setText("");
                            pbar.setAlpha(0);
                        }
                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),Loading_into_app.class));
            }
        });


    }




}