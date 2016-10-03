package com.example.david.CrossIt.GameBoard.QuestionDialog;

import android.app.Fragment;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.david.CrossIt.R;

import org.w3c.dom.Text;

import static android.R.attr.fragment;
import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by eitansh on 26/09/2016.
 */

public class AnswerCell extends EditText implements OnFocusChangeListener , OnKeyListener, TextWatcher{
    private QuestionFragment questionFragment;

    public AnswerCell(Context context) {
        super(context);
    }


    public AnswerCell(View v, QuestionFragment questionFragment,int cell_size) {
        super(v.getContext());
        this.questionFragment = questionFragment;

        this.setId(View.generateViewId());
        this.setBackgroundResource(R.color.answerCellBackground);
        this.setHeight(cell_size);
        this.setWidth(cell_size);
        this.setCursorVisible(false);
        this.setGravity(Gravity.CENTER);
        this.setTextIsSelectable(false);

        setOnFocusChangeListener(this);
        setOnKeyListener(this);
        addTextChangedListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(b){
            view.setBackgroundResource( R.drawable.answer_cell_has_focus);
        }
        else{
            view.setBackgroundResource( R.drawable.answer_cell_lost_focus);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
        if(keyCode == KeyEvent.KEYCODE_DEL) {
            ((EditText)view).setText("");
            return true;
        }
        return false;
    }

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
            this.setText(t);
        }
        this.endBatchEdit();
        questionFragment.goToNextAvailableCell();
    }
}
