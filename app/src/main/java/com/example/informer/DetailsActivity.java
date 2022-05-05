package com.example.informer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class DetailsActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        webView= findViewById(R.id.webView);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String url= bundle.getString("url");
            webView.loadUrl(url);
        }
    }
}