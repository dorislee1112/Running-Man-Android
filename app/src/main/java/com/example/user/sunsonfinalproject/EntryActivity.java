package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by chanhua on 16/5/12.
 */
public class EntryActivity extends Activity {
    EntryUI entryUI;
    public static int serverPort;
    public static InetAddress serverIp;

    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

        entryUI = new EntryUI(this);

        entryUI.start.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entryUI.ip.getText()!=null && entryUI.port.getText()!=null) {
                    try {
                        serverPort = Integer.valueOf(entryUI.port.getText().toString());
                        serverIp = InetAddress.getByName(entryUI.ip.getText().toString());
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent();
                    intent.setClass(EntryActivity.this, ConnectActivity.class);
                    EntryActivity.this.startActivity(intent);
                }

            }

        });

    }



}
