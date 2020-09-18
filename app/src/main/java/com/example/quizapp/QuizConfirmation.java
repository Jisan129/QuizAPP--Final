package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class QuizConfirmation extends AppCompatActivity {
    Button quizgo;
    ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_confirmation);
        quizgo=findViewById(R.id.To_quiz);
        //backButton=findViewById(R.id.backarrowQuizcompletation);
        quizgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(QuizConfirmation.this,Quiz_options.class);
                startActivity(intent);

               // startDialogFragment();
            }
        });

   /*     backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    private void startDialogFragment() {
        DilogBox dilogBox=new DilogBox();
        dilogBox.show(getSupportFragmentManager(),"QuizConfirmation");
    }
}
