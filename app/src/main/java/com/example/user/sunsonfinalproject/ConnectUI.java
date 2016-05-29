package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by user on 2016/5/26.
 */
public class ConnectUI {
    public ImageButton next;
    public Button play;

    public ConnectUI(Activity activity) {
        next = (ImageButton) activity.findViewById(R.id.next_button);
        play = (Button) activity.findViewById(R.id.button);
    }
}
