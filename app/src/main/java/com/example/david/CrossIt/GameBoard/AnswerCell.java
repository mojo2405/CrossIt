package com.example.david.CrossIt.GameBoard;

import android.app.Fragment;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.david.CrossIt.R;

import org.w3c.dom.Text;

import static android.R.attr.fragment;

/**
 * Created by eitansh on 26/09/2016.
 */

public class AnswerCell extends EditText{


    public AnswerCell(final Context context, int cell_size) {
        super(context);

        this.setId(View.generateViewId());
        this.setBackgroundResource(R.color.answerCellBackground);
        this.setHeight(cell_size);
        this.setWidth(cell_size);
//        this.setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});
        this.setCursorVisible(false);
        this.setGravity(Gravity.CENTER);

        this.setOnFocusChangeListener( new View.OnFocusChangeListener(){

            public void onFocusChange( View view, boolean hasfocus){
                if(hasfocus){
                    view.setBackgroundResource( R.drawable.answer_cell_has_focus);
                }
                else{
                    view.setBackgroundResource( R.drawable.answer_cell_lost_focus);
                }
            }
        });

        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    ((EditText)v).setText("");
                    return true;
                }
                return false;
            }
        });


    }

}
