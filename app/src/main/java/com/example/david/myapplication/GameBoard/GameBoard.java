package com.example.david.myapplication.GameBoard;


import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.david.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameBoard extends Fragment {

    View v;
    ZoomViewContainer zoomViewContainer;
    TableLayout tableView;
    TableLayout tableLayout;

    public GameBoard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        zoomViewContainer = new ZoomViewContainer(getActivity(), createGameTable(8,8));
//        zoomViewContainer.setPadding(10, 10, 10, 10);
        v = zoomViewContainer;
//        AnimationManager.StartAnimation(getActivity(),v,R.animator.flipping);

        return v;
    }
    public Cell getCell(int x, int y ){
        TableRow row = (TableRow) tableLayout.getChildAt(x);
        View v = row.getChildAt(y);
        return (Cell) ((LinearLayout) v).getChildAt(0);
    }

    public void setQuestion(int x, int y , String question, String answer,String arrowPos, String arrowDir){
        Cell arrowCell = null;
        Cell cell = getCell(x,y);
        cell.setQuestion(question);
        cell.setAnswer(answer);

        if (arrowPos.equals("Left") ){
            arrowCell = getCell(x,y-1);
        }
        else if (arrowPos.equals("Right") ){
            arrowCell = getCell(x,y+1);
        }
        else if (arrowPos.equals("Up") ){
            arrowCell = getCell(x-1,y);
        }
        else if (arrowPos.equals("Down") ){
            arrowCell = getCell(x+1,y);
        }
        if (arrowCell != null){
            arrowCell.setArrow(arrowDir);
        }

    }

    public void zoomHome()
    {
        zoomViewContainer.smoothZoomTo(1f);
    }

    private TableLayout createGameTable(int columnCount, int rowCount)
    {   
        tableLayout = new TableLayout(getActivity());
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        float screenWidth = metrics.heightPixels;
        float screenHeight = metrics.widthPixels;

        int maxCellWidth = (int) (screenWidth / columnCount) - 10;
     //   tableLayout.setStretchAllColumns(true);

        for (int y = 0; y < rowCount; y++) {

            TableRow tableRow = new TableRow(getActivity());
            for (int x= 0; x < columnCount; x++) {

//                final TextView crossCellTextView = new TextView(getActivity());
                Cell crossCellTextView = new Cell(this.getContext(),maxCellWidth);

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setBackgroundResource(R.drawable.textview_style);
                linearLayout.addView(crossCellTextView);

                TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, 1f);

                tableRow.addView(linearLayout,tableRowParams);
            }

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, 1f));

        }
        tableLayout.setPadding(1,1,8,8);
        //tableLayout.setBackgroundResource(R.drawable.shadow);
        return tableLayout;
    }

}
