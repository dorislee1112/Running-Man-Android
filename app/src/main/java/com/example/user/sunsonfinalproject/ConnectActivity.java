package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

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
    public int serverPort = EntryActivity.serverPort;
    public  InetAddress serverIp = EntryActivity.serverIp;
    public static Socket clientSocket;
    public static BufferedReader br;
    public static PrintWriter writer;
    public static Thread thread;
    int finish=0;

    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect);
        connectUI=new ConnectUI(this);


        thread=new Thread(Connection);
        thread.start();
        System.out.println("已經連線...");
        /*writer.println("enter");
        writer.flush();*/
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {}
        return true;
    }

    private Runnable Connection=new Runnable() {
        public void run() {
            try{
                // IP為Server端

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
                while(true&&finish==0) {
                    writer.println("enter");
                    writer.flush();
                    try {
                        Log.d("tag", "wait");
                        String line = br.readLine();
                        Log.d("tag", "wait2 " + line);
                        System.out.println("收到的");
                        if (line.equals("start")) {
                            String selectColor = br.readLine();
                            String playerIndex = br.readLine();
                            Log.d("TAG", "get color:" + selectColor + " " + playerIndex);
                            Intent intent = new Intent();
                            intent.setClass(ConnectActivity.this, SelectActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("color", selectColor);
                            bundle.putString("playerIndex", playerIndex);
                            intent.putExtras(bundle);
                            ConnectActivity.this.startActivity(intent);

                        }
                    } catch (IOException e) {}

                    finish=1;
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
