package com.myproject.mymodel.partition.presenter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.mymodel.partition.bean.PartitionMoreBean;
import com.myproject.mymodel.utils.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/23 11:01.
 * 作用:XXXX
 */

public class TypeViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<PartitionMoreBean.DataBean.BodyBean> data;

    public TypeViewAdapter(Context mContext, List<PartitionMoreBean.DataBean.BodyBean> body) {
        this.mContext = mContext;
        this.data = body;
    }

    @Override
    public int getCount() {
        return data.size()/2*2;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_type_more, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PartitionMoreBean.DataBean.BodyBean bodyBean = data.get(position);

        boolean networkAvailable = CommonUtil.isNetworkAvailable(mContext);
        boolean isMobile = CommonUtil.isMobile(mContext);
        if (networkAvailable){
            if (isMobile){
                holder.ivType.setImageResource(R.drawable.aa);
                Toast.makeText(mContext, "非wifi状态下不显示图片", Toast.LENGTH_SHORT).show();
            }else {
                Glide.with(mContext).load(bodyBean.getCover()).into(holder.ivType);
            }
        }

//        Glide.with(mContext).load(bodyBean.getCover()).into(holder.ivType);
        holder.tvTypeName.setText(String.valueOf(bodyBean.getTitle()));
        holder.tvPlayNumber.setText(String.valueOf(bodyBean.getPlay()));
        holder.tvDanmuNumber.setText(String.valueOf(bodyBean.getDanmaku()));
        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.iv_type)
        ImageView ivType;
        @BindView(R.id.tv_type_name)
        TextView tvTypeName;
        @BindView(R.id.tv_play_number)
        TextView tvPlayNumber;
        @BindView(R.id.tv_danmu_number)
        TextView tvDanmuNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.setOnClick(v);
                    }
                }
            });
        }
    }



    public interface OnItemClickListener{
        void setOnClick(View view);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
