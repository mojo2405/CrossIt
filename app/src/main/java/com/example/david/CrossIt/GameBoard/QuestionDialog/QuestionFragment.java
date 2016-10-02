package com.example.david.CrossIt.GameBoard.QuestionDialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.david.CrossIt.AnimationManager.AnimationManager;
import com.example.david.CrossIt.GameBoard.GameBoard;
import com.example.david.CrossIt.GameBoard.GridCell;
import com.example.david.CrossIt.R;
import com.facebook.FacebookSdk;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by David on 24/09/2016.
 */
public class QuestionFragment extends DialogFragment {
    private static GridCell gridCell;
    private static int answer_length;
    private static TextView[] EditTextArray;
    private static String answer;

    String question;



    int cell_margin=10;
    int cell_size=180;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_question, container, false);
        TextView questionTxt = (TextView) v.findViewById(R.id.questionTextView);
        RelativeLayout rLayout1 = (RelativeLayout)  v.findViewById(R.id.answerLayout);


        gridCell = GameBoard.selectedGridCell;
        TextView[] tv = GameBoard.getCorrespondingTextView(gridCell);

        question = gridCell.getQuestion();
        answer = gridCell.getAnswer();

        questionTxt.setText(question);

        answer_length = answer.length();
        if (answer_length>6){
            cell_margin = 4;
            cell_size = 110;
        }


        EditTextArray = new EditText[answer_length];
        AnswerCell EditTextToSeeFirst = new AnswerCell(v,this,cell_size);
        if (!tv[0].getText().equals("")){
            EditTextToSeeFirst.setText(tv[0].getText().toString());
            EditTextToSeeFirst.setFocusable(false);
        }


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
            AnswerCell newEditText = new AnswerCell(v,this,cell_size);

            if (!tv[i].getText().equals("")){
                newEditText.setText(tv[i].getText().toString());
                newEditText.setFocusable(false);
            }

            EditTextArray[i] = newEditText;

            rLayout1.addView(EditTextArray[i], lparams);

        }
        rLayout1.setGravity(Gravity.CENTER );

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();


        final View decorView = getDialog()
                .getWindow()
                .getDecorView();

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(decorView,

                PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1.0f),
                PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f));
        scaleDown.setDuration(500);
        scaleDown.start();

    }

    public static GridCell getGridCell(){
        return gridCell;
    }





    public void goToNextAvailableCell(){
        int focusedEditText = 0;
        for (int i = 0; i<answer_length ; i++) {
            if (EditTextArray[i].hasFocus()){
                focusedEditText = i;
                if (focusedEditText+1 == answer_length){
                    focusedEditText = 0;
                }else{
                    focusedEditText++;
                }
            }

            if(EditTextArray[i].getText().toString().matches("")){
                EditTextArray[i].requestFocus();
                return;
            }
        }
        //Did not found empty cell
        if (!checkCorrectAnswer()){
            EditTextArray[focusedEditText].requestFocus();
        }
    }

    private boolean checkCorrectAnswer(){
        String tryAnswer = "";
        for (int i = 0; i<answer_length ; i++) {
            tryAnswer += EditTextArray[i].getText().toString();
        }
        if (tryAnswer.matches(answer)){
            Log.d("Correct !!","Correct Answer");
            correctAnswer();
            return true;
        }
        return false;
    }

    private void correctAnswer   (){

        GridCell cell = getGridCell();
        TextView[] tv = GameBoard.getCorrespondingTextView(cell);
        for (int i = 0 ; i < tv.length ;i++) {
            String s = Character.toString( cell.getAnswer().charAt(i));
            tv[i].setText(s);
        }

        this.dismiss();

        //Remove listener for this Grid Cell
        cell.setOnClickListener(null);
        // TODO : Cool correct animation
        // TODO : Add points
        // TODO : progress wheel advance
    }

}
