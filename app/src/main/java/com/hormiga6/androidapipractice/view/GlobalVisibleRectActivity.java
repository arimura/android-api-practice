package com.hormiga6.androidapipractice.view;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hormiga6.androidapipractice.R;

public class GlobalVisibleRectActivity extends AppCompatActivity {
    private static final String TAG = GlobalVisibleRectActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_visible_rect);
    }

    public void checkGlobalVisibleRect(View view){
        print(R.id.view1, "view1");
        print(R.id.view2, "view2");
        print(R.id.view3, "view3");
        print(R.id.view5, "view5");
    }

    private void print(int viewId, String name) {
        View view1 = findViewById(viewId);
        Rect globalVisibleRect = new Rect();
        boolean rtn1 = view1.getGlobalVisibleRect(globalVisibleRect);

        Log.d(TAG,name + " width: " + globalVisibleRect.width() + ", height: " + globalVisibleRect.height());
        Log.d(TAG,name + " left, top - right, bottom " + globalVisibleRect + ", rtn: " +  Boolean.valueOf(rtn1));
    }
}
