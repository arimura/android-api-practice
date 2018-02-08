package com.hormiga6.androidapipractice.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hormiga6.androidapipractice.R;
import com.hormiga6.androidapipractice.SQLite.FeedReaderContract.FeedEntry;

import java.util.ArrayList;
import java.util.List;

public class SQLiteActivity extends AppCompatActivity {

    FeedReaderDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        dbHelper = new FeedReaderDbHelper(this);
    }

    public void insert(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE, "Hoge hoge");
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE, "fuga fuga");
        db.insert(FeedEntry.TABLE_NAME, null, values);
    }

    public void read(View view){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                FeedEntry._ID,
                FeedEntry.COLUMN_NAME_TITLE,
                FeedEntry.COLUMN_NAME_SUBTITLE
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = new String[]{"Hoge hoge"};
        String sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(FeedEntry.TABLE_NAME, projection, selection, selectionArgs, null,
                null, sortOrder);
        while (cursor.moveToNext()){
            Log.d("SQLiteActivity", "read: " + cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SUBTITLE)));
        }
        cursor.close();
    }
}
