package com.sanpeng.ourproject.callbacks;

import com.sanpeng.ourproject.beans.ZoneData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/8/3 0003.
 * http://api.jiangwoo.com/api/v2/spaces?page=1&per_page=10
 */
public interface MyZoneInterface {
//    @GET("api/v2/spaces?page=1&per_page=10")
    @GET("api/v2/spaces")
    public Call<ZoneData> getData(
            @Query("page") int page,
            @Query("per_page") int per_page
    );
}
