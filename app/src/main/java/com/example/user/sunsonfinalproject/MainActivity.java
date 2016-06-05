package com.example.user.sunsonfinalproject;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    MainUI mainUI;
    private SensorManager mSensorManager;   //體感(Sensor)使用管理
    private Sensor mSensor;                 //體感(Sensor)類別
    private float mLastX;                    //x軸體感(Sensor)偏移
    private float mLastY;                    //y軸體感(Sensor)偏移
    private float mLastZ;                    //z軸體感(Sensor)偏移
    private double mSpeed;                 //甩動力道數度
    private long mLastUpdateTime;           //觸發時間
    public static int ctrl=0;

    int bomb_left = 2;
    int play_tag=0;
    int num = 0, begin = 0;
    double sleep = 0;
    private int serverPort1 = EntryActivity.serverPort;
    private Socket clientSocket1 = ConnectActivity.clientSocket;
    private BufferedReader br1 = ConnectActivity.br;
    private PrintWriter writer1 = ConnectActivity.writer;
    Thread thread;

    //甩動力道數度設定值 (數值越大需甩動越大力，數值越小輕輕甩動即會觸發)
    private static final int SPEED_SHRESHOLD = 500;

    //觸發間隔時間
    private static final int UPTATE_INTERVAL_TIME = 70;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainUI = new MainUI(this);

        mainUI.bomb_num.setText("x" + bomb_left);
        //   thread=new Thread(Connection);                //賦予執行緒工作
        //   thread.start();

        //取得體感(Sensor)服務使用權限
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        //取得手機Sensor狀態設定
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //註冊體感(Sensor)甩動觸發Listener
        mSensorManager.registerListener(SensorListener, mSensor, SensorManager.SENSOR_DELAY_GAME);
        mainUI = new MainUI(this);
        mainUI.bomb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mainUI.bomb.setImageResource(R.drawable.bomb_onclick);
                    writer1.println("bomb");
                    writer1.flush();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    mainUI.bomb.setImageResource(R.drawable.bomb);
                }
                //Handle selected state change
                return true;
            }
        });
    }

    private SensorEventListener SensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent mSensorEvent) {
            //當前觸發時間
            long mCurrentUpdateTime = System.currentTimeMillis();

            //觸發間隔時間 = 當前觸發時間 - 上次觸發時間
            long mTimeInterval = mCurrentUpdateTime - mLastUpdateTime;

            //若觸發間隔時間< 70 則return;
            if (mTimeInterval < UPTATE_INTERVAL_TIME) return;

            mLastUpdateTime = mCurrentUpdateTime;

            //取得xyz體感(Sensor)偏移
            float x = mSensorEvent.values[0];
            float y = mSensorEvent.values[1];
            float z = mSensorEvent.values[2];

            //甩動偏移速度 = xyz體感(Sensor)偏移 - 上次xyz體感(Sensor)偏移
            float mDeltaX = x - mLastX;
            float mDeltaY = y - mLastY;
            float mDeltaZ = z - mLastZ;

            mLastX = x;
            mLastY = y;
            mLastZ = z;

            //體感(Sensor)甩動力道速度公式
            mSpeed = Math.sqrt(mDeltaX * mDeltaX + mDeltaY * mDeltaY + mDeltaZ * mDeltaZ) / mTimeInterval * 10000;
            //若體感(Sensor)甩動速度大於等於甩動設定值則進入 (達到甩動力道及速度)
            if (mSpeed >= SPEED_SHRESHOLD) {
                //達到搖一搖甩動後要做的事情

                Log.d("TAG", "搖一搖中..." + num);

                tmpThread();
                writer1.println(num);
                writer1.flush();
                while (sleep > 0) {
                    sleep -= 0.1;
                }
                num++;

            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在程式關閉時移除體感(Sensor)觸發
        mSensorManager.unregisterListener(SensorListener);
    }

    public void tmpThread() {
        Thread readOneTime = new Thread(new Runnable() {
            public void run() {
                while (ctrl==1) {
                    // TODO Auto-generated method st
                    try {
                        String line = br1.readLine();
                        Log.d("TAG", "已讀" + line);
                        System.out.println(line);
                        if (line.equals("one")) {
                            ctrl=0;
                            System.out.println("FIRST!!");
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, FirstActivity.class);
                            MainActivity.this.startActivity(intent);
                            play_tag=1;
                            break;
                        } else if (line.equals("two")) {
                            ctrl=0;
                            System.out.println("SECOND!!");
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, SecondActivity.class);
                            MainActivity.this.startActivity(intent);
                            play_tag=1;
                            break;
                        } else if (line.equals("three")) {
                            ctrl=0;
                            System.out.println("THIRD!!");
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, ThirdActivity.class);
                            MainActivity.this.startActivity(intent);
                            play_tag=1;
                            break;
                        } else if (line.equals("four")) {
                            ctrl=0;
                            System.out.println("FORTH!!");
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, ForthActivity.class);
                            MainActivity.this.startActivity(intent);
                            play_tag=1;
                            break;
                        } else if (line.equals("sleep")) {
                            sleep = 8000000;
                        } else if (line.equals("success")) {
                            System.out.println("-----------------in success-----------------------");
                            bomb_left--;
                            System.out.println(bomb_left);
                        }

                       // Thread.sleep(100);
                        Message msg = new Message();
                        uiMessageHandler.sendMessage(msg);

                        System.out.println("!!!!!!!!!!control: "+ctrl);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //bomb_num.setText("x"+bomb_left);
                    //System.out.println("bombleft: "+bomb_left);
                }
            }
        });
        readOneTime.start();
    }

    Handler uiMessageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //讀出ui.xml中的描述用TextView
            TextView bomb_num = (TextView) findViewById(R.id.bomb_num);
            bomb_num.setText("x" + bomb_left);
            System.out.println("bombleft: " + bomb_left);
            super.handleMessage(msg);
        }
    };
}
