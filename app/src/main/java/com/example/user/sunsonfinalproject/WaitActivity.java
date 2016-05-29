package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by user on 2016/5/15.
 */
public class WaitActivity extends Activity {
    WaitUI waitUI;
    private int serverPort1=ConnectActivity.serverPort;
    private Socket clientSocket1=ConnectActivity.clientSocket;
    private BufferedReader br1=ConnectActivity.br;
    private PrintWriter writer1=ConnectActivity.writer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting);
        waitUI=new WaitUI(this);

        System.out.println("before writer");
       // writer1.println("out");
       // writer1.flush();
        System.out.println("after writer");


        while(true) {
            try {
                //br1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
                System.out.println("in try");
                //         writer1.println("in");
                //        writer1.flush();
                //String line = ConnectActivity.br.readLine();
                //System.out.println("xxxxxxxxxxxxxxx" + line);
                if (br1.readLine().equals("game")) {
                    System.out.println("xxxxxxxxxxxxxxx--iniinininininininin");
                    Intent intent = new Intent();
                    intent.setClass(WaitActivity.this, MainActivity.class);
                    WaitActivity.this.startActivity(intent);
                }

            } catch (IOException e) {
            }
        }
       /* waitUI.next.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //waitUI.next.setImageResource(R.drawable.sign_onclick);
                Intent intent = new Intent();
                intent.setClass(WaitActivity.this, MainActivity.class);
                WaitActivity.this.startActivity(intent);
            }

        });*/
    }
}