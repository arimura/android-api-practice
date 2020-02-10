package com.hormiga6.androidapipractice.looper;

import android.os.Bundle;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hormiga6.androidapipractice.R;

public class MyLooperActivity extends AppCompatActivity {

    private MyLooperThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_looper);

        //start looper
        MyLooperThread myLooperThread = new MyLooperThread();
        myLooperThread.start();
        thread = myLooperThread;
    }

    public void send(View view){
        EditText messageView = (EditText)findViewById(R.id.editMessage);
        Message message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("message",messageView.getText().toString());
        message.setData(bundle);
        thread.getHandler().sendMessage(message);
    }
}
