package com.hormiga6.androidapipractice.Layout;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.hormiga6.androidapipractice.R;

public class LinearLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        //Position of object is specified by parent's gravity and child's layout_gravity
        //https://developer.android.com/reference/android/widget/LinearLayout.html#attr_android:gravity
        //https://developer.android.com/reference/android/widget/LinearLayout.LayoutParams.html#attr_android:layout_gravity
    }
}
