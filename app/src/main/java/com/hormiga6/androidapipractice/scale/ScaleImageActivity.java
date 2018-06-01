package com.hormiga6.androidapipractice.scale;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.hormiga6.androidapipractice.R;

public class ScaleImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_image);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android_developers);

        setImage(bitmap, R.id.scaleImageView1, ImageView.ScaleType.CENTER_INSIDE);
        setImage(bitmap, R.id.scaleImageView2, ImageView.ScaleType.CENTER_CROP);
        setImage(bitmap, R.id.scaleImageView3, ImageView.ScaleType.FIT_XY);
        setImage(bitmap, R.id.scaleImageView4, ImageView.ScaleType.FIT_END);
    }

    private void setImage(Bitmap bitmap, int id, ImageView.ScaleType scaleType) {
        ImageView imageView = findViewById(id);
        imageView.setScaleType(scaleType);
        imageView.setImageBitmap(bitmap);
    }
}
