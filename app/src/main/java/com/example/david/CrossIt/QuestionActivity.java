package com.example.david.CrossIt;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.david.CrossIt.GameBoard.Answer;

/**
 * Created by David on 24/09/2016.
 */
public class QuestionActivity extends AppCompatActivity {
    String question;
    String answer;

    InputMethodManager inputMethodManager;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_question);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        TextView questionTxt = (TextView) this.findViewById(R.id.questionTextView);


        RelativeLayout answerLayout = (RelativeLayout)  this.findViewById(R.id.answerLayout);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            // TODO: Error exception - did not get question from cell
        }
        question = extras.getString("question");
        answer = extras.getString("answer");
        questionTxt.setText(question);

        Answer fragobj = new Answer(answerLayout,this,answer);


    }


}
