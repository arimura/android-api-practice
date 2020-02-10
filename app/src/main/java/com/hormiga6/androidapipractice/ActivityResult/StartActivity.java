package com.hormiga6.androidapipractice.ActivityResult;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hormiga6.androidapipractice.R;

public class StartActivity extends AppCompatActivity {
    private static final String TAG = "StartActivity";
    private static final int RC = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void clickResult(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivityForResult(intent, RC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, String.format("requestCode: %d, resultCode: %d, data: %s", requestCode, requestCode, data));
    }
}
