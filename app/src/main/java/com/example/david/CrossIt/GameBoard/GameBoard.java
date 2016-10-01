package com.example.david.CrossIt.GameBoard;


import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.david.CrossIt.GameBoard.QuestionDialog.QuestionFragment;
import com.example.david.CrossIt.GameBoard.Zoom.ZoomViewContainer;
import com.example.david.CrossIt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameBoard extends Fragment {

    private static TableLayout tableLayout;
    View v;
    ZoomViewContainer zoomViewContainer;
    TableLayout tableView;
    public static GridCell selectedGridCell;

    public GameBoard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        zoomViewContainer = new ZoomViewContainer(getActivity(), createGameTable(8,8));
        v = zoomViewContainer;

        return v;
    }

    public static GridCell getCell(int x, int y){
        TableRow row = (TableRow) tableLayout.getChildAt(x);
        View v = row.getChildAt(y);
        return (GridCell) ((LinearLayout) v).getChildAt(0);
    }

    public static void setQuestion(int x, int y , String question, String answer,String arrowPos, String arrowDir, String arrowType){
        GridCell arrowGridCell = null;
        GridCell gridCell = getCell(x,y);
        gridCell.setQuestion(question);
        gridCell.setAnswer(answer);
        gridCell.setArrow(arrowType);

        if (arrowPos.equals("Left") ){
            arrowGridCell = getCell(x,y-1);
        }
        else if (arrowPos.equals("Right") ){
            arrowGridCell = getCell(x,y+1);
        }
        else if (arrowPos.equals("Top") ){
            arrowGridCell = getCell(x-1,y);
        }
        else if (arrowPos.equals("Bottom") ){
            arrowGridCell = getCell(x+1,y);
        }
        if (arrowGridCell != null){
            arrowGridCell.drawArrow(arrowType);
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

        for (int y = 0; y < rowCount; y++) {

            TableRow tableRow = new TableRow(getActivity());
            for (int x= 0; x < columnCount; x++) {

                final GridCell crossGridCellTextView = new GridCell(this.getContext(),maxCellWidth,y,x);
                crossGridCellTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String q = crossGridCellTextView.getQuestion();
                            if (q != null) {
                                QuestionFragment fragInfo = new QuestionFragment();
                                selectedGridCell = crossGridCellTextView;
                                fragInfo.show(getFragmentManager(),"question");
                            }else{
                                // TODO : did not press on question
                            }
                        }
                });
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setBackgroundResource(R.drawable.textview_style);
                linearLayout.addView(crossGridCellTextView);

                TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, 1f);

                tableRow.addView(linearLayout,tableRowParams);
            }

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, 1f));

        }
        tableLayout.setPadding(1,1,8,8);
        //tableLayout.setBackgroundResource(R.drawable.shadow);
        return tableLayout;
    }



    public static TextView[] getCorrespondingTextView(GridCell cell){
        int start_x = cell.x;
        int start_y = cell.y;

        int answer_length = cell.getAnswer().length();
        TextView[] tv = new TextView[answer_length];


        switch (cell.getArrow()) {
            case "LeftLeft":
                start_y--;
                for (int i = 0 ; i < answer_length;i++){
                    tv[i] = GameBoard.getCell(start_x,start_y);
                    start_y--;
                }
                break;
            case "BottomDown":
                start_x++;
                for (int i = 0 ; i < answer_length;i++){
                    tv[i] = GameBoard.getCell(start_x,start_y);
                    start_x++;
                }
                break;
            case "RightDown":
                start_y++;
                for (int i = 0 ; i < answer_length;i++){
                    tv[i] = GameBoard.getCell(start_x,start_y);
                    start_x++;
                }
                break;
            case "BottomLeft":
                start_x++;
                for (int i = 0 ; i < answer_length;i++){
                    tv[i] = GameBoard.getCell(start_x,start_y);
                    start_y--;
                }
                break;
            case "TopLeft":
                start_x--;
                for (int i = 0 ; i < answer_length;i++){
                    tv[i] = GameBoard.getCell(start_x,start_y);
                    start_y--;
                }
                break;
        }
        return tv;
    }

}
