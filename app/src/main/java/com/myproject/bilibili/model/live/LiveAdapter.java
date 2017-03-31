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
import com.myproject.bilibili.activity.LiveCenterActivity;
import com.myproject.bilibili.model.live.activity.SmallVideoActivity;
import com.myproject.bilibili.model.live.adapter.RecommendLiveAdapter;
import com.myproject.bilibili.model.live.bean.LIveRecomendBean;
import com.myproject.bilibili.model.live.bean.LiveBean;
import com.myproject.bilibili.utils.Constants;
import com.myproject.bilibili.view.HotGridView;
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

    private final Context mContect;

    private final LayoutInflater inflater;
    /**
     * BANNER
     */
    public static final int BANNER = 0;

    /**
     * RECCOMEND
     */
    public static final int RECCOMEND = 1;

    /**
     * RECCOMEND2
     */
    public static final int RECCOMEND2 = 2;

    /**
     * GRID
     */
    public static final int GRID = 3;

    private final LiveBean.DataBean data;
    private final LIveRecomendBean.DataBean.RecommendDataBean recommendData;
    /**
     * 当前类型
     */
    public int currentType = BANNER;

    public LiveAdapter(Context mContect, LiveBean.DataBean data, LIveRecomendBean.DataBean.RecommendDataBean recommendData) {
        this.mContect = mContect;
        this.data = data;
        this.recommendData = recommendData;
        inflater = LayoutInflater.from(mContect);
    }

    @Override
    public int getItemCount() {
        return data.getPartitions().size() + 3;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == RECCOMEND) {
            currentType = RECCOMEND;
        } else if (position == RECCOMEND2) {
            currentType = RECCOMEND2;
        } else if (position == GRID) {
            currentType = GRID;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContect, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == RECCOMEND) {
            return new RecommendViewHolder(mContect, inflater.inflate(R.layout.recommend_live, null));
        } else if (viewType == RECCOMEND2) {
            return new Recommend2ViewHolder(mContect, inflater.inflate(R.layout.recommend_live, null));
        } else if (viewType == GRID) {
            return new GridViewHolder(mContect, inflater.inflate(R.layout.grid_viewpager, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == BANNER) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            //绑定数据
            viewHolder.setData(data.getBanner());
        } else if (getItemViewType(position) == RECCOMEND) {
            RecommendViewHolder viewHolder = (RecommendViewHolder) holder;
            //绑定数据
            viewHolder.setData(recommendData);
        } else if (getItemViewType(position) == RECCOMEND2) {
            Recommend2ViewHolder viewHolder = (Recommend2ViewHolder) holder;
            //绑定数据
            viewHolder.setData(recommendData);
        } else if (getItemViewType(position) == GRID) {
            GridViewHolder viewHolder = (GridViewHolder) holder;
            //绑定数据
            viewHolder.setData(data.getPartitions().get(position - 3));
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
                    intent.putExtra(Constants.TITLE, bannerinfo.get(position).getTitle());
                    intent.putExtra(Constants.URL, bannerinfo.get(position).getLink());
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
                    mContect.startActivity(new Intent(mContext, LiveCenterActivity.class));
                    break;
                case R.id.vedio:
                    mContect.startActivity(new Intent(mContext, SmallVideoActivity.class));
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

        public void setData(final LiveBean.DataBean.PartitionsBean partitionsBean) {

            zhanQu.setText(partitionsBean.getPartition().getName());
            tvLiveNumber.setText(String.valueOf(partitionsBean.getPartition().getCount()));
            Glide.with(mContect)
                    .load(partitionsBean.getPartition().getSub_icon().getSrc())
                    .into(ivHead);

            adapter = new GridAdapter(mContect, partitionsBean);
            gvLive.setAdapter(adapter);

        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;

        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_live_number)
        TextView tvLiveNumber;
        @BindView(R.id.recommend_banner)
        Banner recommendBanner;
        @BindView(R.id.recommend_grid_view)
        HotGridView recommendGridView;
        private List<String> images;
        private RecommendLiveAdapter adapter;

        RecommendViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }


        public void setData(LIveRecomendBean.DataBean.RecommendDataBean recommendData) {


            Glide.with(mContext)
                    .load(recommendData.getPartition().getSub_icon().getSrc())
                    .crossFade()
                    .into(ivHead);
            tvName.setText(recommendData.getPartition().getName());
            tvLiveNumber.setText(String.valueOf(recommendData.getPartition().getCount()));

            recommendBanner.setVisibility(View.GONE);
            /*images = new ArrayList<>();
            images.add(recommendData.getBanner_data().get(0).getCover().getSrc());
            recommendBanner.setImages(images)
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
            recommendBanner.setBannerAnimation(BackgroundToForegroundTransformer.class);

            recommendBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });*/


            adapter = new RecommendLiveAdapter(mContext, currentType, recommendData.getLives());
            recommendGridView.setAdapter(adapter);


        }
    }

    class Recommend2ViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;

        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_live_number)
        TextView tvLiveNumber;
        @BindView(R.id.tv_01)
        TextView tv01;
        @BindView(R.id.tv_02)
        TextView tv02;
        @BindView(R.id.recommend_banner)
        Banner recommendBanner;
        @BindView(R.id.recommend_grid_view)
        HotGridView recommendGridView;
        private List<String> images;
        private RecommendLiveAdapter adapter;

        Recommend2ViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }


        public void setData(LIveRecomendBean.DataBean.RecommendDataBean recommendData) {


            ivHead.setVisibility(View.GONE);
            tvName.setVisibility(View.GONE);
            tvLiveNumber.setVisibility(View.GONE);
            tv01.setVisibility(View.GONE);
            tv02.setVisibility(View.GONE);

            images = new ArrayList<>();

            images.add(recommendData.getBanner_data().get(0).getCover().getSrc());
            /*for (int i = 0; i < recommendData.getBanner_data().size(); i++) {
                images.add(recommendData.getBanner_data().get(1).getCover().getSrc());
            }*/

            recommendBanner.setImages(images)
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
            recommendBanner.setBannerAnimation(BackgroundToForegroundTransformer.class);

            recommendBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });


            adapter = new RecommendLiveAdapter(mContext, currentType, recommendData.getLives());
            recommendGridView.setAdapter(adapter);

        }
    }
}
