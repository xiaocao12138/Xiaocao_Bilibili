package com.myproject.bilibili.model.Partition.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
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
import com.myproject.bilibili.activity.BannerInfoActivity;
import com.myproject.bilibili.model.Partition.bean.PartitionBean;
import com.myproject.bilibili.model.Partition.bean.PartitionMoreBean;
import com.myproject.bilibili.utils.Constants;
import com.myproject.bilibili.view.HotGridView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/23 0:37.
 * 作用:XXXX
 */

public class PartitionTagAdapter extends RecyclerView.Adapter {

//    public static final String TITLE = "title";
//    public static final String LINK = "link";

    private final Context mContext;
    private final List<PartitionBean.DataBean> list;
    private final List<PartitionMoreBean.DataBean> moreData;

    /**
     * TAG
     */
    public static final int TAG = 0;

    /**
     * GRIDANDBANNER
     */
    public static final int GRIDANDBANNER = 1;

    /**
     * GRID
     */
    public static final int GRID = 2;

    /**
     * ACTIVITY
     */
    public static final int ACTIVITY = 3;

    private final LayoutInflater inflater;


    /**
     * 当前类型
     */
    public int currentType = TAG;


    private PartitionMoreBean.DataBean moreDataBean;

    /*public PartitionTagAdapter(Context mContext, List<PartitionBean.DataBean> data, List<PartitionMoreBean.DataBean> datas) {
        this.mContext = mContext;
        this.data = data;
        this.list = datas;
        inflater = LayoutInflater.from(mContext);
    }*/

   /* public PartitionTagAdapter(Context mContext, List<PartitionMoreBean.DataBean> datas) {
        this.mContext = mContext;
        this.list = datas;
        inflater = LayoutInflater.from(mContext);
    }*/

