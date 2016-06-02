package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.ImageButton;

/**
 * Created by chanhua on 16/5/12.
 */
public class MainUI {
    public ImageButton bomb;

    public MainUI(Activity activity){
        bomb = (ImageButton)activity.findViewById(R.id.bomb);
    }

}
