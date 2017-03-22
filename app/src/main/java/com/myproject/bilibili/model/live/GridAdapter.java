package com.myproject.bilibili.model.live;

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
        LiveBean.DataBean.PartitionsBean partitionsBean = partitions.get(position);

        Glide.with(mContect).load(partitionsBean.getLives().get(position).getCover().getSrc()).into(holder.ivGrid);
        holder.tvHomeName.setText(partitionsBean.getLives().get(position).getTitle());
        holder.tvPerName.setText(partitionsBean.getLives().get(position).getOwner().getName());
//        holder.number.setText(partitionsBean.getLives().get(position).getOnline());

        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.iv_grid)
        ImageView ivGrid;
        @BindView(R.id.tv_home_name)
        TextView tvHomeName;
        @BindView(R.id.tv_per_name)
        TextView tvPerName;
        @BindView(R.id.number)
        TextView number;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
