package com.myproject.bilibili.model.live;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.live.bean.LiveBean;
import com.myproject.bilibili.utils.AppNetConfig;
import com.myproject.bilibili.view.CustomEmptyView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by chen on 2017/3/21 19:12.
 * 作用:直播页面
 */

public class LiveFragment extends BaseFragment {


    @BindView(R.id.recycle)
    RecyclerView recycle;

    @BindView(R.id.empty_layout)
    CustomEmptyView emptyLayout;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private LiveAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_live, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void initData() {
        super.initData();
        getDataFromNet();

    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(AppNetConfig.LIVE_TAG)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败===" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.e("TAG", "联网成功===" + response);
                        processData(response);
                    }

                });
    }

    private void processData(String json) {
        LiveBean liveBean = JSON.parseObject(json, LiveBean.class);
        LiveBean.DataBean data = liveBean.getData();
        if (data != null) {
            //设置适配器
            adapter = new LiveAdapter(mContext , data);
            recycle.setAdapter(adapter);
            //设置布局管理
            recycle.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        }else {
            Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
        }
    }
}
