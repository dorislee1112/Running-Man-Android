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

/**
 * Created by chanhua on 16/5/12.
 */
public class SelectActivity extends Activity {
    SelectUI selectUI;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        selectUI = new SelectUI(this);

        selectUI.check.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true)
                    selectUI.check.setImageResource(R.drawable.sign_onclick);
                else
                    selectUI.check.setImageResource(R.drawable.sign);
            }
        });
        selectUI.up.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true)
                    selectUI.check.setImageResource(R.drawable.up_onclick);
                else
                    selectUI.check.setImageResource(R.drawable.up);
            }
        });
        selectUI.down.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true)
                    selectUI.check.setImageResource(R.drawable.down_onclick);
                else
                    selectUI.check.setImageResource(R.drawable.down);
            }
        });
        selectUI.right.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true)
                    selectUI.check.setImageResource(R.drawable.right_onclick);
                else
                    selectUI.check.setImageResource(R.drawable.right);
            }
        });
        selectUI.left.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused == true)
                    selectUI.check.setImageResource(R.drawable.left_onclick);
                else
                    selectUI.check.setImageResource(R.drawable.left);
            }
        });
        selectUI.check.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectUI.check.setImageResource(R.drawable.sign_onclick);
                        Intent intent = new Intent();
                        intent.setClass(SelectActivity.this, WaitActivity.class);
                        SelectActivity.this.startActivity(intent);

            }

        });
        /*selectUI.up.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUI.up.setImageResource(R.drawable.up_onclick);
            }

        });
        selectUI.down.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUI.down.setImageResource(R.drawable.down_onclick);
            }

        });
        selectUI.left.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUI.left.setImageResource(R.drawable.left_onclick);
            }

        });
        selectUI.right.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUI.right.setImageResource(R.drawable.right_onclick);
            }

        });*/
    }


}
