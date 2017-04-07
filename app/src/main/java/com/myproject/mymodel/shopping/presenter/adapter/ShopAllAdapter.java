package com.myproject.mymodel.shopping.presenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.mymodel.shopping.view.activity.ShopInfoActivity;
import com.myproject.mymodel.shopping.bean.ShopAllBean;
import com.myproject.mymodel.utils.CommonUtil;
import com.myproject.mymodel.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/27 18:53.
 * 作用:XXXX
 */

public class ShopAllAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ShopAllBean.ResultBean.RecordsBean> records;

    public ShopAllAdapter(Context mContext, List<ShopAllBean.ResultBean.RecordsBean> records) {
        this.mContext = mContext;
        this.records = records;
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int position) {
        return records.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_shop_all, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ShopAllBean.ResultBean.RecordsBean recordsBean = records.get(position);

        holder.tvShopName.setText(recordsBean.getTitle());
        holder.tvShopPrice.setText(String.valueOf(recordsBean.getSalvePrice()));

        boolean networkAvailable = CommonUtil.isNetworkAvailable(mContext);
        boolean isMobile = CommonUtil.isMobile(mContext);
        if (networkAvailable){
            if (isMobile){
                holder.ivShop.setImageResource(R.drawable.aa);
                Toast.makeText(mContext, "非wifi状态下不显示图片", Toast.LENGTH_SHORT).show();
            }else {
                Glide.with(mContext).load(recordsBean.getImgUrl()).crossFade().into(holder.ivShop);
            }
        }else {
            Toast.makeText(mContext, "联网失败", Toast.LENGTH_SHORT).show();
        }

//        Glide.with(mContext).load(recordsBean.getImgUrl()).crossFade().into(holder.ivShop);

        holder.itemCartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext , ShopInfoActivity.class);
                intent.putExtra(Constants.IMAGE_URL , recordsBean.getImgUrl());
                intent.putExtra(Constants.SKUID , recordsBean.getSkuId());
                intent.putExtra(Constants.TITLE , recordsBean.getTitle());
                intent.putExtra(Constants.PRICE , recordsBean.getSalvePrice());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_shop)
        ImageView ivShop;
        @BindView(R.id.item_cart_view)
        CardView itemCartView;
        @BindView(R.id.tv_shop_name)
        TextView tvShopName;
        @BindView(R.id.tv_shop_price)
        TextView tvShopPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
