package com.myproject.mymodel.found.presenter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.mymodel.found.bean.TAgBean;
import com.myproject.mymodel.utils.CommonUtil;

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
//        Glide.with(mContext).load(archiveBean.getCover()).into(holder.ivIcon);
        holder.tvName.setText(archiveBean.getTitle());
        holder.tvDuration.setText(archiveBean.getDuration());


        boolean networkAvailable = CommonUtil.isNetworkAvailable(mContext);
        boolean isMobile = CommonUtil.isMobile(mContext);
        if (networkAvailable){
            if (isMobile){
                holder.ivIcon.setImageResource(R.drawable.aa);
                Toast.makeText(mContext, "非wifi状态下不显示图片", Toast.LENGTH_SHORT).show();
            }else {
                Glide.with(mContext).load(archiveBean.getCover()).into(holder.ivIcon);
            }
        }

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
