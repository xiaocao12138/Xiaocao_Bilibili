package com.myproject.bilibili.model.Cartoon.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.view.HotGridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chen on 2017/3/21 19:13.
 * 作用:追番页面
 */

public class CartoonFragment extends BaseFragment {


    @BindView(R.id.rl_fan_ju)
    RelativeLayout rlFanJu;
    @BindView(R.id.rl_guo_man)
    RelativeLayout rlGuoMan;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.suoyin)
    TextView suoyin;
    @BindView(R.id.iv_zhui_fan)
    ImageView ivZhuiFan;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.type_zhan_qu)
    TextView typeZhanQu;
    @BindView(R.id.tv_type_more)
    TextView tvTypeMore;
    @BindView(R.id.gridView)
    HotGridView gridView;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_cartoon, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

//        getDataFromNet();
    }

    /*private void getDataFromNet() {
        OkHttpUtils.get()
                .url()
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }*/


    @OnClick({R.id.rl_fan_ju, R.id.rl_guo_man, R.id.time, R.id.suoyin, R.id.iv_zhui_fan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_fan_ju:
                Toast.makeText(mContext, "番剧", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_guo_man:
                Toast.makeText(mContext, "国漫", Toast.LENGTH_SHORT).show();
                break;
            case R.id.time:
                Toast.makeText(mContext, "时间表", Toast.LENGTH_SHORT).show();
                break;
            case R.id.suoyin:
                Toast.makeText(mContext, "索引", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_zhui_fan:
                ivZhuiFan.setVisibility(View.VISIBLE);
                break;
        }
    }
}
