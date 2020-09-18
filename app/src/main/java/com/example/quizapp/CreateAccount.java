package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateAccount extends AppCompatActivity {

    String[] city_names;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        city_names = getResources().getStringArray(R.array.city_names);
        spinner = (Spinner) findViewById(R.id.CitySpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, R.id.CitySpinner, city_names);
        spinner.setAdapter(adapter);
    }
}
