package com.myproject.bilibili.model.found;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/22 10:37.
 * 作用:XXXX
 */

public class ListMoreAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<TAgBean.DataBean.ItemsBean.ArchiveBean> archive;

    public ListMoreAdapter(Context mContext, List<TAgBean.DataBean.ItemsBean.ArchiveBean> archive) {
        this.mContext = mContext;
        this.archive = archive;
    }

    @Override
    public int getCount() {
        return archive.size();
    }

    @Override
    public Object getItem(int position) {
        return archive.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_more_zong, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TAgBean.DataBean.ItemsBean.ArchiveBean archiveBean = archive.get(position);
        Glide.with(mContext).load(archiveBean.getCover()).into(holder.ivIcon);
        holder.tvName.setText(archiveBean.getTitle());
        holder.tvDuration.setText(archiveBean.getDuration());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_duration)
        TextView tvDuration;
        @BindView(R.id.tv_size)
        TextView tvSize;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
