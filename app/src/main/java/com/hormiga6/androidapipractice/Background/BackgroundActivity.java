package com.hormiga6.androidapipractice.Background;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hormiga6.androidapipractice.R;

public class BackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        makeAsyncTask();
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("BackgroundTest", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("BackgroundTest", "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("BackgroundTest", "onPause: ");
    }

    private static void makeAsyncTask(){
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Log.d("BackgroundTest", "doInBackground: ");
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                makeAsyncTask();
            }

        };
        asyncTask.execute();
    }
}
