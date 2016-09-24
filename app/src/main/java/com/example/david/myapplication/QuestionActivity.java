package com.example.david.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.david.myapplication.R;

/**
 * Created by David on 24/09/2016.
 */
public class QuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_question);
    }
}
