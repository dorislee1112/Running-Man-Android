package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import java.net.InetAddress;
import android.widget.TextView;
import android.view.inputmethod.EditorInfo;
import android.text.Editable;

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
            port = (EditText) activity.findViewById(R.id.editText2);
        }

}
