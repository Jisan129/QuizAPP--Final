package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PreviousQuiz extends AppCompatActivity {
    Button quizSubmit;
    Button reset;
    EditText question;
    EditText option1;
    EditText option2;
    EditText option3;
    EditText option4;
    EditText rightOption;
    ArrayList<Question> addQuestion = new ArrayList<>();
    CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Questions");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_quiz);
        quizSubmit = findViewById(R.id.quizSubmit);
        reset = findViewById(R.id.resetBtn);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.op1);
        option2 = findViewById(R.id.op2);
        option3 = findViewById(R.id.op3);
        option4 = findViewById(R.id.op4);
        rightOption = findViewById(R.id.rightOp);


        quizSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Please insert a question first", Toast.LENGTH_SHORT).show();
                } else if (option1.getText().toString().equals("") && option2.getText().toString().equals("") && option3.getText().toString().equals("") && option4.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Please insert at least one option", Toast.LENGTH_SHORT).show();
                }
                String questionText = question.getText().toString();
                String rightAnswer = rightOption.getText().toString();
                String optionOne = option1.getText().toString();
                String optionTwo = option2.getText().toString();
                String optionThree = option3.getText().toString();
                String optionFour = option4.getText().toString();

                //addQuestion.add(new Question("Abc",10,"23","23","26","25","28"));
                Map<String, String> map = new HashMap<>();
                map.put("questionText",questionText);
                map.put("answer", rightAnswer);
                map.put("option1",optionOne);
                map.put("option2",optionTwo);
                map.put("option3",optionThree);
                map.put("option4",optionFour);
                map.put("userAnswer",null);
                collectionReference.document("4").set(map);


                Toast.makeText(getBaseContext(), "Question added", Toast.LENGTH_SHORT).show();
                question.getText().clear();
                option1.getText().clear();
                option2.getText().clear();
                option3.getText().clear();
                option4.getText().clear();

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
                Toast.makeText(getBaseContext(), "Reset Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}