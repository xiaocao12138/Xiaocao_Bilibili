package com.myproject.bilibili.model.found;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.bilibili.utils.AppNetConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/21 16:37.
 * 作用:XXXX
 */

public class HuaTiAdapter extends Adapter<HuaTiAdapter.ViewHolder> {

    private final Context context;
    private final List<HuaTiBean.ListBean> list;
    private final LayoutInflater inflater;

    public HuaTiAdapter(Context context, List<HuaTiBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_hua_ti, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvContent.setText(list.get(position).getTitle());
        Glide.with(context)
                .load(AppNetConfig.DISCOVER_HUATI + list.get(position)
                .getCover()).into(holder.ivIcon);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
