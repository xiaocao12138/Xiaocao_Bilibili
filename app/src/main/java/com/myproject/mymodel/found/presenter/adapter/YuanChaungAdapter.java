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
import com.myproject.mymodel.found.bean.YuanChaungBean;
import com.myproject.mymodel.utils.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/27 14:23.
 * 作用:XXXX
 */

public class YuanChaungAdapter extends BaseAdapter {
    private final List<YuanChaungBean.DataBean> data;
    private final Context mContext;

    public YuanChaungAdapter(Context mContext, List<YuanChaungBean.DataBean> dataBeen) {
        this.mContext = mContext;
        this.data = dataBeen;
    }

    @Override
    public int getCount() {
        return data.size();
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
            convertView = View.inflate(mContext, R.layout.item_yuan_chaung, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        YuanChaungBean.DataBean dataBean = data.get(position);

        holder.tvShu.setText(String.valueOf(position + 1));

        holder.tvZhuti.setText(dataBean.getTitle());
        holder.upZhu.setText(dataBean.getName());
        holder.tvPingfen.setText(String.valueOf(dataBean.getPts()));

        boolean networkAvailable = CommonUtil.isNetworkAvailable(mContext);
        boolean isMobile = CommonUtil.isMobile(mContext);
        if (networkAvailable){
            if (isMobile){
                holder.imageview.setImageResource(R.drawable.aa);
                Toast.makeText(mContext, "非wifi状态下不显示图片", Toast.LENGTH_SHORT).show();
            }else {
                Glide.with(mContext).load(dataBean.getCover()).crossFade().into(holder.imageview);
            }
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_shu)
        TextView tvShu;
        @BindView(R.id.imageview)
        ImageView imageview;
        @BindView(R.id.tv_zhuti)
        TextView tvZhuti;
        @BindView(R.id.up_zhu)
        TextView upZhu;
        @BindView(R.id.tv_pingfen)
        TextView tvPingfen;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
