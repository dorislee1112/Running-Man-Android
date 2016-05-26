package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by chanhua on 16/5/12.
 */
public class SelectUI {
    public ImageButton check;
    public ImageButton up;
    public ImageButton down;
    public ImageButton left;
    public ImageButton right;

    public SelectUI(Activity activity){
       check = (ImageButton)activity.findViewById(R.id.check_button);
        //check.requestFocus();
        up= (ImageButton)activity.findViewById(R.id.up_button);
        //up.requestFocus();
        down= (ImageButton)activity.findViewById(R.id.down_button);
        //down.requestFocus();
        right = (ImageButton)activity.findViewById(R.id.right_button);
        //right.requestFocus();
        left = (ImageButton)activity.findViewById(R.id.left_button);
        //left.requestFocus();
    }

}
