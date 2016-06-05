package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by chanhu on 16/6/2.
 */
public class MathUI {
    public TextView q;
    public Button send;
    public EditText user_ans;
    public TextView text;

    public MathUI(Activity activity) {
        q = (TextView) activity.findViewById(R.id.question);
        send = (Button)activity.findViewById(R.id.send);
        user_ans = (EditText)activity.findViewById(R.id.ans);
        text = (TextView)activity.findViewById(R.id.textView3);
    }
}
