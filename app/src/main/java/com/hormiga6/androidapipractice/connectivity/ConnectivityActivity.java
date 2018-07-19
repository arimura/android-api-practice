package com.hormiga6.androidapipractice.connectivity;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hormiga6.androidapipractice.R;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectivityActivity extends AppCompatActivity {

    private static final String TAG = ConnectivityActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectivity);

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            queryHost(connectivityManager);
        }

        checkActiveNetworkType(connectivityManager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkActiveNetworkTypeForMultiTransport(connectivityManager);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkActiveNetworkTypeForMultiTransport(ConnectivityManager connectivityManager) {
        Network network = connectivityManager.getActiveNetwork();
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(
                network);
        Log.d(TAG, "hasTransport WIFI: " + networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
        Log.d(TAG, "hasTransport CELLULAR: " + networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
    }

    private void checkActiveNetworkType(ConnectivityManager connectivityManager) {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Log.d(TAG, "network typename: " + networkInfo.getTypeName());
        /*
         getTypeName() is deprecated. getTypeName() cannot account for networks using multiple transports.
         */
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void queryHost(ConnectivityManager connectivityManager) {
        Network network = connectivityManager.getActiveNetwork();
        new Thread(()->{
            InetAddress ip = null;
            try {
                ip = network.getByName("google.com");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "ip: " + ip);
        }).start();
    }
}
