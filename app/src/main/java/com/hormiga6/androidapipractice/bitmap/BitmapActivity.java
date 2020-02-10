package com.hormiga6.androidapipractice.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.hormiga6.androidapipractice.R;

public class BitmapActivity extends AppCompatActivity {
    private static final String TAG = BitmapActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        ImageView imageView1 = findViewById(R.id.bitmapImageView1);
        imageView1.getViewTreeObserver().addOnGlobalLayoutListener(()->{
            imageView1.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.beefly, imageView1.getWidth(), imageView1.getHeight()));
        });
        ImageView imageView2 = findViewById(R.id.bitmapImageView2);
        imageView2.getViewTreeObserver().addOnGlobalLayoutListener(()->{
            imageView2.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.beefly, imageView2.getWidth() * 3, imageView2.getHeight() * 3));
        });
        ImageView imageView3 = findViewById(R.id.bitmapImageView3);
        imageView3.getViewTreeObserver().addOnGlobalLayoutListener(()->{
            imageView3.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.beefly, imageView3.getWidth() / 10, imageView3.getHeight() / 10));
        });
    }

    private Bitmap readBitmapInformation(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beefly, options);
        Log.d(TAG, "h:" + options.outHeight + ", w:" + options.outHeight + ", type:" + options.outMimeType);
        return bitmap;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        int height = options.outHeight;
        int widht = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight || widht > reqWidth){
            int halfHeight = height / 2;
            int halfWidth = widht / 2;

            while((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth){
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
