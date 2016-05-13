package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by chanhua on 16/5/12.
 */
public class SelectActivity extends Activity {
    SelectUI selectUI;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        selectUI = new SelectUI(this);
        selectUI.check.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(SelectActivity.this, MainActivity.class);
                        SelectActivity.this.startActivity(intent);
                        Log.d("TAG", "push!");

            }

        });

    }


}
