package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by chanhua on 16/5/12.
 */
public class SelectActivity extends Activity {
    SelectUI selectUI;
    private int serverPort1=ConnectActivity.serverPort;
    private Socket clientSocket1=ConnectActivity.clientSocket;
    private BufferedReader br1=ConnectActivity.br;
    private PrintWriter writer1=ConnectActivity.writer;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        selectUI = new SelectUI(this);

        selectUI.check.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true) {
                    selectUI.check.setImageResource(R.drawable.sign_onclick);
                    Intent intent = new Intent();
                    intent.setClass(SelectActivity.this, WaitActivity.class);
                    SelectActivity.this.startActivity(intent);
                    writer1.println("select");
                    writer1.flush();
                } else
                    selectUI.check.setImageResource(R.drawable.sign);
            }
        });
        selectUI.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectUI.up.setImageResource(R.drawable.up_onclick);
                writer1.println("up");
                writer1.flush();
            }
        });
        selectUI.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectUI.down.setImageResource(R.drawable.down_onclick);
                writer1.println("down");
                writer1.flush();
            }
        });
        selectUI.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectUI.down.setImageResource(R.drawable.right_onclick);
                writer1.println("right");
                writer1.flush();
            }
        });
        selectUI.left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectUI.left.setImageResource(R.drawable.left_onclick);
                writer1.println("left");
                writer1.flush();
            }
        });

    }
}
