package com.example.david.CrossIt.ToolBar;


import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Fragment;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.CrossIt.AnimationManager.AnimationManager;
import com.example.david.CrossIt.R;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class Points extends Fragment {

    View v;
    TextView txtPoints;
    ImageView imgPoints;
    Animation animFadein;

    public Points() {
        // Required empty public constructor

    }

    public void addPoints(int amountToAdd)
    {
        int points = getPoints() + amountToAdd;
        //Animate diamond
        imgPoints.startAnimation(animFadein);

        //Animate numbers
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(getPoints(), points);
        animator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                txtPoints.setText(String.valueOf(animation.getAnimatedValue()));
            }
        });
        animator.setEvaluator(new TypeEvaluator<Integer>() {
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return Math.round(startValue + (endValue - startValue) * fraction);
            }
        });
        animator.setDuration(1000);
        animator.start();

    }

    public int getPoints(){
        return  Integer.parseInt(txtPoints.getText().toString());
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
                addPoints(randomNum);
                //AnimationManager.ShowMsg(getActivity(),"Coin Click"+txtPoints.getPivotY()+"-"+txtPoints.getY());
            }
        });

//        AnimationManager.StartAnimation(getActivity(), v, R.animator.flipping,2000);

       // StartAnimation(imgCoins);
       // StartAnimation(txtPoints);

        // load the animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);
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
