package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by chanhua on 16/5/31.
 */
public class SecondActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two);
       // Bundle bundle = this.getIntent().getExtras();
       // float num = bundle.getFloat("num");

       /* if(num==1)
            setContentView(R.layout.one);
        else if(num==2)
            setContentView(R.layout.two);
        else if(num==3)
            setContentView(R.layout.three);
        else if(num==4)
            setContentView(R.layout.four);
        */
    }
}


