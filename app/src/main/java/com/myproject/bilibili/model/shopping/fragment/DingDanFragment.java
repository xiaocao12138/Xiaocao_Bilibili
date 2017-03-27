package com.myproject.bilibili.model.shopping.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.myproject.bilibili.base.BaseFragment;

/**
 * Created by chen on 2017/3/27 16:16.
 * 作用:XXXX
 */

public class DingDanFragment extends BaseFragment {

    private TextView textView ;

    @Override
    public View initView() {

        textView  = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("22222222222");
    }
}
