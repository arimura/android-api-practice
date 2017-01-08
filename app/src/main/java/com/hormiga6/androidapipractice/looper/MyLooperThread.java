package com.hormiga6.androidapipractice.looper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.atomic.AtomicReference;

public class MyLooperThread extends Thread {
    private AtomicReference<Handler> mHandlerReference = new AtomicReference<Handler>();

    public void run() {
        Looper.prepare();

        mHandlerReference.set(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.d("MyLooper", "receive message" + msg.getData().toString());
            }
        });

        Looper.loop();
    }

    public Handler getHandler(){
        return mHandlerReference.get();
    }
}
