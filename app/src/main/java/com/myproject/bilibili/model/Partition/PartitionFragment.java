package com.myproject.bilibili.model.Partition;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.Partition.adapter.PartitionTagAdapter;
import com.myproject.bilibili.model.Partition.bean.PartitionBean;
import com.myproject.bilibili.model.Partition.bean.PartitionMoreBean;
import com.myproject.bilibili.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by chen on 2017/3/21 19:14.
 * 作用:分区页面
 */

public class PartitionFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_partition, null);
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
                .url(AppNetConfig.PARTITION_TAG)
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

        OkHttpUtils
                .get()
                .url(AppNetConfig.PARTITION_TAG)
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

    /*private void processDataMore(String response) {
        PartitionMoreBean partitionMoreBean = JSON.parseObject(response, PartitionMoreBean.class);
        List<PartitionMoreBean.DataBean> data = partitionMoreBean.getData();
        if (data != null){
            PartitionTagAdapter adapter = new PartitionTagAdapter(mContext , data);
            recyclerView.setAdapter(adapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false));

        }else {
            Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
        }
    }*/

    private void processData(String json) {
        PartitionBean partitionBean = JSON.parseObject(json, PartitionBean.class);
        List<PartitionBean.DataBean> data = partitionBean.getData();
/*
        PartitionBean partitionBean = JSON.parseObject(json, PartitionBean.class);
        List<PartitionBean.DataBean> data = partitionBean.getData();*/
        if (data != null){
            PartitionTagAdapter adapter = new PartitionTagAdapter(mContext , data);
            recyclerView.setAdapter(adapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false));

        }else {
            Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
        }

    }
}
