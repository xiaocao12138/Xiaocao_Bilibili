package com.myproject.mymodel.live.prestener.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myproject.bilibili.R;
import com.myproject.mymodel.live.bean.LIveRecomendBean;
import com.myproject.mymodel.utils.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/26 22:03.
 * 作用:XXXX
 */

public class RecommendLiveAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<LIveRecomendBean.DataBean.RecommendDataBean.LivesBean> lives;
    private final int currentType;
    private LIveRecomendBean.DataBean.RecommendDataBean.LivesBean livesBean;


    public RecommendLiveAdapter(Context mContext, int currentType, List<LIveRecomendBean.DataBean.RecommendDataBean.LivesBean> lives) {
        this.mContext = mContext;
        this.lives = lives;
        this.currentType = currentType;
    }

    @Override
    public int getCount() {
        return lives.size() / 2;
    }

    @Override
    public Object getItem(int position) {
        return lives.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_recommend_live_adapter, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        List<LiveBean.DataBean.PartitionsBean.LivesBean> lives = partitions.get(position).getLives();
        if (currentType == LiveAdapter.RECCOMEND){
            livesBean = lives.get(position);
        }else if (currentType == LiveAdapter.RECCOMEND2){
            livesBean = lives.get(position + lives.size()/2);
        }

        holder.tvType.setText("##"+ livesBean.getArea()+"##");
        holder.itemLiveTitle.setText(livesBean.getTitle());
        holder.itemLiveUser.setText(livesBean.getOwner().getName());
        holder.itemLiveCount.setText(String.valueOf(livesBean.getOnline()));

        boolean networkAvailable = CommonUtil.isNetworkAvailable(mContext);
        boolean isMobile = CommonUtil.isMobile(mContext);
        if (networkAvailable){
            if (isMobile){
                holder.itemLiveCover.setImageResource(R.drawable.aa);
                Toast.makeText(mContext, "非wifi状态下不显示图片", Toast.LENGTH_SHORT).show();
            }else {
                Glide.with(mContext)
                        .load(livesBean.getCover().getSrc())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.bili_default_image_tv)
                        .into(holder.itemLiveCover);
            }
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_live_cover)
        ImageView itemLiveCover;
        @BindView(R.id.tv_type)
        TextView tvType;
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
        }
    }
}
