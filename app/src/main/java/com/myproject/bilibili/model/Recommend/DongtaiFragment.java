package com.myproject.bilibili.model.Recommend;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by chen on 2017/3/21 20:55.
 * 作用:XXXX
 */

public class DongtaiFragment extends BaseFragment {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycle)
    RecyclerView recycle;


    private ZongheAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_zonghe, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(AppNetConfig.RECOMMEND_ONE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.i("TAG", "onResponse: "+response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        RecommendBean recommendBean = JSON.parseObject(json, RecommendBean.class);
        List<RecommendBean.DataBean> data = recommendBean.getData();

//        Log.e("TAG", "=========" + result.getHot_info().get(0).getName());
        if (data != null) {
            //设置适配器
            adapter = new ZongheAdapter(mContext, data);
            recycle.setAdapter(adapter);
            //设置布局管理
            GridLayoutManager manager = new GridLayoutManager(mContext, 2);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });
            recycle.setLayoutManager(manager);

        } else {
            Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
        }
    }
}
