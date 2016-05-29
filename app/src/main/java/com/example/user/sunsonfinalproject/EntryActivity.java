package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by chanhua on 16/5/12.
 */
public class EntryActivity extends Activity {
    EntryUI entryUI;
    private String addr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

        entryUI = new EntryUI(this);
        entryUI.start.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IPCheck()) {
                    // User input IP address
                    addr = entryUI.IPAddr0.getText().toString() + "." + entryUI.IPAddr1.getText().toString()
                            + "." + entryUI.IPAddr2.getText().toString() + "." + entryUI.IPAddr3.getText().toString();

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("IP", addr);
                    intent.putExtras(bundle);
                    intent.setClass(EntryActivity.this, ConnectActivity.class);
                    EntryActivity.this.startActivity(intent);
                }
                else {
                    entryUI.msg.setTextColor(Color.rgb(255,0,0)); //red
                }
            }

        });

    }

    public boolean IPCheck(){
        if(entryUI.IPAddr0.length() == 0 || entryUI.IPAddr1.length() == 0 ||
                entryUI.IPAddr2.length() == 0 || entryUI.IPAddr3.length() == 0){
            return false;
        }
        return true;
    }



}
