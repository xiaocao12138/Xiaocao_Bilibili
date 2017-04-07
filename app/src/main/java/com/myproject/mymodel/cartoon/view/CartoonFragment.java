package com.myproject.mymodel.cartoon.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.mymodel.base.BaseFragment;
import com.myproject.mymodel.cartoon.presenter.CartoonAdapter;
import com.myproject.mymodel.cartoon.bean.CartoonBean;
import com.myproject.mymodel.cartoon.presenter.CartoonPresenter;
import com.myproject.mymodel.utils.AppNetConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/4/6 23:42.
 * 作用:XXXX
 */

public class CartoonFragment extends BaseFragment implements CartoonView {

    @BindView(R.id.rececle_view)
    RecyclerView rececleView;
    private CartoonPresenter cartoonPresenter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_cartoon, null);
        ButterKnife.bind(this, view);
        cartoonPresenter = new CartoonPresenter(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        cartoonPresenter.getData(AppNetConfig.CARTOON_MORE);

    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    public void processData(String response) {
        CartoonBean cartoonBean = JSON.parseObject(response, CartoonBean.class);
        CartoonBean.ResultBean result = cartoonBean.getResult();
        if (result != null) {
            CartoonAdapter adapter = new CartoonAdapter(mContext, result.getSerializing());
            rececleView.setAdapter(adapter);

            rececleView.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false));
        }
    }
}
