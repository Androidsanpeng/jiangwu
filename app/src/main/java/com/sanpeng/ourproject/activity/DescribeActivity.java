package com.sanpeng.ourproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.sanpeng.ourproject.R;

public class DescribeActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describe);

        webView = (WebView) findViewById(R.id.des_web_view);

        webView.loadUrl("http://www.jiangwoo.com/jiangwoo_space.html");

    }
}
