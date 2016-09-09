package com.example.david.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.widget.TableLayout;


public class ZoomViewContainer extends ZoomView {
    public ZoomViewContainer(Context context, View layout) {
        super(context);
        this.setMaxZoom(3.5f);
        //this.setmin
        this.setBackgroundColor(Color.WHITE);
        Point size= LocalDevice.getScreenSize(context);
        int s = size.x < size.y ? size.x : size.y-100;
        this.addView(layout, new TableLayout.LayoutParams(s, s));
    }
}