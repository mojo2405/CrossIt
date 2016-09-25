package com.example.david.CrossIt.ToolBar;


import android.app.Fragment;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.CrossIt.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Points extends Fragment {

    View v;
    TextView txtPoints;
    ImageView imgPoints;
    public Points() {
        // Required empty public constructor

    }

    public void addCoins(int amountToAdd)
    {
        txtPoints.setText(" "+ String.valueOf(amountToAdd));
    }

    public void RemoveCoins(int amount)
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_points, container, false);
        txtPoints = (TextView) v.findViewById(R.id.points);
        imgPoints = (ImageView)v.findViewById(R.id.DiamondSign);

        txtPoints.setBackgroundDrawable(createRightThumbDrawable());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNum = 0 + (int) (Math.random() * 10000000);
                addCoins(randomNum);
                //AnimationManager.ShowMsg(getActivity(),"Coin Click"+txtPoints.getPivotY()+"-"+txtPoints.getY());
            }
        });

//        AnimationManager.StartAnimation(getActivity(), v, R.animator.flipping,2000);

       // StartAnimation(imgCoins);
       // StartAnimation(txtPoints);
        return v;
    }

    public Drawable createRightThumbDrawable(){

        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(1500, 0);
        path.lineTo(0, 1500);
        path.close();

        PathShape shape = new PathShape(path, 1500, 110);
        ShapeDrawable drawable = new ShapeDrawable(shape);

        drawable.getPaint().setColor(getResources().getColor(R.color.dimGrey));

        return drawable;

    }


}
