package com.hormiga6.androidapipractice.window;

import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.hormiga6.androidapipractice.R;

public class WindowActivity extends AppCompatActivity {

    //see
    // https://qiita.com/tokisuzume/items/466fc9dc471013e4b6dc
    // https://blog.csdn.net/yhaolpz/article/details/68936932
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);

        checkPermission();
    }

    private void checkPermission(){
        if(Build.VERSION.SDK_INT < 23){
            return;
        }
        boolean canDraw = Settings.canDrawOverlays(this);
        if(!canDraw){
            startActivity(
             new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())));

        }

    }
    public void clickShowButton(View view){
        Button floatingButton = new Button(this);
        floatingButton.setText("button");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0,
                0,
                PixelFormat.TRANSPARENT);

        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        layoutParams.type =  Build.VERSION.SDK_INT < 26? WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY : WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        layoutParams.gravity = Gravity.CENTER;
        WindowManager windowManager = getWindowManager();
        windowManager.addView(floatingButton, layoutParams);
    }
}
