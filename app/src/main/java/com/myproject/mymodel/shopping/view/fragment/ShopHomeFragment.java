package com.myproject.mymodel.shopping.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.mymodel.base.BaseFragment;
import com.myproject.mymodel.shopping.presenter.adapter.ShopHomeAdapter;
import com.myproject.mymodel.shopping.bean.ShopAllBean;
import com.myproject.mymodel.shopping.bean.ShopBannerBean;
import com.myproject.mymodel.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by chen on 2017/3/27 16:14.
 * 作用:XXXX
 */

public class ShopHomeFragment extends BaseFragment {


    @BindView(R.id.rececleview)
    RecyclerView rececleview;
    private List<ShopBannerBean.ResultBean.ModelDetailsBean> modelDetails;
    private List<ShopAllBean.ResultBean.RecordsBean> records;
    private ShopHomeAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet01();
    }

    private void getDataFromNet01() {
        OkHttpUtils.get()
                .url(AppNetConfig.SHOPPING)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG" , "onError" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG" , "onResponse" + response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        ShopBannerBean shopBannerBean = JSON.parseObject(json, ShopBannerBean.class);
        modelDetails = shopBannerBean.getResult().getModelDetails();
        getDataFromNet02();
    }

    private void getDataFromNet02() {
        OkHttpUtils.get()
                .url(AppNetConfig.SHOPPING_ALL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG" , "onError" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG" , "onResponse" + response);
                        processData02(response);
                    }
                });
    }

    private void processData02(String json) {
        ShopAllBean shopAllBean = JSON.parseObject(json, ShopAllBean.class);
        records = shopAllBean.getResult().getRecords();
        setAdapter();
    }

    private void setAdapter() {
        if (modelDetails.size() > 0 && modelDetails != null && records.size() > 0 && records != null){

            adapter = new ShopHomeAdapter(mContext , records, modelDetails);
            rececleview.setAdapter(adapter);

            rececleview.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false));
        }else {
            Toast.makeText(mContext, "联网失败", Toast.LENGTH_SHORT).show();
        }
    }


}
