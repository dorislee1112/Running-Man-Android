package com.example.user.sunsonfinalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;   //體感(Sensor)使用管理
    private Sensor mSensor;                 //體感(Sensor)類別
    private float mLastX;                    //x軸體感(Sensor)偏移
    private float mLastY;                    //y軸體感(Sensor)偏移
    private float mLastZ;                    //z軸體感(Sensor)偏移
    private double mSpeed;                 //甩動力道數度
    private long mLastUpdateTime;           //觸發時間
    int num = 0;
   /* private InetAddress serverIp = EntryActivity.serverIp;
    private Socket clientSocket = EntryActivity.clientSocket;
    private BufferedReader br =EntryActivity.br;
    private int serverPort =EntryActivity.serverPort;
    */
   public static InetAddress serverIp;
    public static int serverPort=8888;
    public static Socket clientSocket;
    public static BufferedReader br;
    public static BufferedWriter bw;
    //甩動力道數度設定值 (數值越大需甩動越大力，數值越小輕輕甩動即會觸發)
    private static final int SPEED_SHRESHOLD = 3000;

    //觸發間隔時間
    private static final int UPTATE_INTERVAL_TIME = 70;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread=new Thread(Connection);                //賦予執行緒工作
        thread.start();
        // 嘗試連接Server
       /* try {
            // 設定IP
            //serverIp = InetAddress.getByName("140.114.123.209");
            // 初始socket連接
            clientSocket=new Socket("140.114.123.209",8888);
            // 接收來自Server的訊息
            br=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            // 關閉連線
            clientSocket.close();
        } catch (IOException e) {
            // 出錯後顯示錯誤訊息
            System.out.println( "Connect error.");
        }*/

        //取得體感(Sensor)服務使用權限
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        //取得手機Sensor狀態設定
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //註冊體感(Sensor)甩動觸發Listener
        mSensorManager.registerListener(SensorListener, mSensor, SensorManager.SENSOR_DELAY_GAME);

    }


    private Runnable Connection=new Runnable() {
        public void run() {
            // TODO Auto-generated method stub
            try{
                // IP為Server端
                InetAddress serverIp = InetAddress.getByName("140.114.123.209");
                Socket clientSocket = new Socket(serverIp, serverPort);
                //取得網路輸出串流
                bw = new BufferedWriter( new OutputStreamWriter(clientSocket.getOutputStream()));
                // 取得網路輸入串流
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // 當連線後
                /*while (clientSocket.isConnected()) {
                    // 取得網路訊息
                    String tmp = br.readLine();    //宣告一個緩衝,從br串流讀取值
                    // 如果不是空訊息
                    if(tmp!=null){
                        //將取到的String抓取{}範圍資料
                        tmp=tmp.substring(tmp.indexOf("{"), tmp.lastIndexOf("}") + 1);
                        json_read=new JSONObject(tmp);
                        //從java伺服器取得值後做拆解,可使用switch做不同動作的處理
                    }
                }*/
            }catch(Exception e){
                //當斷線時會跳到catch,可以在這裡寫上斷開連線後的處理
                e.printStackTrace();
                Log.e("text","Socket連線="+e.toString());
                finish();    //當斷線時自動關閉房間
            }
        }
    };
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
                try {
                    bw.write(num);
                    bw.flush();
                }catch (IOException e){}
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

}
