package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizResult extends AppCompatActivity {
    Button Proceed;
    TextView marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        Proceed =findViewById(R.id.proceedBtn);
        marks=findViewById(R.id.marks);


        Intent intent=getIntent();
        final int c=intent.getIntExtra("result",0);
        marks.setText(c+" ");

        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(QuizResult.this, PreviousQuiz.class);
                Intent intent =new Intent(QuizResult.this,QuizConfirmation.class);
                startActivity(intent);
            }
        });
    }
}
