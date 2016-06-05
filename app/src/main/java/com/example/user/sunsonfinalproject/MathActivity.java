package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.AccessibleObject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by chanhua on 16/6/2.
 */
public class MathActivity extends Activity {
    MathUI mathUI;
    Random rand;
    String oper;
    int num1,num2,operindex;
    float ans,user_ans;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math);
        mathUI = new MathUI(this);
        rand = new Random();
        operindex = rand.nextInt(4);
        if (operindex == 0) {
            oper = "+";
            num1 = rand.nextInt(500) + 200;
            num2 = rand.nextInt(500) + 200;
            ans = num1 + num2;
        } else if (operindex == 1) {
            oper = "-";
            num1 = rand.nextInt(500) + 200;
            num2 = rand.nextInt(500) + 100;
            ans = num1 - num2;
        } else if (operindex == 2) {
            oper = "*";
            num1 = rand.nextInt(100);
            num2 = rand.nextInt(100);
            ans = num1 * num2;
        } else if (operindex == 3) {
            oper = "/";
            num1 = rand.nextInt(50);
            num2 = num1 * rand.nextInt(50);
            ans = num2 / num1;
        }


        mathUI.send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_ans = Float.parseFloat(mathUI.user_ans.getText().toString());
                if (user_ans == ans){
                    Intent intent = new Intent();
                    intent.setClass(MathActivity.this, MainActivity.class);
                    MathActivity.this.startActivity(intent);
                }
                else{
                    mathUI.user_ans.setText("");
                    mathUI.text.setText("Wrong! Try again");
                }


            }

        });
    }

}
