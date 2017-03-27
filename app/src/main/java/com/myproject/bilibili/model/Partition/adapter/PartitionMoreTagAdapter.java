package com.myproject.bilibili.model.Partition.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.bilibili.R;
import com.myproject.bilibili.model.Partition.bean.PartitionMoreBean;
import com.myproject.bilibili.view.HotGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/23 19:56.
 * 作用:XXXX
 */

public class PartitionMoreTagAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<PartitionMoreBean.DataBean> data;
    private final LayoutInflater inflater;

    public PartitionMoreTagAdapter(Context mContext, List<PartitionMoreBean.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeViewHolder(mContext, inflater.inflate(R.layout.type_grid_view, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TypeViewHolder viewHolder = (TypeViewHolder) holder;
        //绑定数据
        viewHolder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class TypeViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.type_zhan_qu)
        TextView typeZhanQu;
        @BindView(R.id.tv_type_more)
        TextView tvTypeMore;
        @BindView(R.id.gv_live)
        HotGridView gvLive;

        TypeViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContext;
        }

        public void setData(PartitionMoreBean.DataBean data) {
            typeZhanQu.setText(data.getTitle());

            TypeViewAdapter adapter = new TypeViewAdapter(mContext , data.getBody());
            gvLive.setAdapter(adapter);

            gvLive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
