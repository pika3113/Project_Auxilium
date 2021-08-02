package com.example.projectaux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Register extends AppCompatActivity {
    EditText username, email, password, cfmpassword, phoneno;
    Button register;
    FirebaseAuth fAuth;
    ProgressBar pbar;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ImageView back;
    String post,help;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cfmpassword = findViewById(R.id.cfmpassword);
        register = findViewById(R.id.StartRegister);
        pbar = findViewById(R.id.pbar);
        back = findViewById(R.id.back);
        phoneno = findViewById(R.id.phoneno);
        fAuth = FirebaseAuth.getInstance();
        post = "0";
        help = "0";






        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ausername = username.getText().toString();
                String aemail = email.getText().toString().trim();
                String apassword = password.getText().toString().trim();
                String acfmpassword = cfmpassword.getText().toString();
                String ano = phoneno.getText().toString();
                if(TextUtils.isEmpty(ausername)){
                    username.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(aemail)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(ano)){
                    phoneno.setError("Phone number is required");
                    return;
                }
                if(TextUtils.isEmpty(apassword)){
                    password.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(acfmpassword)){
                    cfmpassword.setError("Confirmation of password is required");
                    return;
                }
                if(TextUtils.isEmpty(aemail)){
                    email.setError("Email is required");
                    return;
                }
                if(ano.length()!=8){
                    phoneno.setError("Invalid phone number");
                    return;
                }
                if(apassword.length() < 6){
                    password.setError("Password must be 6 characters or more");
                    return;
                }
                if(apassword.equals(acfmpassword)){
                } else {
                    cfmpassword.setError("Passwords do not match");
                    cfmpassword.setText("");
                    return;
                }





                pbar.setAlpha(1);

                //reg

                fAuth.createUserWithEmailAndPassword(aemail,apassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Register.this, "Account Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplication(),Login.class));
                            email.setText("");
                            password.setText("");
                            phoneno.setText("");
                            username.setText("");
                            cfmpassword.setText("");
                            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference("users");
                            UserHelper helper = new UserHelper(ausername,aemail,ano,userid,post,help);
                            reference.child(userid).setValue(helper);
                            pbar.setAlpha(0);

                        }else{
                            Toast.makeText(Register.this, "Error occured" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            email.setText("");
                            password.setText("");
                            username.setText("");
                            cfmpassword.setText("");
                            phoneno.setText("");
                            pbar.setAlpha(0);
                        }

                    }
                });
                //other stuff



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

