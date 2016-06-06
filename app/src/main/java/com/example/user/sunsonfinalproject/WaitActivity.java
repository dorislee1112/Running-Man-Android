package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by user on 2016/5/15.
 */
public class WaitActivity extends Activity {
    WaitUI waitUI;
    private int serverPort1=EntryActivity.serverPort;
    private Socket clientSocket1=ConnectActivity.clientSocket;
    private BufferedReader br2=ConnectActivity.br;
    private PrintWriter writer1=ConnectActivity.writer;
    public static int play_again=0;
    public int thread_again=0;
    private int game=0;
    Thread thread;
    Handler handler;
    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting);
        waitUI = new WaitUI(this);

        System.out.println("before writer");
        // writer1.println("out");
        // writer1.flush();
        System.out.println("after writer");
        //if(thread_again==0) {
        thread = new Thread(Connection);
        thread.start();
        Log.d("Tag", "in Wait after jump");
        if(MainActivity.thread != null){
            MainActivity.thread.interrupt();
            MainActivity.thread = null;
            Log.d("Tag", "in Wait thread is null");
        }
    }

    protected void onStop(){
        super.onStop();
        if(thread != null){
            thread.interrupt();
            thread = null;
        }
    }
        private Runnable Connection=new Runnable() {
            public void run() {
                try {
                        Log.d("Tag", "in runnable: " + play_again);
                        if(play_again==1){
                            writer1.println("again");
                            writer1.flush();
                            play_again=0;
                            Log.d("Tag", "in Wait: again");
                        }
                        Log.d("Tag", "in Wait: again2");
                        String line=br2.readLine();
                        Log.d("Tag", "in Wait: get " + line);
                        System.out.println("in wait:!-----------------------------------!"+line);
                        if (line.equals("game")) {
                            System.out.println("xxxxxxxxxxxxxxx--iniinininininininin");
                            MainActivity.ctrl = 1;
                            Intent intent = new Intent();
                            intent.setClass(WaitActivity.this, MainActivity.class);
                            WaitActivity.this.startActivity(intent);
                            game = 1;
                            finish();
                            Log.d("Tag", "in Wait: in game ");

                        }
                } catch (IOException e) {
                }
            }
        };


}