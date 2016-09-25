package com.example.david.CrossIt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by David on 24/09/2016.
 */
public class QuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_question);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String question = extras.getString("question");
            String answer = extras.getString("answer");
        }
    }
}
