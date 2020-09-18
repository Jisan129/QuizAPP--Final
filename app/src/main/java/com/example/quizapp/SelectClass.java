package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelectClass extends AppCompatActivity {
    Spinner classSpinner;
    TextView select_class;
    Button procced ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);
       classSpinner = findViewById(R.id.classSpinner);
       select_class=findViewById(R.id.Select_class);
       procced=findViewById(R.id.procced);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Class 5");
        arrayList.add("Class 6");
        arrayList.add("Class 7");
        arrayList.add("Class 8");
        arrayList.add("Class 9");
        arrayList.add("Class 10");
        arrayList.add("Class 11");
        arrayList.add("Class 12");
        arrayList.add("Varsity Admission Candidate");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(arrayAdapter);
        procced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectClass.this, SubjectSelector.class);
                startActivity(intent);
            }
        });

    }

}
