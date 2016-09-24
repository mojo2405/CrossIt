package com.example.david.myapplication.GameBoard;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.david.myapplication.R;

/**
 * Created by David on 24/09/2016.
 */

class Cell extends TextView {
    private String question;
    private String answer;
    private static final int ARROW_SIZE = 30;

    public Cell(Context context,int cellSize) {
        super(context);
        this.setTextColor(Color.BLACK);
        this.setWidth(cellSize);
        this.setHeight(cellSize);
        this.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        this.setSingleLine(false);
        this.setMaxLines(3);
        this.setBackgroundResource(R.drawable.cellborder);
        this.setTextSize(8);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        ((MainActivity) getActivity()).startCoinAnimation();
            }
        });

        Typeface type = Typeface.createFromAsset(getContext().getAssets(),"fonts/OpenSansHebrew-Regular.ttf");
        this.setTypeface(type);

        this.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
        this.setText(this.getQuestion());
    }

    public void setArrow(String direction){

        if (direction.equals("Left")){
            Drawable image = this.getContext().getResources().getDrawable( R.drawable.arrow_left );
            Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
            // Scale it to 30 x 30
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, ARROW_SIZE, ARROW_SIZE, true));
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, d, null);
        }
        else if (direction.equals("Right")){
            Drawable image = this.getContext().getResources().getDrawable( R.drawable.arrow_right );
            Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
            // Scale it to 30 x 30
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, ARROW_SIZE, ARROW_SIZE, true));
            this.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
        }
        else if (direction.equals("Up")){
            Drawable image = this.getContext().getResources().getDrawable( R.drawable.arrow_up );
            Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
            // Scale it to 30 x 30
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, ARROW_SIZE, ARROW_SIZE, true));
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, d);
        }
        else if (direction.equals("Down")){
            Drawable image = this.getContext().getResources().getDrawable( R.drawable.arrow_down );
            Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
            // Scale it to 30 x 30
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, ARROW_SIZE, ARROW_SIZE, true));
            this.setCompoundDrawablesWithIntrinsicBounds(null, d, null, null);
        }
    }
}
