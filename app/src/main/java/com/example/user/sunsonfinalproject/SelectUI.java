package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by chanhua on 16/5/12.
 */
public class SelectUI {
    public ImageButton check;

    public SelectUI(Activity activity){
       check = (ImageButton)activity.findViewById(R.id.check_button);
    }

}
