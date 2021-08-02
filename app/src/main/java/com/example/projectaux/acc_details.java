
package com.example.projectaux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class acc_details extends AppCompatActivity {
Button logout;
FirebaseAuth fAuth;
ImageView back, userpop, emailpop, nopop;
TextView changepasswordpop,userh,users,email,phoneno,post,help;
    DatabaseReference ref;
    String auser,aemail,aphoneno,apost,ahelp;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_details);
        logout = findViewById(R.id.logout);
        fAuth = FirebaseAuth.getInstance();
        back = findViewById(R.id.back);
        userpop = findViewById(R.id.userpop);
        emailpop = findViewById(R.id.emailpop);
        changepasswordpop = findViewById(R.id.changepasswordpop);
        nopop = findViewById(R.id.nopop);
        userh = findViewById(R.id.userh);
        users = findViewById(R.id.users);
        email = findViewById(R.id.email);
        phoneno = findViewById(R.id.phoneno);
        post = findViewById(R.id.post);
        help = findViewById(R.id.help);
        String userid =  FirebaseAuth.getInstance().getCurrentUser().getUid();
        readData(userid);







        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Thread.sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                userh.setText(auser);
                                users.setText(auser);
                                email.setText(aemail);
                                phoneno.setText(aphoneno);
                                post.setText(apost);
                                help.setText(ahelp);

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(acc_details.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplication(),Loading_into_app.class));

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),Homepg.class));
            }
        });
        userpop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),userpop.class));
            }
        });
        emailpop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),emailpop.class));
            }
        });
        changepasswordpop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),changepasswordpop.class));
            }
        });
        nopop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),nopop.class));
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
                        auser = String.valueOf(dataSnapshot.child("user").getValue());
                        aemail = String.valueOf(dataSnapshot.child("email").getValue());
                        aphoneno = String.valueOf(dataSnapshot.child("phoneno").getValue());
                        apost = String.valueOf(dataSnapshot.child("post").getValue());
                        ahelp = String.valueOf(dataSnapshot.child("help").getValue());




                    }
                }
            }
        });

    }
}