package com.example.quizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QueAdapter extends RecyclerView.Adapter<QueAdapter.QuestionViewHolder> {
    private OnOptionClickListener onOptionClickListener;

    public interface OnOptionClickListener {
        void onClick(int position, String option);
    }

    public void setOnOptionClickListener(OnOptionClickListener onOptionClickListener) {
        this.onOptionClickListener = onOptionClickListener;
    }

    ArrayList<Question> questions;

    public QueAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {

        TextView questionText;
        RadioButton optionA, optionB, optionC, optionD;

        public QuestionViewHolder(@NonNull View itemView, OnOptionClickListener onOptionClickListener) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_textVIew);
            optionA = itemView.findViewById(R.id.optionA);
            optionB = itemView.findViewById(R.id.optionB);
            optionC = itemView.findViewById(R.id.optionC);
            optionD = itemView.findViewById(R.id.optionD);

            optionA.setOnClickListener(view -> onClick(getAdapterPosition(), view,onOptionClickListener));
            optionB.setOnClickListener(view -> onClick(getAdapterPosition(), view,onOptionClickListener));
            optionC.setOnClickListener(view -> onClick(getAdapterPosition(), view,onOptionClickListener));
            optionD.setOnClickListener(view -> onClick(getAdapterPosition(), view,onOptionClickListener));

        }

        private void onClick(int position, View view,OnOptionClickListener onOptionClickListener) {
            String ans="";
            switch (view.getId()) {
                case R.id.optionA:
                    ans = "A";
                    break;
                case R.id.optionB:
                    ans = "B";
                    break;
                case R.id.optionC:
                    ans = "C";
                    break;
                case R.id.optionD:
                    ans = "D";
                    break;
            }
            onOptionClickListener.onClick(position,ans);     //need some interface realizations
        }

    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.que_item, null);

        return new QuestionViewHolder(itemView, onOptionClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

        // holder.questionText.setText(questions.get(position).getQuestionText());
        holder.questionText.setText(questions.get(position).getQuestionText());

        holder.optionA.setText(questions.get(position).getOption1());
        holder.optionB.setText(questions.get(position).getOption2());
        holder.optionC.setText(questions.get(position).getOption3());
        holder.optionD.setText(questions.get(position).getOption4());


    }

    @Override
    public int getItemCount() {
        return questions.size();
    }




/*extends RecyclerView.Adapter<QueAdapter.QuestionViewHolder>     ArrayList<Question> questions;

    public QueAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView que;
        RadioButton opA, opB, opC, opD;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            que = itemView.findViewById(R.id.question_textVIew);
            opA = itemView.findViewById(R.id.optionA);
            opB = itemView.findViewById(R.id.optionB);
            opC = itemView.findViewById(R.id.optionC);
            opD = itemView.findViewById(R.id.optionD);

        }
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.que_item, null);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.que.setText(questions.get(position).getQuestionText());
        holder.opA.setText(questions.get(position).getOption1());
        holder.opB.setText(questions.get(position).getOption2());
        holder.opC.setText(questions.get(position).getOption3());
        holder.opD.setText(questions.get(position).getOption4());

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }*/

}