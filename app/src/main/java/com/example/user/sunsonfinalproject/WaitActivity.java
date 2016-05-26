package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by user on 2016/5/15.
 */
public class WaitActivity extends Activity {
    WaitUI waitUI;
    private int serverPort1=SelectActivity.serverPort;
    private Socket clientSocket1=SelectActivity.clientSocket;
    private BufferedReader br1=SelectActivity.br;
    public PrintWriter writer1=SelectActivity.writer;

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