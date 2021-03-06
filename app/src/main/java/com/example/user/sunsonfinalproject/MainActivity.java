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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private final int stop=1,run=0;
    MainUI mainUI;
    public SensorManager mSensorManager;   //體感(Sensor)使用管理
    private Sensor mSensor;                 //體感(Sensor)類別
    private float mLastX;                    //x軸體感(Sensor)偏移
    private float mLastY;                    //y軸體感(Sensor)偏移
    private float mLastZ;                    //z軸體感(Sensor)偏移
    private double mSpeed;                 //甩動力道數度
    private long mLastUpdateTime;           //觸發時間
    public static int ctrl=1;


    int bomb_left = 2;

    Random rand;
    String oper;
    int num1,num2,operindex;
    float ans;
    int num = 0;
    int pause=0;

    private BufferedReader br1 = ConnectActivity.br;
    private PrintWriter writer1 = ConnectActivity.writer;
    public static readOneTime thread;

    //甩動力道數度設定值 (數值越大需甩動越大力，數值越小輕輕甩動即會觸發)
    private static final int SPEED_SHRESHOLD = 500;

    //觸發間隔時間
    private static final int UPTATE_INTERVAL_TIME = 70;


    private boolean end=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        thread = new readOneTime();
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainUI = new MainUI(this);
        mainUI.bg.setVisibility(View.VISIBLE);
        mainUI.bomb.setVisibility(View.VISIBLE);
        mainUI.shake.setVisibility(View.VISIBLE);
        mainUI.math.setVisibility(View.INVISIBLE);
        mainUI.send.setVisibility(View.INVISIBLE);
        mainUI.ans.setVisibility(View.INVISIBLE);
        mainUI.math_bg.setVisibility(View.INVISIBLE);
        mainUI.bomb_num.setText("x" + bomb_left);

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


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {}
        return true;
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

                    if(end) {
                        thread = new readOneTime();
                        thread.start();

                    if (pause == 0) {
                        num++;
                    }
                        Log.d("Tag", "thread is null");
                    }
                    else{
                        Log.d("Tag", "thread is not null");
                    }

                }
