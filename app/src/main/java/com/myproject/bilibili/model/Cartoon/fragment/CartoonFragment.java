package com.myproject.bilibili.model.Cartoon.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.Cartoon.adapter.CartoonAdapter;
import com.myproject.bilibili.model.Cartoon.bean.CartoonBean;
import com.myproject.bilibili.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by chen on 2017/3/21 19:13.
 * 作用:追番页面
 */

public class CartoonFragment extends BaseFragment {


    @BindView(R.id.rececle_view)
    RecyclerView rececleView;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_cartoon, null);
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
                .url(AppNetConfig.CARTOON_MORE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(String json) {

        CartoonBean cartoonBean = JSON.parseObject(json, CartoonBean.class);
        CartoonBean.ResultBean result = cartoonBean.getResult();
        if (result != null) {
            CartoonAdapter adapter = new CartoonAdapter(mContext, result.getSerializing());
            rececleView.setAdapter(adapter);

            rececleView.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false));
        }


    }

}
