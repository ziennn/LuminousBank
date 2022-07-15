package com.example.luminousbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class StartQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
    }
    public void startQuiz(View view) {

        Spinner spinner = (Spinner) findViewById(R.id.quizCate);
        int quizCategory = spinner.getSelectedItemPosition();

        // Start Quiz
        Intent intent = new Intent(getApplicationContext(), QuizGame.class);
        intent.putExtra("QUIZ_CATEGORY", quizCategory);
        startActivity(intent);
    }
}
