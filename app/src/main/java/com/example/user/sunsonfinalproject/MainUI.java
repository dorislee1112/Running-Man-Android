package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by chanhua on 16/5/31.
 */
public class MainUI {
    public ImageView bg;
    public ImageView shake;

    public MainUI(Activity activity) {
        bg = (ImageView) activity.findViewById(R.id.imageView1);
        shake = (ImageView) activity.findViewById(R.id.imageView2);
    }
}
