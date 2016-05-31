package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
=======
>>>>>>> origin/master

/**
 * Created by chanhua on 16/5/12.
 */
public class EntryActivity extends Activity {
    EntryUI entryUI;
<<<<<<< HEAD
    public static int serverPort;
    public static InetAddress serverIp;
=======
    private String addr;

>>>>>>> origin/master
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

        entryUI = new EntryUI(this);

        entryUI.start.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
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

=======
                if(IPCheck()) {
                    // User input IP address
                    addr = entryUI.IPAddr0.getText().toString() + "." + entryUI.IPAddr1.getText().toString()
                            + "." + entryUI.IPAddr2.getText().toString() + "." + entryUI.IPAddr3.getText().toString();

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("IP", addr);
                    intent.putExtras(bundle);
                    intent.setClass(EntryActivity.this, ConnectActivity.class);
                    EntryActivity.this.startActivity(intent);
                }
                else {
                    entryUI.msg.setTextColor(Color.rgb(255,0,0)); //red
                }
>>>>>>> origin/master
            }

        });

    }

    public boolean IPCheck(){
        if(entryUI.IPAddr0.length() == 0 || entryUI.IPAddr1.length() == 0 ||
                entryUI.IPAddr2.length() == 0 || entryUI.IPAddr3.length() == 0){
            return false;
        }
        return true;
    }



}
