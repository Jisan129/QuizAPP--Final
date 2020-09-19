package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

public class Quiz_options extends AppCompatActivity {
    RadioButton optionA, optionB, optionC, optionD;
    CircularView circularViewWithTimer;
    TextView textView;
    Button submit, skip;
    ArrayList<Question> questions = new ArrayList<>();
    String[] answers = new String[10];
    FirebaseFirestore firebaseFirestore;
    CollectionReference reference = FirebaseFirestore.getInstance().collection("Questions");
    private RecyclerView queRecycler;
    private QueAdapter queAdapter;

    /*FirebaseDatabase database=FirebaseDatabase.getInstance();*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_options);

        submit = findViewById(R.id.submit_btn);
        circularViewWithTimer = findViewById(R.id.circular_view);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
                finish();
            }
        });

        TimerSettings();
        addQuestions();

        queAdapter = new QueAdapter(questions);
        queRecycler = findViewById(R.id.recycleView);
        queRecycler.setAdapter(queAdapter);

        queAdapter.setOnOptionClickListener(new QueAdapter.OnOptionClickListener() {
            @Override
            public void onClick(int position, String option) {
                answers[position] = option;
            }
        });

    /*    questions.add(new Question(1,"A","B","c","D","A"));
        reference.document(set).set()*/


  /*      optionA.setOnClickListener(this::onClick);
        optionC.setOnClickListener(this::onClick);
        optionD.setOnClickListener(this::onClick);
        optionB.setOnClickListener(this::onClick);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quiz_options.this, CompletionOfQuiz.class);
                startActivity(intent);

            }
        });*/







       /* r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference=database.getReference("Question no: 1");
                databaseReference.setValue("A");
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference=database.getReference("Question no: 1");
                databaseReference.setValue("B");
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference=database.getReference("Question no: 1");
                databaseReference.setValue("C");
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference=database.getReference("Question no: 1");
                databaseReference.setValue("D");
            }
        });*/
    }

    private void uploadData() {
        boolean flag=false;
        int count=0;
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).setUserAnswer(answers[i]);
            reference.document(questions.get(i).getQuestionId()+"").set(questions.get(i));
            flag=questions.get(i).getAnswer().equals(questions.get(i).getUserAnswer());
            if(flag)count++;
        }
        Toast.makeText(this, "Your number of Correct answer is : "+count, Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(this,QuizResult.class);
        intent.putExtra("result",count);
        startActivity(intent);


        // reference.document(questions.get(i).getQuestionId()+"").update("userAnswer",answers[i]);

    }

    private void onClick(View view) {
        String userAnswer = "";

        if (optionA.isChecked()) userAnswer = "A";
        else if (optionB.isChecked()) userAnswer = "B";
        else if (optionC.isChecked()) userAnswer = "C";
        else if (optionD.isChecked()) userAnswer = "D";


        reference.document(questions.get(0).getQuestionId() + "").update("userAnswer", userAnswer);


    }

    private void addQuestions() {
        questions.add(new Question("12+(13*5)-54", 0, "23", "23", "18", "17", "26"));
        questions.add(new Question("(45*4)+(23*2)", 1, "226",  "229", "221", "226", "236"));
        questions.add(new Question("87+32-(12*6)", 2, "47", "49", "57", "43", "47"));

        for (Question question : questions) {
            reference.document(question.getQuestionId() + "").set(question);
        }

    }

    private void TimerSettings() {
        CircularView.OptionsBuilder builderWithTimer =
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(10)
                        .setCircularViewCallback(new CircularViewCallback() {
                            @Override
                            public void onTimerFinish() {
                                Intent intent=new Intent(Quiz_options.this,QuizResult.class);
                               // intent.putExtra("result",count);
                                startActivity(intent);
                                // Will be called if times up of countdown timer
                                //Toast.makeText(MainActivity.this, "CircularCallback: Timer Finished ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onTimerCancelled() {

                                // Will be called if stopTimer is called
                                // Toast.makeText(MainActivity.this, "CircularCallback: Timer Cancelled ", Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimer.startTimer();

        circularViewWithTimer.setOptions(builderWithTimer);

    }
}
