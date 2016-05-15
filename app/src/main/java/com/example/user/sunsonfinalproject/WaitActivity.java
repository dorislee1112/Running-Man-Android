package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by user on 2016/5/15.
 */
public class WaitActivity extends Activity {
    WaitUI waitUI;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting);
        waitUI=new WaitUI(this);

        waitUI.next.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //waitUI.next.setImageResource(R.drawable.sign_onclick);
                Intent intent = new Intent();
                intent.setClass(WaitActivity.this, MainActivity.class);
                WaitActivity.this.startActivity(intent);
            }

        });
    }
}