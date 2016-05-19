package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by chanhua on 16/5/12.
 */
public class SelectActivity extends Activity {
    SelectUI selectUI;
    private InetAddress serverIp = EntryActivity.serverIp;
    private int serverPort=8888;
    private Socket clientSocket = EntryActivity.clientSocket;
    private BufferedReader br = EntryActivity.br;

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
                }
                else
                    selectUI.check.setImageResource(R.drawable.sign);
            }
        });
        selectUI.up.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true)
                    selectUI.up.setImageResource(R.drawable.up_onclick);
                else
                    selectUI.up.setImageResource(R.drawable.up);
            }
        });
        selectUI.down.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true)
                    selectUI.down.setImageResource(R.drawable.down_onclick);
                else
                    selectUI.down.setImageResource(R.drawable.down);
            }
        });
        selectUI.right.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true)
                    selectUI.right.setImageResource(R.drawable.right_onclick);
                else
                    selectUI.right.setImageResource(R.drawable.right);
            }
        });
        selectUI.left.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true)
                    selectUI.left.setImageResource(R.drawable.left_onclick);
                else
                    selectUI.left.setImageResource(R.drawable.left);
            }
        });

    }


}
