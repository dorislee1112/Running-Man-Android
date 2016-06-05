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
    private int serverPort1=EntryActivity.serverPort;
    private Socket clientSocket1=ConnectActivity.clientSocket;
    private BufferedReader br1=ConnectActivity.br;
    private PrintWriter writer1=ConnectActivity.writer;
    public static int play_again=0;
    public int thread_again=0;
    Thread thread;
    protected void onCreate(Bundle savedInstanceState) {
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
         //   thread_again=1;
        //}
    }
        private Runnable Connection=new Runnable() {
            public void run() {
                try {
                    while (true) {

                        //br1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
                        System.out.println("in try");
                        //         writer1.println("in");
                        //        writer1.flush();
                        //String line = ConnectActivity.br.readLine();
                        //System.out.println("xxxxxxxxxxxxxxx" + line);
                        if(play_again==1){
                            writer1.println("again");
                            writer1.flush();
                            play_again=0;
                        }
                        String line=br1.readLine();
                        System.out.println("!-----------------------------------!"+line);
                        if (line.equals("game")) {
                            System.out.println("xxxxxxxxxxxxxxx--iniinininininininin");
                            MainActivity.ctrl=1;
                            Intent intent = new Intent();
                            intent.setClass(WaitActivity.this, MainActivity.class);
                            System.out.println("in game");
                            WaitActivity.this.startActivity(intent);

                        }

                    }
                } catch (IOException e) {
                }
            }
        };


}