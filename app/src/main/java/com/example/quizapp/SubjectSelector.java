package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectSelector extends AppCompatActivity {
    Button BanglaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_selector);
        BanglaBtn = (Button)findViewById(R.id.SubBangla);

        BanglaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectSelector.this, ChapterSelector.class);
                startActivity(intent);
            }
        });
    }
}
