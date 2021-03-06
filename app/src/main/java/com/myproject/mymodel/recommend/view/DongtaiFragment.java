package com.myproject.mymodel.recommend.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.myproject.bilibili.R;
import com.myproject.mymodel.base.BaseFragment;
import com.myproject.mymodel.recommend.bean.RecommendBean;
import com.myproject.mymodel.recommend.presenter.adapter.ZongheAdapter;
import com.myproject.mymodel.utils.AppNetConfig;
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

    @BindView(R.id.recycle)
    RecyclerView recycle;

    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private ZongheAdapter adapter;
    private List<RecommendBean.DataBean> data;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_zonghe, null);
        ButterKnife.bind(this, view);

        /**
         * 上拉下拉顺心
         */
        refresh.setMaterialRefreshListener(new MyMaterialRefreshListener());

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }
    /**
     * 判断上拉下拉是否刷新
     */
    private boolean isLoadMore = false;


    /**
     * 上拉下拉刷新
     */
    class MyMaterialRefreshListener extends MaterialRefreshListener {

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            isLoadMore = false;
            getDataFromNet();
        }

        @Override
        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            super.onRefreshLoadMore(materialRefreshLayout);
            isLoadMore = true;
            getDataFromNet();

        }
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

                        if(!isLoadMore){
                            //完成刷新
                            refresh.finishRefresh();
                        }else{
                            //把上拉的隐藏
                            refresh.finishRefreshLoadMore();
                        }

                    }
                });
    }

    private void processData(String json) {
        if (!isLoadMore){
            RecommendBean recommendBean = JSON.parseObject(json, RecommendBean.class);
            data = recommendBean.getData();
            setAdapter();
        }else {
            RecommendBean bean = JSON.parseObject(json, RecommendBean.class);
            data.addAll(bean.getData());
            //刷新适配器
            adapter.notifyDataSetChanged();//getCount-->getView
        }


    }

    private void setAdapter() {
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
