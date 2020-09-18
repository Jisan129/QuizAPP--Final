package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuizOrStudyMaterialSelector extends AppCompatActivity {
    Button quiz;
    Button studyMaterial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_or_study_material_selector);
        quiz = findViewById(R.id.GiveQuiz);
        studyMaterial = findViewById(R.id.ToStudyMaterial);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quiz = new Intent(QuizOrStudyMaterialSelector.this,QuizConfirmation.class);
                startActivity(quiz);
            }
        });
        studyMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studyMaterial = new Intent(QuizOrStudyMaterialSelector.this,StudyMaterial.class);
                startActivity(studyMaterial);
            }
        });

    }
}
