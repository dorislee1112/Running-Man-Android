package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by chanhua on 16/5/12.
 */

public class EntryUI {
        public Button start;
        public EditText ip;
        public  EditText port;

        public EntryUI(Activity activity) {
            start = (Button) activity.findViewById(R.id.start_button);
            ip = (EditText) activity.findViewById(R.id.editText);
            ip.setInputType(EditorInfo.TYPE_CLASS_PHONE);
            port = (EditText) activity.findViewById(R.id.editText2);
            port.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        }

}
