package com.hormiga6.androidapipractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dispatchActivity(View view) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.hormiga6.androidapipractice." + view.getTag().toString());
        Intent intent = new Intent(this,aClass);
        startActivity(intent);
    }
}
