package com.hormiga6.androidapipractice.ActivityOverlay;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import com.hormiga6.androidapipractice.R;

//Theme is "@android:style/Theme.Translucent.NoTitleBar"
//If you use "@android:style/Theme.Translucent.NoTitleBar.Fullscreen", status bar disappear.
//If you want to use AppCompatActivity, create own theme.
//You can' use default "CoordinatorLayout" if parent activity is AppCompatActivity.
public class OverlayActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay);

    }
}
