package com.myproject.bilibili.model.found;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.myproject.bilibili.base.BaseFragment;

/**
 * Created by chen on 2017/3/22 9:49.
 * 作用:XXXX
 */

public class MediaFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("MediaFragment");
    }
}
