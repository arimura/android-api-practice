package com.hormiga6.androidapipractice.Application;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hormiga6.androidapipractice.R;

public class ContextCheckActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_check1);

        // See
        // http://stackoverflow.com/questions/5018545/getapplication-vs-getapplicationcontext
        //https://medium.com/@ali.muzaffar/which-context-should-i-use-in-android-e3133d00772c#.suu8orycn
        Application application = getApplication();
        Context applicationContext = getApplicationContext();
        boolean isSame = application == applicationContext;
        String msg;
        if (isSame){
            msg = "- getApplication() and getApplicationContext() return same obj\n\n";
        }else{
            msg = "- getApplication() and getApplicationContext() return different obj\n\n";
        }

        AlertDialog alert = (new AlertDialog.Builder(this)).create();
        alert.setMessage(msg);
        alert.show();
    }
}
