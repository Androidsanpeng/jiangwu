package com.sanpeng.ourproject.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sanpeng.ourproject.R;
import com.sanpeng.ourproject.fragment.HomeFragment;
import com.sanpeng.ourproject.fragment.MineFragment;
import com.sanpeng.ourproject.fragment.ZoneFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    ZoneFragment zoneFragment;
    MineFragment mineFragment;
    Toolbar toolbar;
    RadioGroup radioGroup;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.main_radio_group);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle("匠铺");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        addContainer();
        radioGRoupgClick();
    }

    //添加fragment到容器中
    private void addContainer() {
        homeFragment = new HomeFragment();
        zoneFragment = new ZoneFragment();
        mineFragment = new MineFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.fragment_container, zoneFragment)
                .add(R.id.fragment_container, mineFragment)
                .add(R.id.fragment_container, homeFragment)
                .addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //radiogroup点击切换fragment
    private void radioGRoupgClick() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.home_rb:
                        toolbar.setTitle("匠铺");
                        ft.show(homeFragment).hide(zoneFragment).hide(mineFragment);
                        break;
                    case R.id.zone_rb:
                        toolbar.setTitle("空间");
                        ft.show(zoneFragment).hide(homeFragment).hide(mineFragment);
                        break;
                    case R.id.mine_rb:
                        toolbar.setTitle("我的");
                        ft.show(mineFragment).hide(homeFragment).hide(zoneFragment);
                        break;
                }
                ft.commit();
                toolbar.setTitleTextColor(Color.WHITE);
                setSupportActionBar(toolbar);
                toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.scan:
                                // 点击微信扫码图片执行的操作
                                Toast.makeText(MainActivity.this, "微信扫一扫", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    //判断返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

}
