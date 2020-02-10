package com.hormiga6.androidapipractice.Layout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hormiga6.androidapipractice.R;

public class ViewAspectRatioActivity extends AppCompatActivity {
    public static final String TAG = ViewAspectRatioActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_aspect_ratio);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android_developers);
        int bitmapHeight = bitmap.getHeight();
        int bitmapWidth = bitmap.getWidth();
        float aspectRatio = (float) bitmapWidth / bitmapHeight;

        ImageView imageViewWithKeepingAspectRatio = new androidx.appcompat.widget.AppCompatImageView(this) {
            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int width = MeasureSpec.getSize(widthMeasureSpec);
                int height = MeasureSpec.getSize(heightMeasureSpec);
                float viewAspectRatio = (float) width / height;

                int newHeight;
                int newWidth;
                if(viewAspectRatio > aspectRatio ){
                    newHeight = height;
                    newWidth = (int) (newHeight * aspectRatio);
                }else {
                    newWidth = width;
                    newHeight = (int)(newWidth / aspectRatio);
                }

                setMeasuredDimension(newWidth, newHeight);
            }
        };
        imageViewWithKeepingAspectRatio.setImageBitmap(bitmap);
        imageViewWithKeepingAspectRatio.setBackgroundColor(Color.RED);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_RIGHT, R.id.imageViewOriginalAspect);
        layoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.imageViewOriginalAspect);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.imageViewOriginalAspect);

        RelativeLayout layout = findViewById(R.id.layout);
        layout.addView(imageViewWithKeepingAspectRatio, layoutParams);
    }
}
