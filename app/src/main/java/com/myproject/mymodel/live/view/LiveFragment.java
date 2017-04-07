package com.myproject.mymodel.live.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.mymodel.base.BaseFragment;
import com.myproject.mymodel.base.BaseListener;
import com.myproject.mymodel.live.bean.LIveRecomendBean;
import com.myproject.mymodel.live.bean.LiveBean;
import com.myproject.mymodel.live.prestener.adapter.LiveAdapter;
import com.myproject.mymodel.live.prestener.LivePresenter;
import com.myproject.mymodel.utils.AppNetConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/4/6 18:52.
 * 作用:XXXX
 */

public class LiveFragment extends BaseFragment implements LiveView {

    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private LiveAdapter adapter;
    private int position;
    private LivePresenter livePresenter;
    private LiveBean.DataBean data;
    private LIveRecomendBean.DataBean.RecommendDataBean recommendData;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_live, null);
        ButterKnife.bind(this, view);
        livePresenter = new LivePresenter(this);
        return view;
    }


    @Override
    public void initData() {
        super.initData();
        livePresenter.getDataFromNet(AppNetConfig.LIVE_TAG);
        initSwipeRefreshLayout();

    }

    private void initSwipeRefreshLayout() {

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                livePresenter.getDataFromNet(AppNetConfig.LIVE_TAG);

            }
        });

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    private void setAdapter() {
        if (data != null && recommendData != null) {
            //设置适配器
            adapter = new LiveAdapter(mContext, data , recommendData);
            recycle.setAdapter(adapter);

            //设置布局管理
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
            livePresenter.getDataFromNet(AppNetConfig.LIVE_TAG);
        }
    }

    @Override
    public void onFailed(Exception ex) {
        Log.i("TAG", "onFailed: "+ex.getMessage());
    }

    @Override
    public void processData(String response) {

        LiveBean liveBean = JSON.parseObject(response, LiveBean.class);
        int code = liveBean.getCode();
        if (code == 0){
            data = liveBean.getData();
            livePresenter.getDataFromNet(AppNetConfig.LIVE_RECCOMEND, new BaseListener() {
                @Override
                public void onSuccess(String response) {
                    LIveRecomendBean LiveRecomendBean = JSON.parseObject(response, LIveRecomendBean.class);
                    recommendData = LiveRecomendBean.getData().getRecommend_data();
                    setAdapter();
                }

                @Override
                public void onFailed(Exception e) {
                    Log.e("TAG", "onFailed: "+e.getMessage() );
                }
            });
        }
    }
}
