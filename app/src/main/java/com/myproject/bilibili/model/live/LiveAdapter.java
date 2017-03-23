package com.myproject.bilibili.model.live;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.bilibili.activity.BannerInfoActivity;
import com.myproject.bilibili.model.live.bean.LiveBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chen on 2017/3/21 15:29.
 * 作用:XXXX
 */

public class LiveAdapter extends RecyclerView.Adapter {

    public static final String TITLE = "title";
    public static final String URL = "url";
    private final Context mContect;
    private final LiveBean.DataBean data;
    private final LayoutInflater inflater;
    /**
     * BANNER
     */
    public static final int BANNER = 0;
    /**
     * GRID
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
        return 6;
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
            gridViewHolder.setData(data.getPartitions().get(position));
        }

    }


    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.guanzu)
        TextView guanzu;
        @BindView(R.id.center)
        TextView center;
        @BindView(R.id.vedio)
        TextView vedio;
        @BindView(R.id.search)
        TextView search;
        @BindView(R.id.type)
        TextView type;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<LiveBean.DataBean.BannerBean> bannerinfo) {
            List<String> imageUrls = new ArrayList<>();

            for (int i = 0; i < bannerinfo.size(); i++) {
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

            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
//                    Toast.makeText(mContext, "position" + position , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, BannerInfoActivity.class);
                    intent.putExtra(TITLE, bannerinfo.get(position).getTitle());
                    intent.putExtra(URL, bannerinfo.get(position).getLink());
                    mContect.startActivity(intent);
                }
            });

        }

        @OnClick({R.id.guanzu, R.id.center, R.id.vedio, R.id.search, R.id.type})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.guanzu:
                    Toast.makeText(mContext, "关注", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.center:
                    Toast.makeText(mContext, "中心", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.vedio:
                    Toast.makeText(mContext, "小视频", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.search:
                    Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.type:
                    Toast.makeText(mContext, "分类", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        private final Context mContect;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.zhan_qu)
        TextView zhanQu;
        @BindView(R.id.tv_live_number)
        TextView tvLiveNumber;
        @BindView(R.id.gv_live)
        GridView gvLive;
        private GridAdapter adapter;

        GridViewHolder(Context mContect, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContect = mContect;
        }

        public void setData(LiveBean.DataBean.PartitionsBean partitionsBean) {

            List<LiveBean.DataBean.PartitionsBean> partitions = data.getPartitions();


                zhanQu.setText(partitionsBean.getPartition().getName());
                tvLiveNumber.setText(partitionsBean.getPartition().getCount()+"");
                Glide.with(mContect)
                        .load(partitionsBean.getPartition().getSub_icon().getSrc())
                        .into(ivHead);

                adapter = new GridAdapter(mContect, partitions);
                gvLive.setAdapter(adapter);

        }
    }
}
