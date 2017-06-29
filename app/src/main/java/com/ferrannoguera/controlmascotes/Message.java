package com.ferrannoguera.controlmascotes;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ferran.noguera on 6/1/16.
 */
public class Message {

    public static void message(Context context, String message){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }
}
