package com.myproject.mymodel.shopping.presenter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.myproject.bilibili.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/27 18:38.
 * 作用:XXXX
 */

public class ShopGridAdapter extends BaseAdapter {
    private final Context mContext;
    private String[] tltles = {"全部", "3C周边", "抱枕萌物", "梦100周边", "官方淘宝店"};

    public ShopGridAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return tltles.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_shop_grid, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.but.setText(tltles[position]);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.but)
        Button but;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
