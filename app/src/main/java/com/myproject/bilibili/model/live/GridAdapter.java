package com.myproject.bilibili.model.live;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myproject.bilibili.R;
import com.myproject.bilibili.model.live.bean.LiveBean;
import com.myproject.bilibili.video.DanmkuVideoActivity;
import com.myproject.bilibili.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/22 14:31.
 * 作用:XXXX
 */

public class GridAdapter extends BaseAdapter {

    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String ONLINE = "online";
    public static final String IMAGE_URL = "imageUrl";
    public static final String USERNAME = "username";

    private final Context mContect;
    private final List<LiveBean.DataBean.PartitionsBean.LivesBean> partitions;

    public GridAdapter(Context mContect, LiveBean.DataBean.PartitionsBean partitions) {
        this.mContect = mContect;
        this.partitions = partitions.getLives();
    }

    @Override
    public int getCount() {
        return partitions.size() / 2;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContect, R.layout.item_grid_adapter, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        List<LiveBean.DataBean.PartitionsBean.LivesBean> lives = partitions.get(position).getLives();
        final LiveBean.DataBean.PartitionsBean.LivesBean livesBean = partitions.get(position);

        Glide.with(mContect)
                .load(livesBean.getCover().getSrc())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .into(holder.itemLiveCover);
        Glide.with(mContect)
                .load(livesBean.getOwner().getFace())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.ico_user_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.itemLiveUserCover);
        holder.itemLiveTitle.setText(livesBean.getTitle());
        holder.itemLiveUser.setText(livesBean.getOwner().getName());
        holder.itemLiveCount.setText(String.valueOf(livesBean.getOnline()));

        holder.itemCartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (listener != null){
                        listener.OnClick(v , position);
                    }*/
                Intent intent = new Intent(mContect, DanmkuVideoActivity.class);
                intent.putExtra(URL,livesBean.getPlayurl());
                intent.putExtra(TITLE, livesBean.getTitle());
                intent.putExtra(ONLINE, livesBean.getOnline());
                intent.putExtra(USERNAME, livesBean.getOwner().getName());
                intent.putExtra(IMAGE_URL, livesBean.getOwner().getFace());
                mContect.startActivity(intent);
            }
        });

        return convertView;
    }


    class ViewHolder {
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
        @BindView(R.id.item_cart_view)
        CardView itemCartView;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
           /* view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.OnClick(v , );
                    }
                }
            });*/
        }
    }

    public interface OnItemClickListener{
        void OnClick(View view , int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
