package com.myproject.bilibili.model.Partition.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.bilibili.model.Partition.bean.PartitionMoreBean;
import com.myproject.bilibili.model.found.HuaTiActivity;
import com.myproject.bilibili.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/28 10:40.
 * 作用:XXXX
 */

public class HDActivityAdapter extends RecyclerView.Adapter<HDActivityAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater inflater;
    private final PartitionMoreBean.DataBean data;


    public HDActivityAdapter(Context mContext, PartitionMoreBean.DataBean moreDataBean) {
        this.mContext = mContext;
        this.data = moreDataBean;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mContext, inflater.inflate(R.layout.activity_partition, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(data.getBody().get(position));
    }

    @Override
    public int getItemCount() {
        return data.getBody().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.iv_huati)
        ImageView ivHuati;
        @BindView(R.id.tv_name_activity)
        TextView tvNameActivity;
        @BindView(R.id.item_live_layout)
        CardView itemLiveLayout;

        ViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }

        public void setData(PartitionMoreBean.DataBean.BodyBean bodyBean) {

            boolean networkAvailable = CommonUtil.isNetworkAvailable(mContext);
            boolean isMobile = CommonUtil.isMobile(mContext);
            if (networkAvailable){
                if (isMobile){
                    ivHuati.setImageResource(R.drawable.aa);
                    Toast.makeText(mContext, "非wifi状态下不显示图片", Toast.LENGTH_SHORT).show();
                }else {
                    Glide.with(mContext).load(bodyBean.getCover()).crossFade().into(ivHuati);
                }
            }

//            Glide.with(mContext).load(bodyBean.getCover()).crossFade().into(ivHuati);
            tvNameActivity.setText(bodyBean.getTitle());

            itemLiveLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, "1111111111111111", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, HuaTiActivity.class));
                }
            });

        }
    }
}
