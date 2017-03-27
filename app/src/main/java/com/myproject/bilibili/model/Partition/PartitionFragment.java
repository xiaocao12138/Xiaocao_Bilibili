package com.myproject.bilibili.model.Partition;

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
    private List<PartitionBean.DataBean> data;
    private List<PartitionMoreBean.DataBean> moreData;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_partition, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet01();

    }


    private void getDataFromNet02() {
        OkHttpUtils
                .get()
                .url(AppNetConfig.PARTITION_MORE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败===" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Log.e("TAG", "联网成功===" + response);
                        processDataMore(response);
                    }

                });
    }

    private void processDataMore(String json) {
        PartitionMoreBean partitionMoreBean = JSON.parseObject(json, PartitionMoreBean.class);
        moreData = partitionMoreBean.getData();

        setAdapter();

    }

    private void getDataFromNet01() {
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


    private void processData(String json) {
        PartitionBean partitionBean = JSON.parseObject(json, PartitionBean.class);
        data = partitionBean.getData();
        getDataFromNet02();

    }

    private void setAdapter() {
        if (data != null && data.size() > 0 && moreData != null && moreData.size() >0) {

            PartitionTagAdapter adapter = new PartitionTagAdapter(mContext, moreData, data);
            recyclerView.setAdapter(adapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        } else {
            Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
//
//            getDataFromNet01();
//
//            getDataFromNet02();
        }

        /*if (data != null) {
            PartitionMoreTagAdapter adapter = new PartitionMoreTagAdapter(mContext, data);
            recyclerView.setAdapter(adapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        } else {
            Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
        }*/
    }

}
