package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    Button Login_button;
    Button SignUpButton;
    EditText username,password;
    TextView no_Id;
    FirebaseAuth mAuth;
    EditText editTextEmail ,editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        no_Id=findViewById(R.id.noIdView);
        editTextEmail = findViewById(R.id.user_name);
        editTextPassword = findViewById(R.id.pass_for_login);
        Login_button=findViewById(R.id.loginId);
        SignUpButton=findViewById(R.id.signUpId);
        mAuth = FirebaseAuth.getInstance();


        Login_button.setOnClickListener(new View.OnClickListener() {
            private void userLogin(){

                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();


                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(LogInActivity.this,SelectClass.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                        else{
                            Intent intent=new Intent(LogInActivity.this,SelectClass.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            Toast toast = Toast.makeText(getApplicationContext(),"Email not registered",Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
            }
            @Override

            public void onClick(View v) {
                userLogin();
           }
       });

       SignUpButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent2 = new Intent(LogInActivity.this,SignUp.class);
               startActivity(intent2);
           }
       });

    }
}
