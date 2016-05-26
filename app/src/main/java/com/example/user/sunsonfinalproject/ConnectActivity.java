package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by user on 2016/5/26.
 */
public class ConnectActivity extends Activity{
    ConnectUI connectUI;
    public static int serverPort=8000;
    public static InetAddress serverIp;
    public static Socket clientSocket;
    public static BufferedReader br;
    public static PrintWriter writer;
    Thread thread;
    int finish=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect);
        connectUI=new ConnectUI(this);
        System.out.println("還沒連...");
        thread=new Thread(Connection);
        System.out.println("GGGGG.....");
        thread.start();
        System.out.println("已經連線...");
        /*writer.println("enter");
        writer.flush();*/


    }
    private Runnable Connection=new Runnable() {
        public void run() {
            // TODO Auto-generated method stub
            try{
                // IP為Server端
                serverIp = InetAddress.getByName("192.168.43.63");
                System.out.println(serverIp);
                clientSocket = new Socket();
                clientSocket.bind(null);
                clientSocket.connect(new InetSocketAddress(serverIp, serverPort), 100000);
                System.out.println("Socket已經連線");
                //取得網路輸出串流
                writer = new PrintWriter( new OutputStreamWriter(clientSocket.getOutputStream()));
                // 取得網路輸入串流
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("trytry");
                while(true){
                    writer.println("enter");
                    writer.flush();
                    try {
                        String line=br.readLine();
                        System.out.println("收到的");
                        if (line.equals("start")) {
                            Log.d("TAG", "start...");
                            writer.println("i get start");
                            writer.flush();
                            Intent intent = new Intent();
                            intent.setClass(ConnectActivity.this, SelectActivity.class);
                            ConnectActivity.this.startActivity(intent);
                        }
                    }catch (IOException e){}
                }
            }catch(Exception e){
                //當斷線時會跳到catch,可以在這裡寫上斷開連線後的處理
                e.printStackTrace();
                Log.e("text","Socket連線="+e.toString());
                finish();    //當斷線時自動關閉房間
            }
        }
    };
}