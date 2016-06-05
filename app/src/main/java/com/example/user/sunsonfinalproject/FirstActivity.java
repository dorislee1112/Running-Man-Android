package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
        this.restart.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this, WaitActivity.class);
                FirstActivity.this.startActivity(intent);
                System.out.println("--------------------again---------------------------");
                WaitActivity.play_again = 1;
            }
        });
        exit = (ImageButton)this.findViewById(R.id.exit);
        this.exit.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                AppManager.getAppManager().AppExit(FirstActivity.this);
            }
        });

    }
}


