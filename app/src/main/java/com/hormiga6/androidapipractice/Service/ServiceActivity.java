package com.hormiga6.androidapipractice.Service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.hormiga6.androidapipractice.R;

import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentServices(intent, PackageManager.MATCH_ALL);
        for(ResolveInfo info :resolveInfoList){
            Log.d("view_service", info.toString());
        }

        ResolveInfo info = getPackageManager().resolveService(intent, PackageManager.MATCH_ALL);
        if(info != null){
            Log.d("view_service", info.toString());
        } else {
            Log.d("view_service", "not available");
        }
    }

    private static boolean isChromeCustomTabsSupported(@NonNull final Context context) {
        Intent serviceIntent = new Intent("android.support.customtabs.action.CustomTabsService");
        serviceIntent.setPackage("com.android.chrome");
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentServices(serviceIntent, 0);
        return !(resolveInfos == null || resolveInfos.isEmpty());
    }
}
