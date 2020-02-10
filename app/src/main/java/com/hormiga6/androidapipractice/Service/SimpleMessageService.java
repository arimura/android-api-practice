package com.hormiga6.androidapipractice.Service;

import android.app.IntentService;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

public class SimpleMessageService extends IntentService {
    private static final String TAG = SimpleMessageService.class.getSimpleName();

    public SimpleMessageService() {
        super("SimpleMessageService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.d(TAG, "Broadcasting message");
            Intent localIntent = new Intent("custom-event-name");
            localIntent.putExtra("message","hogehoge");
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
        }
    }
}
