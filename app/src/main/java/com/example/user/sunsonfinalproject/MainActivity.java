package com.example.user.sunsonfinalproject;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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

    int num = 0, begin=0;
    double sleep=0;
    private int serverPort1=EntryActivity.serverPort;
    private Socket clientSocket1=ConnectActivity.clientSocket;
    private BufferedReader br1=ConnectActivity.br;
    private PrintWriter writer1=ConnectActivity.writer;
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
    //    thread=new Thread(Connection);
    //    thread.start();

    }

   /* private Runnable Connection=new Runnable() {
        public void run() {
            while(true) {
                // TODO Auto-generated method st
                try {
                    String line = br1.readLine();
                    Log.d("TAG", "已讀"+line);
                    if (line.equals("one")) {
                        System.out.println("FIRST!!");
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, FirstActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    } else if (line.equals("two")) {
                        System.out.println("SECOND!!");
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, SecondActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    } else if (line.equals("three")) {
                        System.out.println("THIRD!!");
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, ThirdActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    } else if (line.equals("four")) {
                        System.out.println("FORTH!!");
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, ForthActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    }
                    else if (line.equals("sleep")) {
                        sleep = 100000000;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };*/
    private SensorEventListener SensorListener = new SensorEventListener()
    {

        public void onSensorChanged(SensorEvent mSensorEvent)
        {
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
            mSpeed = Math.sqrt(mDeltaX * mDeltaX + mDeltaY * mDeltaY + mDeltaZ * mDeltaZ)/ mTimeInterval * 10000;
            //若體感(Sensor)甩動速度大於等於甩動設定值則進入 (達到甩動力道及速度)
            if (mSpeed >= SPEED_SHRESHOLD)
            {
                //達到搖一搖甩動後要做的事情

                Log.d("TAG", "搖一搖中..."+num);
             /*   if(begin == 1) {
                    tmpThread();
                }
                else{
                    begin = 1;
                }*/

                tmpThread();
                    writer1.println(num);
                    writer1.flush();
                while(sleep > 0 ){
                    sleep-=0.1;
                }
                num++;

            }
        }

        public void onAccuracyChanged(Sensor sensor , int accuracy)
        {
        }
    };
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //在程式關閉時移除體感(Sensor)觸發
        mSensorManager.unregisterListener(SensorListener);
    }

   public void tmpThread(){
       Thread readOneTime = new Thread(new Runnable()  {
           public void run() {
               while(true) {
                   // TODO Auto-generated method st
                   try {
                       String line = br1.readLine();
                       Log.d("TAG", "已讀"+line);
                       if (line.equals("one")) {
                           System.out.println("FIRST!!");
                           Intent intent = new Intent();
                           intent.setClass(MainActivity.this, FirstActivity.class);
                           MainActivity.this.startActivity(intent);
                           break;
                       } else if (line.equals("two")) {
                           System.out.println("SECOND!!");
                           Intent intent = new Intent();
                           intent.setClass(MainActivity.this, SecondActivity.class);
                           MainActivity.this.startActivity(intent);
                           break;
                       } else if (line.equals("three")) {
                           System.out.println("THIRD!!");
                           Intent intent = new Intent();
                           intent.setClass(MainActivity.this, ThirdActivity.class);
                           MainActivity.this.startActivity(intent);
                           break;
                       } else if (line.equals("four")) {
                           System.out.println("FORTH!!");
                           Intent intent = new Intent();
                           intent.setClass(MainActivity.this, ForthActivity.class);
                           MainActivity.this.startActivity(intent);
                           break;
                       }
                       else if (line.equals("sleep")) {
                           sleep = 7500000;
                       }

                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
       });
       readOneTime.start();
   }
/*    public void tmpThread(){
        Thread readOneTime = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String line = br1.readLine();
                    Log.d("TAG", "已讀"+line);
                    if (line.equals("sleep")) {
                        sleep = 100000000;
                    }
                } catch (IOException e) {
                }
            }
        });
        readOneTime.start();
    }
*/

}
