package com.hormiga6.androidapipractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hormiga6.androidapipractice.ActivityOverlay.BaseActivity;
import com.hormiga6.androidapipractice.ActivityResult.StartActivity;
import com.hormiga6.androidapipractice.Application.ContextCheckActivity1;
import com.hormiga6.androidapipractice.Drawable.DrawableActivity;
import com.hormiga6.androidapipractice.Layout.LinearLayoutActivity;
import com.hormiga6.androidapipractice.ListView.ListViewActivity;
import com.hormiga6.androidapipractice.MultiTypeList.MultiTypeListActivity;
import com.hormiga6.androidapipractice.NavigationDrawer.DrawerActivity;
import com.hormiga6.androidapipractice.ProgressBar.ProgressBarActivity;
import com.hormiga6.androidapipractice.Ripple.RippleActivity;
import com.hormiga6.androidapipractice.Service.ServiceActivity;
import com.hormiga6.androidapipractice.UncaughtException.UncaughtExceptionActivity;
import com.hormiga6.androidapipractice.looper.MyLooperActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, Class> dispatchMap = new HashMap<String, Class>() {{
        put("BaseActivity", BaseActivity.class);
        put("ListViewActivity", ListViewActivity.class);
        put("LinearLayoutActivity", LinearLayoutActivity.class);
        put("RippleActivity", RippleActivity.class);
        put("MultiTypeListActivity", MultiTypeListActivity.class);
        put("DrawableActivity", DrawableActivity.class);
        put("DrawerActivity", DrawerActivity.class);
        put("ProgressBarActivity", ProgressBarActivity.class);
        put("StartActivity",StartActivity.class );
        put("ContextCheckActivity1",ContextCheckActivity1.class );
        put("MyLooperActivity",MyLooperActivity.class );
        put("ServiceActivity",ServiceActivity.class );
        put("UncaughtActivity",UncaughtExceptionActivity.class );
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dispatchActivity(View view) {
        Intent intent = new Intent(this, dispatchMap.get(view.getTag().toString()));
        startActivity(intent);
    }
}
