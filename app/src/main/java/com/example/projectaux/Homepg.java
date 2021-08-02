package com.example.projectaux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.ScriptGroup;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputBinding;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectaux.databinding.ActivityHomepgBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class Homepg extends AppCompatActivity {
    TextView time;
    Thread thread;
    ImageView newpost, listposts;
    TextView user;
    FirebaseAuth fAuth;
    DatabaseReference ref;
    String buser,level, sub;


    RecyclerView recyclerView;
    PostsAdapter postsAdapter;
    ArrayList<posts> list;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepg);
        newpost = findViewById(R.id.newpost);
        listposts = findViewById(R.id.Listposts);
        TextView Modacc = findViewById(R.id.modacc);
        time = findViewById(R.id.datetime);
        user = findViewById(R.id.user);
        String userid =  FirebaseAuth.getInstance().getCurrentUser().getUid();
        readData(userid);
        recyclerView = findViewById(R.id.postslist);
        ref = FirebaseDatabase.getInstance().getReference("posts");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        postsAdapter = new PostsAdapter(this,list);
        recyclerView.setAdapter(postsAdapter);





        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();   
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    posts posts = dataSnapshot.getValue(posts.class);
                    list.add(posts);


                    Collections.sort(list, new Comparator<posts>() {
                        @Override
                        public int compare(posts o1, posts o2) {

                            return o1.title.compareToIgnoreCase(o2.title);
                        }
                    });

                }
                postsAdapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        Modacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modacc = new Intent(Homepg.this, acc_details.class);
                Homepg.this.startActivity(modacc);
            }
        });

        newpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newpost = new Intent(Homepg.this, NewPost.class);
                Homepg.this.startActivity(newpost);
            }
        });

        listposts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), ListofPosts.class));
            }
        });






        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Thread.sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Date curenttime = Calendar.getInstance().getTime();
                                TimeZone.setDefault( TimeZone.getTimeZone("Singapore"));
                                curenttime.setHours(curenttime.getHours());
                                String formatteddate = DateFormat.getDateInstance(DateFormat.SHORT).format(curenttime);
                                String formattedtime = DateFormat.getTimeInstance(DateFormat.SHORT).format(curenttime);
                                time.setText(formatteddate+"    "+formattedtime);

                                user.setText("Welcome, "+ buser);

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();



    }
    private void readData(String userid) {
        ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(userid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){

                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        buser = String.valueOf(dataSnapshot.child("user").getValue());
                        if (buser.equals("")){
                            buser="";
                        }



                    }
                }
            }
        });

    }


}