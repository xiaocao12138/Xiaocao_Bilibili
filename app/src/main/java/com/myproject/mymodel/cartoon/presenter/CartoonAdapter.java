package com.myproject.mymodel.cartoon.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myproject.bilibili.R;
import com.myproject.mymodel.cartoon.bean.CartoonBean;
import com.myproject.mymodel.utils.HotGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/24 9:03.
 * 作用:XXXX
 */

public class CartoonAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<CartoonBean.ResultBean.SerializingBean> result;
    /**
     * BANNER
     */
    public static final int LIST = 0;

    /**
     * BANNER
     */
    public static final int GRID = 1;
    /**
     * GRID
     */
//    public static final int BANNER = 2;
    private final LayoutInflater inflater;

    /**
     * 当前类型
     */
    public int currentType = LIST;

    public CartoonAdapter(Context mContext, List<CartoonBean.ResultBean.SerializingBean> result) {
        this.mContext = mContext;
        this.result = result;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemViewType(int position) {
        if (position == LIST) {
            currentType = LIST;
        } else if (position == GRID) {
            currentType = GRID;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LIST) {
            return new TagViewHolder(mContext, inflater.inflate(R.layout.cartoon_tag, null));
        } else if (viewType == GRID) {
            return new GridViewHolder(mContext, inflater.inflate(R.layout.cartoon_grid, null));
        }/*else if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.cartoon_banner, null));
        }*/
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == LIST) {
            TagViewHolder viewHolder = (TagViewHolder) holder;
            //绑定数据
//            viewHolder.setData(data);
        }else if (getItemViewType(position) == GRID) {
            GridViewHolder viewHolder = (GridViewHolder) holder;
            //绑定数据
//            viewHolder.setData(data);
            viewHolder.setData(result);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.type_zhan_qu)
        TextView typeZhanQu;
        @BindView(R.id.tv_type_more)
        TextView tvTypeMore;
        @BindView(R.id.hot_grid_view)
        HotGridView hotGridView;

        GridViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }

        public void setData(List<CartoonBean.ResultBean.SerializingBean> headBean) {
//            Glide.with(mContext).load(headBean.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivHead);
            typeZhanQu.setText("");
            GridCartoonAdapter adapter = new GridCartoonAdapter(mContext, headBean);
            hotGridView.setAdapter(adapter);
        }
    }

    static class TagViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.rl_fan_ju)
        RelativeLayout rlFanJu;
        @BindView(R.id.rl_guo_man)
        RelativeLayout rlGuoMan;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.suoyin)
        TextView suoyin;
        @BindView(R.id.iv_zhui_fan)
        ImageView ivZhuiFan;

        TagViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }


    }
}
