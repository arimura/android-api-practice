package com.hormiga6.androidapipractice.build;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hormiga6.androidapipractice.R;

public class BuildActivity extends AppCompatActivity {

    private static final String TAG = BuildActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build);

        Log.d(TAG, "Build.DEVICE :" + Build.DEVICE);
        Log.d(TAG, "Build.HARDWARE :" + Build.HARDWARE);
        Log.d(TAG, "Build.MODEL :" + Build.MODEL);
        Log.d(TAG, "Build.PRODUCT :" + Build.PRODUCT);
    }
}
