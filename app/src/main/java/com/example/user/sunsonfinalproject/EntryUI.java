package com.example.user.sunsonfinalproject;

import android.app.Activity;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.inputmethod.EditorInfo;
import android.text.Editable;

/**
 * Created by chanhua on 16/5/12.
 */
public class EntryUI implements TextWatcher{
    public Button start;
    /** IP text variable **/
    public EditText IPAddr0, IPAddr1, IPAddr2, IPAddr3;
    public TextView msg;
    private int curtext;

    public EntryUI(Activity activity){
        start = (Button)activity.findViewById(R.id.start_button);
        IPAddr0 = (EditText) activity.findViewById(R.id.editText0);
        IPAddr1 = (EditText) activity.findViewById(R.id.editText1);
        IPAddr2 = (EditText) activity.findViewById(R.id.editText2);
        IPAddr3 = (EditText) activity.findViewById(R.id.editText3);

        IPAddr0.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        IPAddr1.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        IPAddr2.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        IPAddr3.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        /** Text change animation **/
        IPAddr0.addTextChangedListener(this);
        IPAddr1.addTextChangedListener(this);
        IPAddr2.addTextChangedListener(this);
        IPAddr3.addTextChangedListener(this);

        msg = (TextView) activity.findViewById(R.id.textView3);
    }

    @Override
    public void afterTextChanged(Editable e){
        if(e.length() == 3){
            if(curtext == 0) {
                IPAddr1.requestFocus();
                curtext = 1;
            }
            else if(curtext == 1){
                IPAddr2.requestFocus();
                curtext = 2;
            }
            else if(curtext == 2){
                IPAddr3.requestFocus();
                curtext = 3;
            }
        }
        else if(e.length() == 0){
            if(curtext == 0) {
                IPAddr0.requestFocus();
                curtext = 1;
            }
            else if(curtext == 1){
                IPAddr1.requestFocus();
                curtext = 2;
            }
            else if(curtext == 2){
                IPAddr2.requestFocus();
                curtext = 3;
            }
            else if(curtext == 3){
                IPAddr3.requestFocus();
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count){
    }

}
