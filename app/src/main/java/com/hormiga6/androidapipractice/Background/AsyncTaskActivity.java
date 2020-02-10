package com.hormiga6.androidapipractice.Background;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hormiga6.androidapipractice.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        //watcher
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            ThreadPoolExecutor exec = (ThreadPoolExecutor) AsyncTask.THREAD_POOL_EXECUTOR;
            Log.d("AsyncTaskActivity",
                    exec.toString());
        }, 0, 1000, TimeUnit.MILLISECONDS);

    }

    public void startAsyncTask(View view){
        for (int i = 0; i < 100; i++) {
            (new MyTask()).execute();
        }
    }

    public void startAsyncTaskWithTpe(View view) {
        for (int i = 0; i < 100; i++) {
            (new MyTask()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    static class MyTask extends AsyncTask<Void,Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
