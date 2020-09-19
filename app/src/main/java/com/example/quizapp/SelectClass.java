package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectClass extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemSelectedListener {
    private static final String TAG = "tag";
    Spinner classSpinner;
    TextView select_class;
    Button procced ;
    String ClassName;
    CollectionReference reference= FirebaseFirestore.getInstance().collection("Class");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);
       classSpinner = findViewById(R.id.classSpinner);
       select_class=findViewById(R.id.Select_class);
       procced=findViewById(R.id.procced);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
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
        classSpinner.setOnItemSelectedListener(this);
        procced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateClass();

            }
        });

    }

    private void updateClass() {
        Map<String,String> map=new HashMap<>();
        map.put("Class_key",ClassName);
        reference.document("1").set(map).addOnSuccessListener(s-> Log.i(TAG,"1234"+s));
        Toast.makeText(this, ClassName, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(SelectClass.this, PreviousQuiz.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ClassName=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
