package com.example.david.CrossIt.GameBoard;

import android.app.ActionBar;
import android.app.Fragment;
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

import com.example.david.CrossIt.GameBoard.QuestionDialog.QuestionFragment;
import com.example.david.CrossIt.R;

/**
 * Created by David on 24/09/2016.
 */

public class GridCell extends TextView {
    private String question;
    private String answer;
    private final static int ARROW_PADDING = 10;
    private final static int ARROW_SIZE = 20;
    private String arrowType;
    public int x;
    public int y;

    public GridCell(final Context context, int cellSize, int x , int y) {
        super(context);
        this.x = x;
        this.y = y;
        
        this.setTextColor(Color.BLACK);
        this.setWidth(cellSize);
        this.setHeight(cellSize);
        this.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        this.setSingleLine(false);
        this.setMaxLines(1);
        this.setBackgroundResource(R.drawable.cellborder);
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
        this.setMaxLines(3);
        this.setTextSize(8);
        this.question = question;
        this.setText(this.getQuestion());
    }

    public void setArrow(String arrowType){
        this.arrowType = arrowType;
    }

    public String getArrow(){
        return this.arrowType;
    }

    public void drawArrow(String arrowType){
        Drawable icon = null;

        switch (arrowType) {
            case "LeftLeft":
                icon = getContext().getResources().getDrawable(
                        R.drawable.arrow_left,null);
                icon = resize(icon);
                this.setPadding(0,0,ARROW_PADDING,0);

                this.setCompoundDrawablesWithIntrinsicBounds(
                        null, null, icon, null);
                break;
            case "BottomDown":
                icon = getContext().getResources().getDrawable(
                        R.drawable.arrow_down,null);
                icon = resize(icon);
                this.setPadding(0,ARROW_PADDING,0,0);
                this.setCompoundDrawablesWithIntrinsicBounds(
                        null, icon, null, null);
                break;
            case "RightDown":
                icon = getContext().getResources().getDrawable(
                        R.drawable.arrow_right_down,null);
                icon = resize(icon);
                this.setPadding(ARROW_PADDING,0,0,0);
                this.setCompoundDrawablesWithIntrinsicBounds(
                        icon, null, null, null);
                break;
            case "BottomLeft":
                icon = getContext().getResources().getDrawable(
                        R.drawable.arrow_down_left,null);
                icon = resize(icon);
                this.setPadding(0,ARROW_PADDING,0,0);
                this.setCompoundDrawablesWithIntrinsicBounds(
                        null, icon, null, null);
                break;
            case "TopLeft":
                icon = getContext().getResources().getDrawable(
                        R.drawable.arrow_up_left,null);
                icon = resize(icon);
                this.setPadding(0,0,0,ARROW_PADDING);
                this.setCompoundDrawablesWithIntrinsicBounds(
                        null, null, null, icon);
                break;
        }
    }


    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, ARROW_SIZE, ARROW_SIZE, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }


}
