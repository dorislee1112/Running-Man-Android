package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.ImageButton;

/**
 * Created by user on 2016/5/15.
 */
public class WaitUI {
    public ImageButton next;
    public WaitUI(Activity activity) {
        next = (ImageButton) activity.findViewById(R.id.next_button);
    }
}
