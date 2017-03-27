package com.myproject.bilibili.model.Cartoon.adapter;

import android.content.Context;
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
        holder.tvTypeName.setText(serializingBean.getTitle());
//        holder.tvPrice.setText(recommendInfoBean.getCover_price());
        Glide.with(mContext).load(serializingBean.getCover()).into(holder.ivRecommend);
        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_type_name)
        TextView tvTypeName;
        @BindView(R.id.tv_recom_name)
        TextView tvRecomName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
