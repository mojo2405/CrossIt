package com.example.david.myapplication.AnimationManager;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.david.myapplication.R;

/**
 * Created by Dror on 09/02/2016.
 */
public class AnimationManager {

    public static void StartAnimation(Context c, Object target, int animationId, int duration)
    {
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(c,animationId);
anim.addListener(new Animator.AnimatorListener() {
    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
});
      //  anim.setRepeatMode(ValueAnimator.INFINITE);
       // anim.setRepeatCount(-1);
        anim.setTarget(target);
        anim.setDuration(duration);
        anim.start();


    }

    public static void StartCoinAnimation(Context c, RelativeLayout root, float toPosX, float toPosy)
    {
        ImageView iv = new ImageView(c);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity= Gravity.CENTER;
        iv.setLayoutParams(layoutParams);

        iv.setImageResource(R.drawable.diamond);
        root.addView(iv);
        StartAnimation(c, iv, R.animator.flipping, 1000);
        AnimationSet set = new AnimationSet( true );

     //   TranslateAnimation translate = new TranslateAnimation(0, 0,
         //       TranslateAnimation.ABSOLUTE, toPosX - getLeft(), 0, 0,
        //        TranslateAnimation.ABSOLUTE, toPosy - getTop());
        TranslateAnimation translate = new TranslateAnimation(0, -900,900 , 120);
     //  Animation translate = new TranslateAnimation(0,toPosX,0,toPosy);
        translate.setFillAfter(true);
        //TranslateAnimation translate =  new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.1f, Animation.RELATIVE_TO_PARENT, 0.3f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.9f);
      //  translate.setRepeatMode(Animation.INFINITE);
       // translate.setRepeatCount(-1);
        translate.setDuration(1000);
        set.addAnimation(translate);
        RotateAnimation rotate = new RotateAnimation(0.0f, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);


    //    rotate.setRepeatMode(Animation.INFINITE);
        rotate.setDuration(2000);
      //  rotate.setRepeatCount(-1);
      //  set.addAnimation(rotate);

        iv.startAnimation(set);




    }

    public static void ShowMsg(Context c, String str)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        dialog.setTitle("MyException Occured");
        dialog.setMessage(str);
        dialog.setNeutralButton("Cool", null);
        dialog.create().show();
    }

}
