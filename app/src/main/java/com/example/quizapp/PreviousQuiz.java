package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    DatabaseReference QuizTable;
    int count = 0;

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
        QuizTable = FirebaseDatabase.getInstance().getReference("Quiz");
        QuizTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    count = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

                Question que = new Question(questionText,0,rightAnswer,optionOne,optionTwo,optionThree,optionFour);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Questions").document(""+que.getQuestionId()).set(que);
                //addQuestion.add(new Question("Abc",10,"23","23","26","25","28"));
              /*  Map<String, String> map = new HashMap<>();*/
               /* map.put("questionText",questionText);
                map.put("answer", rightAnswer);
                map.put("option1",optionOne);
                map.put("option2",optionTwo);
                map.put("option3",optionThree);
                map.put("option4",optionFour);
                map.put("userAnswer",null);
                collectionReference.document("4").set(map);*/

               QuizTable.child(String.valueOf(count+1)).child("Question").setValue(questionText);
               QuizTable.child(String.valueOf(count+1)).child("Option 1").setValue(optionOne);
               QuizTable.child(String.valueOf(count+1)).child("Option 2").setValue(optionTwo);
               QuizTable.child(String.valueOf(count+1)).child("Option 3").setValue(optionThree);
               QuizTable.child(String.valueOf(count+1)).child("Option 4").setValue(optionFour);
               QuizTable.child(String.valueOf(count+1)).child("Right Option").setValue(rightAnswer);

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