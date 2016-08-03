package com.sanpeng.ourproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mob.tools.utils.UIHandler;
import com.sanpeng.ourproject.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class MineFragment extends Fragment {

    Button button01,button02;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                HashMap<String, Object> hashMap = (HashMap<String, Object>) msg.obj;
                Toast.makeText(getActivity(), "登录成功" + hashMap.toString(), Toast.LENGTH_LONG).show();

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_mine_fragment,container,false);

        //初始化对象
        ShareSDK.initSDK(getActivity());
        button01 = (Button) rootView.findViewById(R.id.login);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(QQ.NAME);

            }
        });

        button02 = (Button) rootView.findViewById(R.id.share);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });


        return rootView;
    }

    private void showShare() {
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.ssdk_oks_multi_share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getActivity());
    }

    public void login(String name) {
        /**
         * 获取指定平台对象
         * 参数2:平台的名称
         */
        Platform platform = ShareSDK.getPlatform(getActivity(), name);
        //移除账号信息(调用此方法，再次登录会弹出输入用户名密码的授权页面)
        platform.removeAccount();
        //参数值为null获取当前登录的账号信息
        platform.showUser(null);
        //设置登录结果监听
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(final Platform platform, int i, final HashMap<String, Object> hashMap) {
                //Log.e("=====", "===登录成功===" + hashMap.toString());
                //更新UI方式1；在子线程中操作如下：
//                        Looper.prepare();
//                        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
//                        Looper.loop();
                //更新UI方式2；
                // handler.obtainMessage(0,hashMap).sendToTarget();

                //更新UI方式3；sharesdk提供的更新UI的类
                final Message message = new Message();
                message.what = 0;
                message.obj = hashMap;
                UIHandler.sendMessage(message, new android.os.Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        if (msg.what == 0) {
                            if (platform.getName().equals(QQ.NAME)){
                                HashMap<String, Object> hashMap1 = (HashMap<String, Object>) msg.obj;
                            }else if (platform.getName().equals(SinaWeibo.NAME)){
                                HashMap<String, Object> hashMap1 = (HashMap<String, Object>) msg.obj;
                            }
                            String name = platform.getDb().getUserName();
                            String icon = platform.getDb().getUserIcon();
                            Log.e("====", "==name==" + name);
                            Log.e("====", "==icon==" + icon);
                            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                });

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e("=====", "===登录失败===" + throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e("=====", "===登录取消===");
            }
        });


    }


}
