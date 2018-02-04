package com.hormiga6.androidapipractice.MemoryLeak;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hormiga6.androidapipractice.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ChildActivity extends AppCompatActivity {

    public static ArrayList<WeakReference<Activity>> weakList = new ArrayList<>();
    public static ArrayList<Activity> strongList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },1000);
        weakList.add(new WeakReference<Activity>(this));
        strongList.add(this);
    }
}
