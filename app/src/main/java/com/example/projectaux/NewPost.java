package com.example.projectaux;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NewPost extends AppCompatActivity {
    ImageView back;
    Button postnow;
    FirebaseDatabase rootnode;
    DatabaseReference ref , ref1, ref2;
    String s1, sc1,title, desc, author;
    EditText title1, desc1;
    String buser, postno, postnu;
    double postno1, postno2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        Spinner sub = findViewById(R.id.sub);
        Spinner sec = findViewById(R.id.sec);
        back = findViewById(R.id.back);
        postnow = findViewById(R.id.postnow);
        title1 = findViewById(R.id.title);
        desc1 = findViewById(R.id.desc);
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        readData(userid);






        postnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = title1.getText().toString();
                desc = desc1.getText().toString();
                if(TextUtils.isEmpty(title)){
                    title1.setError("Title is required");

                    return;
                }
                if(TextUtils.isEmpty(desc)){
                    desc1.setError("Description is required");

                    return;
                }
                readData(userid);
                rootnode = FirebaseDatabase.getInstance();
                ref = rootnode.getReference("posts");
                postshelper postshelper = new postshelper(title,s1,sc1,desc,userid, author);
                ref.child(title).setValue(postshelper);
                startActivity(new Intent(getApplication(),Homepg.class));
                ref1 = FirebaseDatabase.getInstance().getReference("users");

            }
        });


        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.sub,
                R.layout.sec_spinner
        );
        adapter1.setDropDownViewResource(R.layout.sec_spinner_small);
        sub.setAdapter(adapter1);
        sub.setOnItemSelectedListener(onItemSelectedListener0);



        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.sec,
                R.layout.sec_spinner
        );
        adapter2.setDropDownViewResource(R.layout.sec_spinner_small);
        sec.setAdapter(adapter2);
        sec.setOnItemSelectedListener(onItemSelectedListener1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),Loading_into_app.class));
            }
        });
    }

    private void readData(String userid) {
        ref2 = FirebaseDatabase.getInstance().getReference("users");
        ref2.child(userid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){

                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        author = String.valueOf(dataSnapshot.child("user").getValue());



                    }
                }
            }
        });
    }


    //spinners
OnItemSelectedListener onItemSelectedListener0 =
            new OnItemSelectedListener(){
    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s = (String)parent.getItemAtPosition(position);
        s1 = s;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    };

OnItemSelectedListener onItemSelectedListener1 =
            new OnItemSelectedListener(){
    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sc = parent.getItemAtPosition(position).toString();
       sc1 = sc;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    };




}