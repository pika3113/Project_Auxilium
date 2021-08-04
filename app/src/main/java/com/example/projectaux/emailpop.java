package com.example.projectaux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class emailpop extends Activity {

    ImageView change;
    EditText email;
    DatabaseReference ref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.emailpop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        change = findViewById(R.id.changeemail);
        email = findViewById(R.id.etemail);

        getWindow().setLayout((int)(width*.8),(int)(height*.25));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail =  email.getText().toString();
                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                ref = FirebaseDatabase.getInstance().getReference("users");
                HashMap hashMap = new HashMap();
                hashMap.put("email", mail);
                if (mail.contains(".com")){
                    ref.child(userid).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Intent intent = new Intent(emailpop.this, acc_details.class);
                            startActivity(intent);
                            return;
                        }
                    });

                } else {
                    email.setError("Invalid email");
                    return;
                }

            }
        });

    }
}
