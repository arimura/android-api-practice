package com.hormiga6.androidapipractice.disklrucache;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.hormiga6.androidapipractice.R;
import com.jakewharton.disklrucache.DiskLruCache;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DiskLruCacheActivity extends AppCompatActivity {
    public static final String TAG = DiskLruCacheActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DiskLruCache mDiskLruCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disk_lru_cache);

        File cacheDir = new File(getCacheDir() + File.separator + "lru-cache");
        if(!cacheDir.exists()){
            cacheDir.mkdir();
        }

        try {
            mDiskLruCache = DiskLruCache.open(cacheDir,1,1,10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addImage(View view)  {
        DiskLruCache.Editor editor = null;
        InputStream inputStream = null;
        try {
            EditText imgNum = findViewById(R.id.editTextImgNum);
            editor = mDiskLruCache.edit("image" + imgNum.getText().toString());
            OutputStream outputStream = editor.newOutputStream(0);
            inputStream = getAssets().open("beefly1.jpg");
            IOUtils.copy(inputStream, outputStream);
            editor.commit();
        } catch (IOException e) {
            if (editor != null) {
                try {
                    editor.abort();
                } catch (IOException e1) {
                    //ignore
                }
            }
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public void getImage(View v) {
        DiskLruCache.Snapshot snapshot;
        try {
            EditText imgNum = findViewById(R.id.editTextImgNum);
            snapshot = mDiskLruCache.get("image" + imgNum.getText().toString());
            InputStream inputStream = snapshot.getInputStream(0);
            int r = inputStream.read();
            if(r > 0){
                Log.d(TAG, "read byte: " + r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
