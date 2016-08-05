package com.sanpeng.ourproject.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.sanpeng.ourproject.R;
import com.sanpeng.ourproject.activity.DescribeActivity;
import com.sanpeng.ourproject.adapter.ZoneAdapter;
import com.sanpeng.ourproject.beans.ZoneData;
import com.sanpeng.ourproject.callbacks.MyZoneInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class ZoneFragment extends Fragment {

    PullToRefreshScrollView pullToRefreshScrollView;

    ImageView cloudImageView;

    RecyclerView recyclerView;
    ZoneAdapter adapter;

    boolean isSucess = false;

    int page=1;
    int per_page = 10;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ZoneAdapter(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_zone_fragment, container, false);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.zone_header_layout,null);
        cloudImageView = (ImageView) view.findViewById(R.id.cloud_img);

        pullToRefreshScrollView = (PullToRefreshScrollView) rootView.findViewById(R.id.zone_scroll_view);
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                page++;
                downLoadData(page,0);
                showAnim(cloudImageView);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page++;
                downLoadData(page,1);
            }
        });


        recyclerView = (RecyclerView) rootView.findViewById(R.id.zone_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setView(view);
        recyclerView.setAdapter(adapter);

        showAnim(cloudImageView);

        cloudImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DescribeActivity.class);
                startActivity(intent);
            }
        });

        downLoadData(page,0);

        return rootView;
    }

    // http://api.jiangwoo.com/api/v2/spaces?page=1&per_page=10
    public void downLoadData(int page, final int type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.jiangwoo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyZoneInterface myZoneInterface = retrofit.create(MyZoneInterface.class);
        Call<ZoneData> call = myZoneInterface.getData(page,per_page);
        call.enqueue(new Callback<ZoneData>() {
            @Override
            public void onResponse(Call<ZoneData> call, Response<ZoneData> response) {
                ZoneData zoneData = response.body();
                final List<ZoneData.SpacesBean> spacesBeens = zoneData.getSpaces();
//                Log.e("========", "========" + spacesBeens.size());
                adapter.setSpacesBeens(spacesBeens,type);
                adapter.setmOnItemClickListener(new ZoneAdapter.onItemClickListener() {
                    @Override
                    public void onItemClickListener(int pos) {
//                        Intent intent = new Intent(getActivity(), ZoneDetailActivity.class);
//                        int id = spacesBeens.get(pos).getId();
//                        intent.putExtra("id", id);
//                        intent.putExtra("pos",pos);
//                        startActivity(intent);
//                        Log.e("========","========="+pos);
                    }
                });
            }

            @Override
            public void onFailure(Call<ZoneData> call, Throwable t) {

            }
        });

        isSucess = true;
        if (isSucess==true){
            pullToRefreshScrollView.onRefreshComplete();
        }

    }

    // header动画展示
    public void showAnim(View view){
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"translationX",0.0f,30.0f,0.0f);
        animator.setDuration(5000);
        animator.setRepeatCount(-1);//设置动画重复次数
        animator.setRepeatMode(ValueAnimator.RESTART);//动画重复模式
        animator.start();
    }
}
