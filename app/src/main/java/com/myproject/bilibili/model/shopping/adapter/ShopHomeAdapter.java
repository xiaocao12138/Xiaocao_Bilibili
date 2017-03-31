package com.myproject.bilibili.model.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.bilibili.activity.BannerInfoActivity;
import com.myproject.bilibili.model.shopping.bean.ShopAllBean;
import com.myproject.bilibili.model.shopping.bean.ShopBannerBean;
import com.myproject.bilibili.utils.Constants;
import com.myproject.bilibili.view.HotGridView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/27 16:48.
 * 作用:XXXX
 */

public class ShopHomeAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<ShopAllBean.ResultBean.RecordsBean> records;
    private final List<ShopBannerBean.ResultBean.ModelDetailsBean> modelDetails;
    private final LayoutInflater inflater;

    private int BANNER = 0;
    private int GRID = 1;
    private int SHOP = 2;
    private int currentType = BANNER;

    public ShopHomeAdapter(Context mContext, List<ShopAllBean.ResultBean.RecordsBean> records,
                           List<ShopBannerBean.ResultBean.ModelDetailsBean> modelDetails) {

        this.mContext = mContext;
        this.records = records;
        this.modelDetails = modelDetails;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == GRID) {
            currentType = GRID;
        } else if (position == SHOP) {
            currentType = SHOP;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.shop_banner, null));
        } else if (viewType == GRID) {
            return new GridViewHolder(mContext, inflater.inflate(R.layout.shop_grid, null));
        } else if (viewType == SHOP) {
            return new ShopViewHolder(mContext, inflater.inflate(R.layout.shop_all, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            //绑定数据
//            viewHolder.setData(data);
            viewHolder.setData(modelDetails);
        } else if (getItemViewType(position) == GRID) {
            GridViewHolder viewHolder = (GridViewHolder) holder;
            //绑定数据
            viewHolder.setData();
        } else if (getItemViewType(position) == SHOP) {
            ShopViewHolder viewHolder = (ShopViewHolder) holder;
            //绑定数据
            viewHolder.setData(records);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.banner)
        Banner banner;

        BannerViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }

        public void setData(final List<ShopBannerBean.ResultBean.ModelDetailsBean> modelDetails) {
            List<String> imageUrls = new ArrayList<>();
            for (int i = 0; i < modelDetails.size(); i++) {
                imageUrls.add(modelDetails.get(i).getSmallImageUrl());
            }
            banner.setImages(imageUrls)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).load(path).crossFade().into(imageView);
                        }
                    }).start();

            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(mContext, BannerInfoActivity.class);
                    intent.putExtra(Constants.URL, modelDetails.get(position).getImgLink());
                    intent.putExtra(Constants.TITLE, modelDetails.get(position).getBigTitle());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    static class GridViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.my_grid_view)
        HotGridView myGridView;

        GridViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }


        public void setData() {

            myGridView.setAdapter(new ShopGridAdapter(mContext));
        }
    }


    static class ShopViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.my_grid_view)
        HotGridView myGridView;
        private ShopAllAdapter adapter;

        ShopViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }

        public void setData(List<ShopAllBean.ResultBean.RecordsBean> records) {
            adapter = new ShopAllAdapter(mContext , records);
            myGridView.setAdapter(adapter);

        }
    }

}
