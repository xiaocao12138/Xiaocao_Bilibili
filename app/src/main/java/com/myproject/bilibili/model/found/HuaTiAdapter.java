package com.myproject.bilibili.model.found;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.bilibili.model.found.bean.HuaTiBean;
import com.myproject.bilibili.utils.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/21 16:37.
 * 作用:XXXX
 */

public class HuaTiAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<HuaTiBean.ListBean> list;

    public HuaTiAdapter(Context context, List<HuaTiBean.ListBean> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = View.inflate(mContext , R.layout.item_hua_ti , null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        HuaTiBean.ListBean listBean = list.get(position);

        holder.tvContent.setText(listBean.getTitle());


        boolean networkAvailable = CommonUtil.isNetworkAvailable(mContext);
        boolean isMobile = CommonUtil.isMobile(mContext);
        if (networkAvailable){
            if (isMobile){
                holder.ivIcon.setImageResource(R.drawable.aa);
                Toast.makeText(mContext, "非wifi状态下不显示图片", Toast.LENGTH_SHORT).show();
            }else {
                Glide.with(mContext)
                        .load(listBean.getCover())
                        .into(holder.ivIcon);
            }
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
