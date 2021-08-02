package com.example.projectaux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Loading_into_app extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_into_app);

        Button Login =findViewById(R.id.StartLogin);
        Button Reg = findViewById(R.id.StartRegister);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplication(),Homepg.class));
            finish();
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(Loading_into_app.this, Login.class);
                Loading_into_app.this.startActivity(Login);
            }
        });

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Reg = new Intent(Loading_into_app.this, Register.class);
                Loading_into_app.this.startActivity(Reg);
            }
        });
    }
}