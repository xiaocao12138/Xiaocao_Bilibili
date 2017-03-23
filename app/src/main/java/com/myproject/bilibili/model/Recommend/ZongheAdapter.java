package com.myproject.bilibili.model.Recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chen on 2017/3/21 22:03.
 * 作用:XXXX
 */

public class ZongheAdapter extends RecyclerView.Adapter<ZongheAdapter.ViewHolder> {

    private final Context mContext;
    private final List<RecommendBean.DataBean> data;
    private final LayoutInflater from;

    public ZongheAdapter(Context mContext, List<RecommendBean.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
        from = LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(from.inflate(R.layout.item_zong_he, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvRecomName.setText(data.get(position).getTname());
        holder.tvTypeName.setText(data.get(position).getTitle());
        Glide.with(mContext).load(data.get(position).getCover()).into(holder.ivRecommend);
        holder.tvPlayNumber.setText(String.valueOf(data.get(position).getPlay()));
        holder.tvDanmuNumber.setText(String.valueOf(data.get(position).getDanmaku()));
        holder.tvVedioDuration.setText(String.valueOf(data.get(position).getDuration()));
        holder.tvPlayNumber.setText(String.valueOf(data.get(position).getPlay()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_play_number)
        TextView tvPlayNumber;
        @BindView(R.id.tv_danmu_number)
        TextView tvDanmuNumber;
        @BindView(R.id.tv_vedio_duration)
        TextView tvVedioDuration;
        @BindView(R.id.tv_type_name)
        TextView tvTypeName;
        @BindView(R.id.tv_recom_name)
        TextView tvRecomName;
        @BindView(R.id.tv_more)
        TextView tvMore;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.tv_more)
        public void onClick() {
            Toast.makeText(mContext, "更多", Toast.LENGTH_SHORT).show();
        }
    }

}
