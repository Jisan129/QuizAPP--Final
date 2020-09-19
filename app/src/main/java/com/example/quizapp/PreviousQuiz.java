package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PreviousQuiz extends AppCompatActivity {
    Button quizSubmit;
    Button reset;
    EditText question;
    EditText option1;
    EditText option2;
    EditText option3;
    EditText option4;
    EditText rightOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_quiz);
        quizSubmit = (Button) findViewById(R.id.quizSubmit);
        reset = (Button) findViewById(R.id.resetBtn);
        question = (EditText) findViewById(R.id.question);
        option1 = (EditText) findViewById(R.id.op1);
        option2 = (EditText) findViewById(R.id.op2);
        option3 = (EditText) findViewById(R.id.op3);
        option4 = (EditText) findViewById(R.id.op4);
        rightOption = (EditText) findViewById(R.id.rightOp);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_quiz);

        quizSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(question.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Please insert a question first" , Toast.LENGTH_SHORT ).show();
                }

                else if(option1.getText().toString().equals("") && option2.getText().toString().equals("") && option3.getText().toString().equals("") && option4.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Please insert at least one option", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Question added", Toast.LENGTH_SHORT).show();
                    question.getText().clear();
                    option1.getText().clear();
                    option2.getText().clear();
                    option3.getText().clear();
                    option4.getText().clear();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.getText().clear();
                option1.getText().clear();
                option2.getText().clear();
                option3.getText().clear();
                option4.getText().clear();
                Toast.makeText(getBaseContext(), "Reset Successfully" , Toast.LENGTH_SHORT ).show();
            }
        });

    }
}
