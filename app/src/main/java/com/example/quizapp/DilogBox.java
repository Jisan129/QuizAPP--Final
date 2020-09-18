package com.example.quizapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DilogBox extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog=null;
       if(getTag().equals("QuizConfirmation")) dialog=getSavedDialog();
        return dialog;
    }

    private Dialog getSavedDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Want to return home?").
     setPositiveButton("Yes", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             Intent intent=new Intent(getActivity(),LogInActivity.class);
             startActivity(intent);
         }
     }).setNegativeButton("No",null);

        return builder.create();
    }
}
