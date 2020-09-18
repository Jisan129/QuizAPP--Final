package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SubjectSelector extends AppCompatActivity {
    private static final String TAG ="tag";
    Button BanglaBtn;
    CollectionReference collectionReference= FirebaseFirestore.getInstance().collection("Subject");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_selector);
        BanglaBtn = (Button)findViewById(R.id.SubBangla);

        BanglaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map=new HashMap<>();
                map.put("Subject Name: ","Bangla");
                Toast.makeText(SubjectSelector.this, "Bangla", Toast.LENGTH_SHORT).show();
                Log.i(TAG,"Bangla");
                collectionReference.document("1").set(map);
                Intent intent = new Intent(SubjectSelector.this, ChapterSelector.class);
                startActivity(intent);
            }
        });
    }
}
