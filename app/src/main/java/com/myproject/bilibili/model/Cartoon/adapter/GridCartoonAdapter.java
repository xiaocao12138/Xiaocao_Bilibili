package com.myproject.bilibili.model.Cartoon.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.bilibili.model.Cartoon.bean.CartoonBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/24 10:16.
 * 作用:XXXX
 */

public class GridCartoonAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<CartoonBean.ResultBean.SerializingBean> list;

    public GridCartoonAdapter(Context mContext, List<CartoonBean.ResultBean.SerializingBean> headBean) {
        this.mContext = mContext;
        this.list = headBean;
    }


   /* public GridCartoonAdapter(Context mContext, List<CartoonBean.ResultBean.AdBean.HeadBean> head) {
        this.mContext = mContext;
        this.head = head;
    }*/


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_grid_cartoon, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CartoonBean.ResultBean.SerializingBean serializingBean = list.get(position);
        holder.tvVedioName.setText(serializingBean.getTitle());
        holder.tvPlayNumber.setText(String.valueOf(serializingBean.getFavourites()));
        holder.tvNumber.setText(serializingBean.getNewest_ep_index() + "话");
//        holder.tvPrice.setText(recommendInfoBean.getCover_price());
        Glide.with(mContext).load(serializingBean.getCover()).into(holder.ivRecommend);

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_play_number)
        TextView tvPlayNumber;
        @BindView(R.id.tv_vedio_name)
        TextView tvVedioName;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.item_live_layout)
        CardView itemLiveLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
