package com.sanpeng.ourproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sanpeng.ourproject.R;

public class ZoneDetailActivity extends AppCompatActivity {

    String baseUrl = "http://api.jiangwoo.com/api/v1/spaces/91?id=";
    String url ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_detail);

        getData();

    }

    public void getData(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String pos = intent.getStringExtra("pos");
        url = baseUrl + id;
    }
}
