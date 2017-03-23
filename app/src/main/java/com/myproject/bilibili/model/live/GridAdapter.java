package com.myproject.bilibili.model.live;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myproject.bilibili.R;
import com.myproject.bilibili.model.live.bean.LiveBean;
import com.myproject.bilibili.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/22 14:31.
 * 作用:XXXX
 */

public class GridAdapter extends BaseAdapter {

    private final Context mContect;
    private final List<LiveBean.DataBean.PartitionsBean> partitions;

    public GridAdapter(Context mContect, List<LiveBean.DataBean.PartitionsBean> partitions) {
        this.mContect = mContect;
        this.partitions = partitions;
    }

    @Override
    public int getCount() {
        return partitions.size();
    }

    @Override
    public Object getItem(int position) {
        return partitions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContect, R.layout.item_grid_adapter, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        List<LiveBean.DataBean.PartitionsBean.LivesBean> lives = partitions.get(position).getLives();
        LiveBean.DataBean.PartitionsBean.LivesBean livesBean = lives.get(position);
        Glide.with(mContect)
                .load(livesBean.getCover().getSrc())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .into(holder.itemLiveCover);
        Glide.with(mContect)
                .load(livesBean.getCover().getSrc())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.ico_user_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.itemLiveUserCover);
        holder.itemLiveTitle.setText(livesBean.getTitle());
        holder.itemLiveUser.setText(livesBean.getOwner().getName());
        holder.itemLiveCount.setText(String.valueOf(livesBean.getOnline()));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_live_cover)
        ImageView itemLiveCover;
        @BindView(R.id.item_live_user_cover)
        CircleImageView itemLiveUserCover;
        @BindView(R.id.item_live_title)
        TextView itemLiveTitle;
        @BindView(R.id.item_live_user)
        TextView itemLiveUser;
        @BindView(R.id.item_live_count)
        TextView itemLiveCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
