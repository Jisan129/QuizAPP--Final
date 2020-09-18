package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CompletionOfQuiz extends AppCompatActivity {
    Button eval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion_of_quiz);
        //eval = findViewById(R.id.evalue);
        eval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eval = new Intent(CompletionOfQuiz.this,QuizResult.class);
                startActivity(eval);
            }
        });
    }
}
