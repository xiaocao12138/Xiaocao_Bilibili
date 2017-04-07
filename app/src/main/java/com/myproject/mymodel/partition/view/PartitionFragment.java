package com.myproject.mymodel.partition.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.mymodel.base.BaseFragment;
import com.myproject.mymodel.partition.presenter.adapter.PartitionTagAdapter;
import com.myproject.mymodel.partition.bean.PartitionBean;
import com.myproject.mymodel.partition.bean.PartitionMoreBean;
import com.myproject.mymodel.base.BaseListener;
import com.myproject.mymodel.partition.presenter.PartitionPresenter;
import com.myproject.mymodel.utils.AppNetConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/4/7 10:47.
 * 作用:XXXX
 */

public class PartitionFragment extends BaseFragment implements PartitionView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<PartitionBean.DataBean> data;
    private List<PartitionMoreBean.DataBean> moreData;
    private PartitionPresenter partitionPresenter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_partition, null);
        ButterKnife.bind(this, view);
        partitionPresenter = new PartitionPresenter(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        partitionPresenter.getDataFromNet(AppNetConfig.PARTITION_TAG);

    }

    private void setAdapter() {
        if (data != null && data.size() > 0 && moreData != null && moreData.size() >0) {

            PartitionTagAdapter adapter = new PartitionTagAdapter(mContext, moreData, data);
            recyclerView.setAdapter(adapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        } else {
            Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailed(Exception ex) {
        Toast.makeText(mContext, "onFailed"+ex.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void processData(String response) {
        PartitionBean partitionBean = JSON.parseObject(response, PartitionBean.class);
        int code = partitionBean.getCode();
        if (code == 0){
            data = partitionBean.getData();
            partitionPresenter.getDataFromNet(AppNetConfig.PARTITION_MORE, new BaseListener() {
                @Override
                public void onSuccess(String response) {
                    PartitionMoreBean partitionMoreBean = JSON.parseObject(response, PartitionMoreBean.class);
                    moreData = partitionMoreBean.getData();
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
