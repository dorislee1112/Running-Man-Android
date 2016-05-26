package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.ImageButton;

/**
 * Created by user on 2016/5/26.
 */
public class ConnectUI {
    public ImageButton next;
    public ConnectUI(Activity activity) {
        next = (ImageButton) activity.findViewById(R.id.next_button);
    }
}
