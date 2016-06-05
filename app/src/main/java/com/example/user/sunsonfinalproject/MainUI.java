package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;
import android.widget.TextView;

/**
 * Created by chanhua on 16/5/12.
 */
public class MainUI {
    public ImageButton bomb;
    public ImageView bg;
    public ImageView shake;
    public TextView math;
    public Button send;
    public EditText ans;
    public ImageView math_bg;
    public TextView bomb_num;

    public MainUI(Activity activity) {

        bomb = (ImageButton) activity.findViewById(R.id.bomb);
        bg = (ImageView)activity.findViewById(R.id.imageView1);
        shake = (ImageView)activity.findViewById(R.id.imageView2);
        math = (TextView)activity.findViewById(R.id.math);
        send = (Button)activity.findViewById(R.id.send);
        ans = (EditText)activity.findViewById(R.id.ans);
        math_bg = (ImageView)activity.findViewById(R.id.math_bg);
        bomb_num=(TextView) activity.findViewById(R.id.bomb_num);
    }
}