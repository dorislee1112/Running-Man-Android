package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by chanhua on 16/5/12.
 */
public class MainUI {
    public ImageButton bomb;
    public TextView bomb_num;

    public MainUI(Activity activity) {

        bomb = (ImageButton) activity.findViewById(R.id.bomb);
        bomb_num=(TextView) activity.findViewById(R.id.bomb_num);
    }
}