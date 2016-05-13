package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.Button;

/**
 * Created by chanhua on 16/5/12.
 */
public class EntryUI {
        public Button start;

        public EntryUI(Activity activity){
            start = (Button)activity.findViewById(R.id.start_button);
        }

}
