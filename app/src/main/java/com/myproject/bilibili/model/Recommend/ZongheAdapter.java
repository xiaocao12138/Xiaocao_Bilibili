package com.myproject.bilibili.model.Recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/21 22:03.
 * 作用:XXXX
 */

public class ZongheAdapter extends RecyclerView.Adapter<ZongheAdapter.ViewHolder> {

    private final Context mContext;
    private final List<RecommendBean.DataBean> data;
    private final LayoutInflater from;
    @BindView(R.id.iv_recommend)
    ImageView ivRecommend;
    @BindView(R.id.tv_type_name)
    TextView tvTypeName;
    @BindView(R.id.tv_recom_name)
    TextView tvRecomName;
    @BindView(R.id.iv_more)
    ImageView ivMore;

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
        holder.tvRecomName.setText(data.get(position).getTitle());
        holder.tvTypeName.setText(data.get(position).getTname());
        Glide.with(mContext).load(data.get(position).getCover()).into(holder.ivRecommend);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_type_name)
        TextView tvTypeName;
        @BindView(R.id.tv_recom_name)
        TextView tvRecomName;
        @BindView(R.id.iv_more)
        ImageView ivMore;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            ivMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }

    }


}
