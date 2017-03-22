package com.myproject.bilibili.model.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.bilibili.view.HotGridView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/21 15:29.
 * 作用:XXXX
 */

public class LiveAdapter extends RecyclerView.Adapter {

    private final Context mContect;
    private final LiveBean.DataBean data;
    private final LayoutInflater inflater;
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 横幅广告
     */
    public static final int GRID = 1;
    /**
     * 当前类型
     */
    public int currentType = BANNER;

    public LiveAdapter(Context mContect, LiveBean.DataBean data) {
        this.mContect = mContect;
        this.data = data;
        inflater = LayoutInflater.from(mContect);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == GRID) {
            currentType = GRID;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContect, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == GRID) {
            return new GridViewHolder(mContect, inflater.inflate(R.layout.grid_viewpager, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //绑定数据
            bannerViewHolder.setData(data.getBanner());
        } else if (getItemViewType(position) == GRID) {
            GridViewHolder gridViewHolder = (GridViewHolder) holder;
            //绑定数据
            gridViewHolder.setData(data.getPartitions());
        }

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.banner)
        Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<LiveBean.DataBean.BannerBean> bannerinfo) {

            List<String> imageUrls = new ArrayList<>();

            for (int i = 0; i < bannerinfo.size(); i++) {
                imageUrls.add(bannerinfo.get(i).getImg());
                imageUrls.add(bannerinfo.get(i).getImg());
                imageUrls.add(bannerinfo.get(i).getImg());
                imageUrls.add(bannerinfo.get(i).getImg());
                imageUrls.add(bannerinfo.get(i).getImg());
            }

            banner.setImages(imageUrls)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    }).start();

            //设置动画效果---手风琴效果
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
        }
    }

    class GridViewHolder extends RecyclerView.ViewHolder {

        private final Context mContect;
        @BindView(R.id.zhanqu)
        TextView zhanqu;
        @BindView(R.id.tv_live_number)
        TextView tvLiveNumber;
        @BindView(R.id.gv_live)
        HotGridView gvLive;
        @BindView(R.id.refresh)
        TextView refresh;
        @BindView(R.id.more)
        Button more;
        private GridAdapter adapter;

        GridViewHolder(Context mContect, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContect = mContect;
        }

        public void setData(List<LiveBean.DataBean.PartitionsBean> partitions) {

            //设置适配器
            adapter = new GridAdapter(mContect , partitions);
            gvLive.setAdapter(adapter);

            gvLive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContect , "position" + position , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
