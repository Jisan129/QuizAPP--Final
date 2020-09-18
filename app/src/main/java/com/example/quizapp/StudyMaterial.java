package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudyMaterial extends AppCompatActivity {
    Button study_Material;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_material);
        study_Material= findViewById(R.id.Share_btn);

        study_Material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudyMaterial.this,UploadStudyMaterial.class);
                startActivity(intent);
            }
        });
    }
}
