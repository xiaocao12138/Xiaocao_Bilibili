package com.myproject.bilibili.model.found;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.myproject.bilibili.base.BaseFragment;

/**
 * Created by chen on 2017/3/21 20:16.
 * 作用:XXXX
 */

public class FirstFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        return null;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("sssssssssssssssssss");
    }
}
