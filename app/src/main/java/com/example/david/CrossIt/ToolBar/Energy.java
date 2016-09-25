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
import android.widget.TextView;

import com.example.david.CrossIt.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Energy extends Fragment {


    TextView txtEnergy;
    View v;
    public Energy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_energy, container, false);
        txtEnergy = (TextView) v.findViewById(R.id.txtEnergy);
        txtEnergy.setBackgroundDrawable(createRightThumbDrawable());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  AnimationManager.ShowMsg(getActivity(), "Coin Click" + txtEnergy.getPivotY() + "-" + txtEnergy.getY());
                int randomNum = 0 + (int) (Math.random() * 1000000);
                addEnergy(randomNum);
            }
        });
        return v;
    }

    public void addEnergy(int amountToAdd)
    {

        txtEnergy.setText(" "+ String.valueOf(amountToAdd));
       //AnimationManager.StartAnimation(getActivity(), v, R.animator.flipping, 1500);
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
