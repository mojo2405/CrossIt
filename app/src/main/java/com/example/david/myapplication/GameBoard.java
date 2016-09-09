package com.example.david.myapplication;


import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameBoard extends Fragment {

    View v;
    ZoomViewContainer zoomViewContainer;
    TableLayout tableView;

    public GameBoard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);



        zoomViewContainer = new ZoomViewContainer(getActivity(), createGameTable(5,8));
//        zoomViewContainer.setPadding(10, 10, 10, 10);
        v = zoomViewContainer;
//        AnimationManager.StartAnimation(getActivity(),v,R.animator.flipping);

        return v;
    }

    public void zoomHome()
    {
        zoomViewContainer.smoothZoomTo(1f);
    }

    private TableLayout createGameTable(int columnCount, int rowCount)
    {   
        TableLayout tableLayout = new TableLayout(getActivity());
     //   tableLayout.setStretchAllColumns(true);

        for (int y = 0; y < rowCount; y++) {

            TableRow tableRow = new TableRow(getActivity());
            for (int x= 0; x < columnCount; x++) {

                final TextView crossCellTextView = new TextView(getActivity());
                crossCellTextView.setTextColor(Color.BLACK);
                crossCellTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                crossCellTextView.setSingleLine(false);
                crossCellTextView.setMaxLines(3);
                crossCellTextView.setBackgroundResource(R.drawable.cellborder);
                crossCellTextView.setTextSize(8);
                crossCellTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        ((MainActivity) getActivity()).startCoinAnimation();
                    }
                });

                Typeface type = Typeface.createFromAsset(getContext().getAssets(),"fonts/OpenSansHebrew-Regular.ttf");
                crossCellTextView.setTypeface(type);

                crossCellTextView.setText("Cros-IT!!!! \n 1234567890 \n בדיקה בדיקה בדיקה ");
             //   crossCellTextView.setTag(toTagName(new Point(x,y)));
                crossCellTextView.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
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
