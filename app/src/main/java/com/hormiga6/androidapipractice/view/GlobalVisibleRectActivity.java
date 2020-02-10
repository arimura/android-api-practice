package com.hormiga6.androidapipractice.view;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

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
        printDisplaySpec();
        printIntersect(R.id.view1,R.id.view3);
        printIntersect(R.id.view1,R.id.view2);
    }

    private void print(int viewId, String name) {
        View view = findViewById(viewId);
        Rect globalVisibleRect = new Rect();
        boolean rtn1 = view.getGlobalVisibleRect(globalVisibleRect);

        Log.d(TAG,name + " width: " + globalVisibleRect.width() + ", height: " + globalVisibleRect.height());
        Log.d(TAG,name + " left, top - right, bottom " + globalVisibleRect + ", rtn: " +  Boolean.valueOf(rtn1));
    }

    private void printIntersect(int viewId1, int viewId2){
        Rect rect1 = new Rect();
        findViewById(viewId1).getGlobalVisibleRect(rect1);
        Rect rect2 = new Rect();
        findViewById(viewId2).getGlobalVisibleRect(rect2);
        Rect intersectRect = new Rect();
        boolean isIntersect = intersectRect.setIntersect(rect1, rect2);
        if(isIntersect) {
            Log.d(TAG, "intersect: " + intersectRect);
        }else {
            Log.d(TAG, "no intersect");
        }
    }

    private void printDisplaySpec(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        Log.d(TAG, "default display point:" + point);

        Rect rect = new Rect();
        defaultDisplay.getRectSize(rect);
        Log.d(TAG, "default display rect: " + rect);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        Log.d(TAG, "display metrics xdpi" + displayMetrics.xdpi);
        Log.d(TAG, "display metrics w-pixel" + displayMetrics.widthPixels);
        Log.d(TAG, "display metrics density" + displayMetrics.density);
    }
}
