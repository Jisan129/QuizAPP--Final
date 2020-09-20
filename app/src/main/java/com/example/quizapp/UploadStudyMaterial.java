package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Objects;

public class UploadStudyMaterial extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private static final String TAG = "Tag";
    Button uploadbtn,uploadFinal;
    TextView uploadText;
    public  static Boolean call = false;
    public static ArrayList<Question> questions = new ArrayList<>();
    public static String[] option1;
    public static String[] option2;
    public static String[] option3;
    public static String[] option4;
    public static String[] rightOption;
    public static String[] question;
    public static String[] answers;
    public static int count = 100;
    ProgressDialog progressDialog;
    DatabaseReference QuizTable;
    //private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_study_material);






        uploadbtn=findViewById(R.id.upload);
        uploadText=findViewById(R.id.UploadMaterial);
        uploadFinal=findViewById(R.id.goBackbtn);

        progressDialog=new ProgressDialog(UploadStudyMaterial.this);
        progressDialog.setMessage("Getting Data");
        progressDialog.show();
        /*progressDialog = new ProgressDialog(UploadStudyMaterial.this);
        progressDialog.setMessage("Getting data");
        progressDialog.show();*/

        QuizTable = FirebaseDatabase.getInstance().getReference("Quiz");
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

                        //Toast.makeText(UploadStudyMaterial.this, question[i], Toast.LENGTH_SHORT).show();
                        // questions.add(new Question(question[0], 0, rightOption[0], option1[0], option2[0], option3[0], option4[0]));


//                       Toast.makeText(getBaseContext(), question[i - 1], Toast.LENGTH_SHORT).show();
//                       questions.add(new Question(question[i - 1],1, option1[i - 1], option2[i - 1], option3[i - 1], option4[i - 1], rightOption[i - 1]));


                    }

                    /*if(question[0]!=null)
                    {
                        progressDialog.dismiss();
                        call = true;
                        test();
                    }*/





                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }




        });


    }}
