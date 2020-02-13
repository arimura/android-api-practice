package com.hormiga6.androidapipractice.WebView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hormiga6.androidapipractice.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                Log.d("WBA shouldOverrideUrl 1", url);
                return true;
            }
            @Override
            public boolean shouldOverrideUrlLoading (WebView view,
                                                     WebResourceRequest request){
                Log.d("WBA shouldOverrideUrl 2", request.toString());
                return true;
            }
        });
        webView.setDownloadListener((url, userAgent, contentDisposition, mimetype, contentLength) -> Log.d("WBA onDownloadStart", url));
        webView.setFindListener((i, i1, b) -> Log.d("WBA onFindResult", i+""));
        webView.loadUrl("http://192.168.116.37:8080/wk.html");
    }
}
