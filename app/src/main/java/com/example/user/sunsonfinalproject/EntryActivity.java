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
public class EntryActivity extends Activity {
    EntryUI entryUI;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        entryUI = new EntryUI(this);
        entryUI.start.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(EntryActivity.this, SelectActivity.class);
                EntryActivity.this.startActivity(intent);

            }

        });

    }


}