package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by chanhua on 16/5/12.
 */
public class EntryActivity extends Activity {
    EntryUI entryUI;
    public static InetAddress serverIp;
    public static int serverPort=8888;
    public static Socket clientSocket;
    public static BufferedReader br;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

        // 嘗試連接Server
        try {
            // 設定IP
            serverIp = InetAddress.getByName("140.114.123.209");
            // 初始socket連接
            clientSocket=new Socket(serverIp,serverPort);
            // 接收來自Server的訊息
            br=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // 關閉連線
            clientSocket.close();
        } catch (IOException e) {
            // 出錯後顯示錯誤訊息
            System.out.println( "Connect error.");
        }

        entryUI = new EntryUI(this);
        entryUI.start.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(EntryActivity.this, SelectActivity.class);
                EntryActivity.this.startActivity(intent);

            }

        });

    }


}
