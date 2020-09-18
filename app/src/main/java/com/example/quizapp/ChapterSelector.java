package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChapterSelector extends AppCompatActivity {
     Button Chapter_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_selector);
        Chapter_1=findViewById(R.id.chapter1);

        Chapter_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chapter_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(ChapterSelector.this,QuizOrStudyMaterialSelector.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
