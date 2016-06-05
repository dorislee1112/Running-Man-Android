package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.graphics.PorterDuff.Mode;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by chanhua on 16/5/12.
 */
public class SelectActivity extends Activity {
    SelectUI selectUI;
    private int serverPort1=EntryActivity.serverPort;
    private Socket clientSocket1=ConnectActivity.clientSocket;
    private BufferedReader br1=ConnectActivity.br;
    private PrintWriter writer1=ConnectActivity.writer;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        selectUI = new SelectUI(this);

        Bundle bundle = this.getIntent().getExtras();
        String color = bundle.getString("color");
        selectUI.selectColor.setColorFilter(Color.parseColor(color), Mode.SRC_ATOP);

    System.out.println("OK");
        selectUI.check.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true) {
                    System.out.println("IN CHECK");
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
        selectUI.up.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    selectUI.up.setImageResource(R.drawable.up_onclick);
                    writer1.println("up");
                    writer1.flush();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    selectUI.up.setImageResource(R.drawable.up);
                }

                //Handle selected state change
                return true;
            }
        });
        selectUI.down.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    selectUI.down.setImageResource(R.drawable.down_onclick);
                    writer1.println("down");
                    writer1.flush();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    selectUI.down.setImageResource(R.drawable.down);
                }

                //Handle selected state change
                return true;
            }
        });
        selectUI.right.setOnTouchListener(new OnTouchListener() {
            @Override
            public  boolean onTouch(View v,MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    selectUI.right.setImageResource(R.drawable.right_onclick);
                    writer1.println("right");
                    writer1.flush();
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    selectUI.right.setImageResource(R.drawable.right);
                }
                //Handle selected state change
                return true;
            }

        });
        selectUI.left.setOnTouchListener(new OnTouchListener() {
            @Override
            public  boolean onTouch(View v,MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    selectUI.left.setImageResource(R.drawable.left_onclick);
                    writer1.println("left");
                    writer1.flush();
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    selectUI.left.setImageResource(R.drawable.left);
                }

                //Handle selected state change
                return true;
            }
        });

    }
}
