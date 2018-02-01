package com.hormiga6.androidapipractice.UncaughtException;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hormiga6.androidapipractice.R;

public class UncaughtExceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncaught_exception);
    }

    public void registerIgnoreHandler(View view){
        Thread.setDefaultUncaughtExceptionHandler(new IgnoreExceptionHandler());
    }

    public void tapException(View view){
        Log.d("UncaughtActivity", "tapException: ");
        throw new IgnoredException();
    }


    static class IgnoredException extends RuntimeException {

    }


    static class IgnoreExceptionHandler implements Thread.UncaughtExceptionHandler {
        private Thread.UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            //ignore specified exception
            if(!(e instanceof IgnoredException)){
                Log.d("IgnoreExceptionHandler", "uncaughtException: Ignore exception");
                return;
            }
            defaultHandler.uncaughtException(t, e);
        }
    }
}
