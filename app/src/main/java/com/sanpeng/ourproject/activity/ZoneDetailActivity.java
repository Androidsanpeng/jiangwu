package com.sanpeng.ourproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.sanpeng.ourproject.R;

public class ZoneDetailActivity extends AppCompatActivity {

    String baseUrl = "http://api.jiangwoo.com/api/v1/spaces/91?id=";
    String url ;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView = (TextView) findViewById(R.id.tv);


        setContentView(R.layout.activity_zone_detail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String pos = intent.getStringExtra("pos");
        Log.e("======","=====id====="+id);
        Log.e("======","======pos===="+pos);
        textView.setText(pos);
        url = baseUrl + id;

    }
}
