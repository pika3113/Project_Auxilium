package com.example.projectaux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class ListofPosts extends AppCompatActivity {
    ImageView back;
    String s1,sc1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_posts);
        back = findViewById(R.id.back);
        Spinner subfilter = findViewById(R.id.subfilter);
        Spinner secfilter = findViewById(R.id.levelfilter);




        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.sub,
                R.layout.sec_spinner
        );
        adapter1.setDropDownViewResource(R.layout.sec_spinner_small);
        subfilter.setAdapter(adapter1);
        subfilter.setOnItemSelectedListener(onItemSelectedListener0);



        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.sec,
                R.layout.sec_spinner
        );
        adapter2.setDropDownViewResource(R.layout.sec_spinner_small);
        secfilter.setAdapter(adapter2);
        secfilter.setOnItemSelectedListener(onItemSelectedListener1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),Homepg.class));


            }
        });
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener0 =
            new AdapterView.OnItemSelectedListener(){
                @Override

                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String s = (String)parent.getItemAtPosition(position);
                    s1 = s;

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };

    AdapterView.OnItemSelectedListener onItemSelectedListener1 =
            new AdapterView.OnItemSelectedListener(){
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