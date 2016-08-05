package com.sanpeng.ourproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.sanpeng.ourproject.R;
import com.sanpeng.ourproject.beans.ZoneData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class ZoneAdapter extends RecyclerView.Adapter<ZoneAdapter.MyViewHolder> {

    List<ZoneData.SpacesBean> spacesBeens = new ArrayList<>();
    Context context;
    onItemClickListener mOnItemClickListener;
    private static final int TYPE_HEADER = 0, TYPE_ITEM = 1;
    View headerView;

    public void setView(View view) {
        this.headerView = view;
    }

    public void setmOnItemClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setSpacesBeens(List<ZoneData.SpacesBean> spacesBeens1,int type) {
        if (type==0){
            this.spacesBeens = spacesBeens1;
        }else if (type==1){
            spacesBeens.addAll(spacesBeens1);
        }
//        this.spacesBeens = spacesBeens1;
        notifyDataSetChanged();
    }


    public ZoneAdapter(Context context) {
        this.context = context;
        Fresco.initialize(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_ITEM:
                view = LayoutInflater.from(context).inflate(R.layout.zone_main_item, null);
                break;
            case TYPE_HEADER:
                view = headerView;
                break;
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_HEADER:
                break;
            case TYPE_ITEM:
                holder.titleTextView.setText(spacesBeens.get(position-1).getName());
                Glide.with(context).load(spacesBeens.get(position-1)
                        .getThumb())
                        .placeholder(R.drawable.bg_default_rectangle)
                        .into(holder.imageView);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClickListener(position-1);
                        }
                    }
                });
                break;
        }    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_ITEM;
        if (position<1) {
            type=TYPE_HEADER;
        }else {
            type=TYPE_ITEM;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        return spacesBeens.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.zone_recycler_img);
            titleTextView = (TextView) itemView.findViewById(R.id.zone_title_tv);
        }
    }

    public interface onItemClickListener {
        public void onItemClickListener(int pos);
    }

}
