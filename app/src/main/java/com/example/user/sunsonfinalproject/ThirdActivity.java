package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Created by chanhua on 16/5/31.
 */
public class ThirdActivity extends Activity {

    public ImageButton restart,exit;
    private BufferedReader br1=ConnectActivity.br;
    private PrintWriter writer1=ConnectActivity.writer;

    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three);

        restart=(ImageButton)this.findViewById(R.id.restart);

       this. restart.setOnClickListener(new ImageButton.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
               intent.setClass(ThirdActivity.this, WaitActivity.class);
               ThirdActivity.this.startActivity(intent);
               WaitActivity.play_again = 1;
           }
       });
        exit = (ImageButton)this.findViewById(R.id.exit);
        this.exit.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                AppManager.getAppManager().AppExit(ThirdActivity.this);
            }
        });
    }
}