//            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在程式關閉時移除體感(Sensor)觸發
        mSensorManager.unregisterListener(SensorListener);
        thread = null;
        Log.d("Tag", "on destroy");
    }


    class readOneTime extends Thread{
        public void run() {

            try {
                end = false;
                    Log.d("TAG", "in Main ctrl: " + ctrl);
                    if (ctrl == 0) {
                        Log.d("TAG", "in Main return");
                        return;
                    }
                    writer1.println(num);
                    writer1.flush();
                    String line = br1.readLine();
                    Log.d("TAG", "in main已讀" + line);
                    if (line.equals("one")) {
                        Log.d("Tag", "in one");
                        end = false;
//                        Drawable.setCallback(null);
                        mSensorManager.unregisterListener(SensorListener);
                        Log.d("Tag", "in main unregister");
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, FirstActivity.class);
                        MainActivity.this.startActivity(intent);
                        ctrl = 0;
                        Thread.currentThread().interrupt();
                        thread = null;
                        Log.d("Tag", "in main finish");

                    } else if (line.equals("two")) {
                        Log.d("Tag", "in two");
                        end = false;
                        mSensorManager.unregisterListener(SensorListener);
                        Log.d("Tag", "in main unregister");
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, SecondActivity.class);
                        MainActivity.this.startActivity(intent);
                        ctrl = 0;
                        Thread.currentThread().interrupt();
                        thread = null;
                        Log.d("Tag", "in main finish");

                    } else if (line.equals("three")) {
                        Log.d("Tag", "in one");
                        end = false;
                        mSensorManager.unregisterListener(SensorListener);
                        Log.d("Tag", "in main unregister");
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, ThirdActivity.class);
                        MainActivity.this.startActivity(intent);
                        ctrl = 0;
                        Thread.currentThread().interrupt();
                        thread = null;
                        Log.d("Tag", "in main finish");

                    } else if (line.equals("four")) {
                        Log.d("Tag", "in one");
                        end = false;
                        mSensorManager.unregisterListener(SensorListener);
                        Log.d("Tag", "in main unregister");
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, ForthActivity.class);
                        MainActivity.this.startActivity(intent);
                        ctrl = 0;
                        Thread.currentThread().interrupt();
                        thread = null;
                        finish();
                        Log.d("Tag", "in main finish");
                    }
                    //sleep
                    else if (line.equals("sleep")) {
                        System.out.println("in sleep");
                        rand = new Random();
                        operindex = rand.nextInt(4);
                        if (operindex == 0) {
                            oper = "+";
                            num1 = rand.nextInt(200) + 200;
                            num2 = rand.nextInt(200) + 200;
                            ans = num1 + num2;
                        } else if (operindex == 1) {
                            oper = "-";
                            num1 = rand.nextInt(100) + 100;
                            num2 = rand.nextInt(50) + 50;
                            ans = num1 - num2;
                        } else if (operindex == 2) {
                            oper = "*";
                            num1 = rand.nextInt(20);
                            num2 = rand.nextInt(20);
                            ans = num1 * num2;
                        } else {
                            oper = "/";
                            num2 = rand.nextInt(100);
                            num1 = num2 * rand.nextInt(10);

                            ans = num1 / num2;
                        }
                        pause = 1;

                        Message msg = new Message();
                        msg.what = stop;
                        uiMessageHandler.sendMessage(msg);
                        while (true) {
                            mainUI.send.setOnClickListener(new Button.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Float.parseFloat(mainUI.ans.getText().toString()) == ans) {
                                        mainUI.ans.setText("");
                                        pause = 0;
                                    } else {
                                        mainUI.ans.setText("");
                                        mainUI.send.setText("Try Again");
                                        pause = 1;
                                    }
                                }

                            });
                            if (pause == 0) {
                                msg = new Message();
                                msg.what = run;
                                uiMessageHandler.sendMessage(msg);
                                break;
                            }
                        }

                    }
                    //success
                    else if (line.equals("success")) {
                        System.out.println("-----------------in success-----------------------");
                        bomb_left--;
                        System.out.println(bomb_left);
                    }

                    Message msg = new Message();
                    bombMessageHandler.sendMessage(msg);
                    end = true;
                    Log.d("end", "in main end run");
                }catch(Exception e){
                    e.printStackTrace();
                }

        }
    }



Handler uiMessageHandler = new Handler(){
    @Override
    public void handleMessage(Message msg){
        //讀出ui.xml中的進度光棒
        //final ImageView bg = (ImageView)findViewById(R.id.imageView1);
       // mainUI.bg.setVisibility(View.VISIBLE); //開始後設為可見


        switch (msg.what){
            case stop:
                mainUI.bg.setVisibility(View.INVISIBLE);
                mainUI.math_bg.setVisibility(View.VISIBLE);
                mainUI.bomb.setVisibility(View.INVISIBLE);
                mainUI.shake.setVisibility(View.INVISIBLE);
                mainUI.ans.setVisibility(View.VISIBLE);
                mainUI.math.setVisibility(View.VISIBLE);
                mainUI.send.setVisibility(View.VISIBLE);
                mainUI.bomb_num.setVisibility(View.INVISIBLE);
                mainUI.math.setText(num1 +" "+ oper +" "+ num2 + " = ? ");
                break;
            case run:
                mainUI.bg.setVisibility(View.VISIBLE); //開始後設為可見
                mainUI.math_bg.setVisibility(View.INVISIBLE);
                mainUI.ans.setVisibility(View.INVISIBLE);
                mainUI.math.setVisibility(View.INVISIBLE);
                mainUI.bomb.setVisibility(View.VISIBLE);
                mainUI.shake.setVisibility(View.VISIBLE);
                mainUI.send.setVisibility(View.INVISIBLE);
                mainUI.bomb_num.setVisibility(View.VISIBLE);
                break;
        }

        super.handleMessage(msg);
    }
};

    Handler bombMessageHandler = new Handler() {
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
