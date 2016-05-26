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
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by chanhua on 16/5/12.
 */
public class SelectActivity extends Activity {
    SelectUI selectUI;
    public static int serverPort=8000;
    public static InetAddress serverIp;
    public static Socket clientSocket;
    public static BufferedReader br;
    public static PrintWriter writer;
    Thread thread;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        selectUI = new SelectUI(this);

        Log.d("TAG", "還沒連...");
        thread=new Thread(Connection);                //賦予執行緒工作
        thread.start();
        Log.d("TAG", "連了...");

        selectUI.check.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true) {
                    selectUI.check.setImageResource(R.drawable.sign_onclick);
                    Intent intent = new Intent();
                    intent.setClass(SelectActivity.this, WaitActivity.class);
                    SelectActivity.this.startActivity(intent);
                    writer.println("select");
                    writer.flush();
                } else
                    selectUI.check.setImageResource(R.drawable.sign);
            }
        });
        selectUI.up.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true) {
                    selectUI.up.setImageResource(R.drawable.up_onclick);
                    writer.println("up");
                    writer.flush();
                } else
                    selectUI.up.setImageResource(R.drawable.up);
            }
        });
        selectUI.down.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true) {
                    selectUI.down.setImageResource(R.drawable.down_onclick);
                    writer.println("down");
                    writer.flush();
                }
                else
                    selectUI.down.setImageResource(R.drawable.down);
            }
        });
        selectUI.right.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true) {
                    selectUI.right.setImageResource(R.drawable.right_onclick);
                    writer.println("right");
                    writer.flush();
                }
                else
                    selectUI.right.setImageResource(R.drawable.right);
            }
        });
        selectUI.left.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true) {
                    selectUI.left.setImageResource(R.drawable.left_onclick);
                   writer.println("left");
                    writer.flush();
                }
                else
                    selectUI.left.setImageResource(R.drawable.left);
            }
        });

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
                clientSocket.connect(new InetSocketAddress(serverIp,serverPort),10000);
                System.out.println("Socket已經連線");
                //取得網路輸出串流
                writer = new PrintWriter( new OutputStreamWriter(clientSocket.getOutputStream()));
                // 取得網路輸入串流
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            }catch(Exception e){
                //當斷線時會跳到catch,可以在這裡寫上斷開連線後的處理
                e.printStackTrace();
                Log.e("text","Socket連線="+e.toString());
                finish();    //當斷線時自動關閉房間
            }
        }
    };
}
