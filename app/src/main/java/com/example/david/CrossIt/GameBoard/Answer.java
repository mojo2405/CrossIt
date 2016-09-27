package com.example.david.CrossIt.GameBoard;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.CrossIt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Answer {

    String answer;
    int answer_length;

    int cell_margin=10;
    int cell_size=180;
    EditText[] EditTextArray;



    public Answer(RelativeLayout rLayout1, Context context , String answer) {

        this.answer = answer;

        answer_length = answer.length();
        if (answer_length>6){
            cell_margin = 4;
            cell_size = 110;
        }


        EditTextArray = new EditText[answer_length];
        AnswerCell EditTextToSeeFirst = new AnswerCell(context,cell_size);
        setListener(EditTextToSeeFirst);

        EditTextArray[0] = EditTextToSeeFirst;


        RelativeLayout.LayoutParams fparams = new RelativeLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        fparams.setMargins(cell_margin,0,cell_margin,0);
        rLayout1.addView(EditTextToSeeFirst, fparams);


        for (int i = 1; i<answer_length ; i++){
            RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lparams.addRule(RelativeLayout.LEFT_OF, EditTextArray[i-1].getId());
            lparams.setMargins(cell_margin,0,cell_margin,0);
            AnswerCell newEditText = new AnswerCell(context,cell_size);
            setListener(newEditText);
            EditTextArray[i] = newEditText;

            rLayout1.addView(EditTextArray[i], lparams);

        }
        rLayout1.setGravity(Gravity.CENTER );
    }

    public void setListener(final AnswerCell ac){

        ac.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newText = s.toString();
                int iLen=s.length();
                if (iLen>1){
                    String t = newText.substring(0, 1);
                    ac.setText(t);
                }
                goToNextAvailableCell();
            }
        });

    }

    public  void goToNextAvailableCell(){
        for (int i = 0; i<answer_length ; i++) {
            if(EditTextArray[i].getText().toString().matches("")){
                EditTextArray[i].requestFocus();
                return;
            }
        }
        //Did not found empty cell
        checkCorrectAnswer();
    }

    private void checkCorrectAnswer(){
        String tryAnswer = "";
        for (int i = 0; i<answer_length ; i++) {
            tryAnswer += EditTextArray[i].getText().toString();
        }
        if (tryAnswer.matches(answer)){
            Log.d("Correct !!","Correct Answer");
        }

    }

}
