package com.hormiga6.androidapipractice.MemoryLeak;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hormiga6.androidapipractice.R;

import java.util.concurrent.atomic.AtomicInteger;

public class ParentActivity extends AppCompatActivity {

    static volatile int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        cnt = 0;
    }

    @Override
    protected void onStart(){
        super.onStart();

        if (cnt < 20) {

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    cnt++;
                    Intent intent = new Intent(ParentActivity.this, ChildActivity.class);
                    startActivity(intent);
                }
            }, 1000);
            for(int i=0, l = ChildActivity.weakList.size();i < l ;i++){
                Activity activity = ChildActivity.weakList.get(i).get();
                Log.d("ChildActivity life", "#" + i + ": " + (activity == null ? "released" : "exists"));
            }
        }
    }

    public void clickNext(View view){
        Intent intent = new Intent(ParentActivity.this, ChildActivity.class);
        startActivity(intent);
    }
}