    public PartitionTagAdapter(Context mContext, List<PartitionMoreBean.DataBean> moreData, List<PartitionBean.DataBean> data) {
        this.mContext = mContext;
        this.list = data;
        this.moreData = moreData;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemViewType(int position) {

        if (position != 0) {
            moreDataBean = moreData.get(position - 1);
        }

        if (position == 0) {
            currentType = TAG;
        } else if ("region".equals(moreDataBean.getType())) {
            currentType = GRIDANDBANNER;
        } else if ("topic".equals(moreDataBean.getType())) {
            currentType = GRID;
        } else if ("activity".equals(moreDataBean.getType())) {
            currentType = ACTIVITY;
        }
        return currentType;

        /*if (position == TAG) {
            currentType = TAG;
        } else if (position == GRIDANDBANNER) {
            currentType = GRIDANDBANNER;
        } else if (position == GRID) {
            currentType = GRID;
        }
        return currentType;*/
    }

    /*@Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == TAG) {
//            return new TagViewHolder(mContext, inflater.inflate(R.layout.tag_grid_view, null));
//        } else if (viewType == GRIDANDBANNER) {
//            return new TypeViewHolder(mContext, inflater.inflate(R.layout.type_grid_view, null));
//        } else if (viewType == GRID) {
//            return new BannerViewHolder(mContext, inflater.inflate(R.layout.other_partition, null));
//        }
//        return null;

        if (viewType == TAG) {
            return new TagViewHolder(mContext, inflater.inflate(R.layout.tag_grid_view, null));
        } else if (viewType == GRIDANDBANNER) {
            return new TypeViewHolder(mContext, inflater.inflate(R.layout.type_grid_view, null));
        } else if (viewType == GRID) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.other_partition, null));
        } else if (viewType == ACTIVITY) {
            return new ActivityViewHolder(mContext, inflater.inflate(R.layout.activity_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*if (getItemViewType(position) == TAG) {
            TagViewHolder viewHolder = (TagViewHolder) holder;
            //绑定数据
            viewHolder.setData(list);
        } else if (getItemViewType(position) == GRIDANDBANNER) {
            TypeViewHolder viewHolder = (TypeViewHolder) holder;
            //绑定数据
            viewHolder.setData(moreData);
        }else if (getItemViewType(position) == GRID){
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            //绑定数据
            viewHolder.setData(moreData.get(position));

        }*/

        if (getItemViewType(position) == TAG) {
            TagViewHolder viewHolder = (TagViewHolder) holder;
            viewHolder.setData(list);
        } else if (getItemViewType(position) == GRIDANDBANNER) {
            TypeViewHolder viewHolder = (TypeViewHolder) holder;
            viewHolder.setData(moreDataBean);
        } else if (getItemViewType(position) == GRID) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            viewHolder.setData(moreDataBean);
        } else if (getItemViewType(position) == ACTIVITY) {
            ActivityViewHolder activityViewHolder = (ActivityViewHolder) holder;
            activityViewHolder.setData(moreDataBean);
        }
    }

    @Override
    public int getItemCount() {
        return moreData.size() + 1;
    }

    class TagViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.gv_tag)
        HotGridView gvTag;

        TagViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }


        public void setData(List<PartitionBean.DataBean> dataBean) {

            TagViewHolderAdapter adapter = new TagViewHolderAdapter(mContext, dataBean);
            gvTag.setAdapter(adapter);

            gvTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.type_zhan_qu)
        TextView typeZhanQu;
        @BindView(R.id.tv_type_more)
        TextView tvTypeMore;
        @BindView(R.id.gv_live)
        HotGridView gvLive;
        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.iv_bottom_more)
        Button ivBottomMore;
        @BindView(R.id.tv_refresh)
        TextView tvRefresh;
        @BindView(R.id.iv_refresh)
        ImageView ivRefresh;


        TypeViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }

        public void setData(PartitionMoreBean.DataBean moreDataBean) {
            TypeViewAdapter adapter = new TypeViewAdapter(mContext, moreDataBean.getBody());
            gvLive.setAdapter(adapter);

            gvLive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });


            tvTypeMore.setText("进去看看");
            typeZhanQu.setText(moreDataBean.getTitle());

            String btn = moreDataBean.getTitle();
            String substring = btn.substring(0, btn.length() - 1);
            ivBottomMore.setText("更多" + substring);

            for (int i = 0; i < list.size(); i++) {
                PartitionBean.DataBean dataBean = list.get(i);

                if (substring.equals(list.get(i).getName())) {
                    Glide.with(mContext).load(dataBean.getLogo()).into(ivHead);
                }
            }

            final PartitionMoreBean.DataBean.BannerBean bannerInfo = PartitionTagAdapter.this.moreDataBean.getBanner();

            if (bannerInfo != null && bannerInfo.getBottom().size() > 0) {

                banner.setVisibility(View.VISIBLE);
                List<String> imgs = new ArrayList<>();
                for (int i = 0; i < bannerInfo.getBottom().size(); i++) {
                    imgs.add(bannerInfo.getBottom().get(i).getImage());
                }

                banner.setImages(imgs).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context)
                                .load((String) path)
                                .crossFade().into(imageView);
                    }
                });

                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

                //设置手动滑动
                banner.setViewPagerIsScroll(true);

                banner.start();

                //设置banner的点击事件
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(mContext, BannerInfoActivity.class);
                        intent.putExtra(Constants.TITLE, bannerInfo.getBottom().get(position).getTitle());
                        intent.putExtra(Constants.URL, bannerInfo.getBottom().get(position).getUri());
                        mContext.startActivity(intent);
                    }
                });

            } else {
                banner.setVisibility(View.GONE);
            }

        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.type_zhan_qu)
        TextView typeZhanQu;
        @BindView(R.id.tv_type_more)
        TextView tvTypeMore;
        @BindView(R.id.banner)
        Banner banner;

        BannerViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }

        public void setData(PartitionMoreBean.DataBean moredataBean) {

            ivHead.setImageResource(R.drawable.ic_header_topic);
            typeZhanQu.setText("话题");
            tvTypeMore.setText("进去看看");

            final List<PartitionMoreBean.DataBean.BodyBean> body = moredataBean.getBody();

            List<String> imgs = new ArrayList<>();
            for (int i = 0; i < body.size(); i++) {
                imgs.add(body.get(i).getCover());
            }

            banner.setImages(imgs).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context)
                            .load((String) path)
                            .crossFade().into(imageView);
                }
            });

            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

            //设置手动滑动
            banner.setViewPagerIsScroll(true);

            banner.start();

            //设置banner的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(mContext, BannerInfoActivity.class);
                    intent.putExtra(Constants.TITLE, body.get(position).getTitle());
                    intent.putExtra(Constants.URL, body.get(position).getUri());
                    mContext.startActivity(intent);
                }
            });

        }
    }

    class ActivityViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.type_zhan_qu)
        TextView typeZhanQu;
        @BindView(R.id.tv_type_more)
        TextView tvTypeMore;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;


        ActivityViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }

        public void setData(PartitionMoreBean.DataBean moreDataBean) {

            //设置适配器
            HDActivityAdapter adapter = new HDActivityAdapter(mContext, moreDataBean);
            recyclerView.setAdapter(adapter);

            typeZhanQu.setText(moreDataBean.getTitle());
            String btn = moreDataBean.getTitle();
            String substring = btn.substring(0, btn.length() - 1);
            tvTypeMore.setText("进去看看");

            for (int i = 0; i < list.size(); i++) {
                PartitionBean.DataBean dataBean = list.get(i);

                if (substring.equals(list.get(i).getName())) {
                    Glide.with(mContext).load(dataBean.getLogo()).into(ivHead);
                }
            }

            //布局管理器
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        }
    }
}

