package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

public class Quiz_options extends AppCompatActivity {
    ProgressDialog progressDialog;
    RadioButton optionA, optionB, optionC, optionD;
    CircularView circularViewWithTimer;
    TextView textView;
    Button submit, skip;
    public  static Boolean call = false;
    public static ArrayList<Question> questions = new ArrayList<>();
    public static String[] option1;
    public static String[] option2;
    public static String[] option3;
    public static String[] option4;
    public static String[] rightOption;
    public static String[] question;
    public static String[] answers = new String[10];
    public static int count = 100;

    DatabaseReference QuizTable;


    FirebaseFirestore firebaseFirestore;
    CollectionReference reference = FirebaseFirestore.getInstance().collection("Questions");
    private RecyclerView queRecycler;
    private QueAdapter queAdapter;

    /*FirebaseDatabase database=FirebaseDatabase.getInstance();*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_options);


       Intent intent=getIntent();
       question=intent.getStringArrayExtra("question");
       rightOption=intent.getStringArrayExtra("right");
       option1=intent.getStringArrayExtra("option1");
       option2=intent.getStringArrayExtra("option2");
       option3=intent.getStringArrayExtra("option3");
       option4=intent.getStringArrayExtra("option4");
        Toast.makeText(this, question[0]+" ", Toast.LENGTH_SHORT).show();

/*        QuizTable = FirebaseDatabase.getInstance().getReference("Quiz");
        QuizTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    count = (int) snapshot.getChildrenCount();
                    question = new String[count];
                    option1 = new String[count];
                    option2 = new String[count];
                    option3 = new String[count];
                    option4 = new String[count];
                    rightOption = new String[count];
                    for (int i = 1; i <= count; i++) {
                        question[i - 1] = snapshot.child((String.valueOf(i))).child("Question").getValue().toString();
                        option1[i - 1] = snapshot.child((String.valueOf(i))).child("Question").getValue().toString();
                        option2[i - 1] = snapshot.child((String.valueOf(i))).child("Question").getValue().toString();
                        option3[i - 1] = Objects.requireNonNull(snapshot.child((String.valueOf(i))).child("Question").getValue()).toString();
                        option4[i - 1] = Objects.requireNonNull(snapshot.child((String.valueOf(i))).child("Question").getValue()).toString();
                        rightOption[i - 1] = Objects.requireNonNull(snapshot.child((String.valueOf(i))).child("Question").getValue()).toString();


                        // questions.add(new Question(question[0], 0, rightOption[0], option1[0], option2[0], option3[0], option4[0]));


//                       Toast.makeText(getBaseContext(), question[i - 1], Toast.LENGTH_SHORT).show();
//                       questions.add(new Question(question[i - 1],1, option1[i - 1], option2[i - 1], option3[i - 1], option4[i - 1], rightOption[i - 1]));


                    }

                    if(question[0]!=null)
                    {
                        progressDialog.dismiss();
                        call = true;
                        test();
                    }





                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }




        });*/


        submit = findViewById(R.id.submit_btn);
        optionB = findViewById(R.id.optionB);
        optionA = findViewById(R.id.optionA);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        circularViewWithTimer = findViewById(R.id.circular_view);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // uploadData();

                for (int i = 0; i < queAdapter.getItemCount(); i++) {
                    reference.document((i)+"").update("userAnswer",answers[i]);
                }
            }
        });

        TimerSettings();
           test();
//        addQuestions();
        //questions.add(new Question(question[0].toString(),0,rightOption[0].toString(),option1[0],option2[0],option3[0],option4[0]));
        //questions.add(firebaseFirestore.collection("Q"))
        queAdapter = new QueAdapter(questions);
        queRecycler = findViewById(R.id.recycleView);
        queRecycler.setAdapter(queAdapter);

        queAdapter.setOnOptionClickListener(new QueAdapter.OnOptionClickListener() {
            @Override
            public void onClick(int position, String option) {

                answers[position] = option;
            }
        });

    }

    public void test()
    {
        addQuestions();
        //Toast.makeText(this, question[0], Toast.LENGTH_SHORT).show();
    }



/*
    private void onClick(View view) {
        String userAnswer = "";

        if (optionA.isChecked()) userAnswer = "A";
        else if (optionB.isChecked()) userAnswer = "B";
        else if (optionC.isChecked()) userAnswer = "C";
        else if (optionD.isChecked()) userAnswer = "D";


        }
*/

       // reference.document(questions.get(0).getQuestionId() + "").update("userAnswer", answers);




    public void addQuestions() {




        for (int i = 0; i <5; i++) {
            questions.add(new Question(question[i], i, rightOption[i], option1[i], option2[i], option3[i], option4[i]));
           // reference.document( i+"").set(question);

        }
//       questions.add(new Question(question[0].toString(),0,rightOption[0].toString(),option1[0],option2[0],option3[0],option4[0]));
        /*questions.add(new Question(" (45* 4)+(23* 2)", 1, "229", "226", "231", "245", "250"));
        questions.add(new Question(" 87+32-(12*6)", 2, "49", "12", "47", "49", "100"));
        questions.add(new Question(" 87+32-(12*3)", 3, "40", "121", "47", "79", "10"));*/
//        System.out.println(question[0]+ "1234");
        Toast.makeText(this, question[1], Toast.LENGTH_SHORT).show();


    }

    private void TimerSettings() {
        CircularView.OptionsBuilder builderWithTimer =
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback() {
                            @Override
                            public void onTimerFinish() {
                                Intent intent = new Intent(Quiz_options.this, QuizResult.class);
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