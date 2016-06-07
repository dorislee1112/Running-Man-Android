package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Created by chanhua on 16/5/31.
 */
public class FirstActivity extends Activity {

    public ImageButton restart;
    public ImageButton exit;
    private BufferedReader br1=ConnectActivity.br;
    private PrintWriter writer1=ConnectActivity.writer;

    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one);

        restart=(ImageButton)this.findViewById(R.id.restart);
        Log.d("Tag", "in first btn out");

        this.restart.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                WaitActivity.play_again=1;
                intent.setClass(FirstActivity.this, WaitActivity.class);
                Log.d("Tag", "in first--------------------again---------------------------");
                FirstActivity.this.startActivity(intent);
            }
        });
        exit = (ImageButton)this.findViewById(R.id.exit);
        this.exit.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                AppManager.getAppManager().AppExit(FirstActivity.this);
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {}
        return true;
    }
}


