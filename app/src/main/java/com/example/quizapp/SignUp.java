package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    TextView account;
    EditText Name, Email, Password, dateOfBirth, address, institute, mobile;
    Button submit;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    CollectionReference reference= FirebaseFirestore.getInstance().collection("Sign Up Info");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        account = findViewById(R.id.createID);
        Name = findViewById(R.id.user_name);
        Email = findViewById(R.id.emailID);
        Password = findViewById(R.id.passwordID);
        dateOfBirth = findViewById(R.id.dateOfBirthID);
        address = findViewById(R.id.addressID);
        institute = findViewById(R.id.institution_name);
        mobile = findViewById(R.id.mobile_number);
        submit = findViewById(R.id.submit);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Password.setError("Password is required.");
                    return;
                }

                if(password.length()<6){
                    Password. setError("Password must be 6 or more characters.");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                            Map<String,String> map=new HashMap<>();
                            map.put("Email",email);
                            reference.document("User: 1").set(map);
                            startActivity(new Intent(getApplicationContext(), SelectClass.class));
                            Intent intentNext = new Intent(SignUp.this,SelectClass.class);
                            startActivity(intentNext);
                        }
                        else{
                            Toast.makeText(SignUp.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
