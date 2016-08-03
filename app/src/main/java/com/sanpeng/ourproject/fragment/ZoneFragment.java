package com.sanpeng.ourproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanpeng.ourproject.R;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class ZoneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_zone_fragment,container,false);
        return rootView;
    }
}
