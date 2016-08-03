package com.sanpeng.ourproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.sanpeng.ourproject.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hide the title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //等待3000毫秒后销毁此页面，并提示登陆成功
                WelcomeActivity.this.finish();
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, MainActivity.class);//跳转到加载界面
                startActivity(intent);
            }
        }, 3000);
    }
}
