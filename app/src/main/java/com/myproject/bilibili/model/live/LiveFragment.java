package com.myproject.bilibili.model.live;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.live.bean.LIveRecomendBean;
import com.myproject.bilibili.model.live.bean.LiveBean;
import com.myproject.bilibili.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by chen on 2017/3/21 19:12.
 * 作用:直播页面
 */

public class LiveFragment extends BaseFragment {


    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.but_load_more)
    Button butLoadMore;


    private LiveAdapter adapter;
    private int position;
    private LiveBean.DataBean data;
    private LIveRecomendBean.DataBean.RecommendDataBean recommendData;

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

        initSwipeRefreshLayout();

    }

    private void initSwipeRefreshLayout() {

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromNet();
            }
        });

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
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
        data = liveBean.getData();
        getDataFromNet02();

    }

    private void getDataFromNet02() {
        OkHttpUtils
                .get()
                .url(AppNetConfig.LIVE_RECCOMEND)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败===" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.e("TAG", "联网成功===" + response);
                        processData02(response);

                    }

                });
    }

    private void processData02(String response) {
        LIveRecomendBean lIveRecomendBean = JSON.parseObject(response, LIveRecomendBean.class);
        recommendData = lIveRecomendBean.getData().getRecommend_data();

        setAdapter();
    }

    private void setAdapter() {
        if (data != null && recommendData != null) {
            //设置适配器
            adapter = new LiveAdapter(mContext, data , recommendData);
            recycle.setAdapter(adapter);
            //设置布局管理
//            recycle.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

            GridLayoutManager manager = new GridLayoutManager(mContext, 1, LinearLayoutManager.VERTICAL, false);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    LiveFragment.this.position = position;
                    return 1;
                }
            });

            recycle.setLayoutManager(manager);


        } else {
            Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
            getDataFromNet();
        }
    }

    @OnClick(R.id.but_load_more)
    public void onClick() {
        Toast.makeText(mContext, "111111111111", Toast.LENGTH_SHORT).show();
    }
}
