package com.myproject.bilibili.model.found;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.found.bean.TAgBean;
import com.myproject.bilibili.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by chen on 2017/3/22 9:49.
 * 作用:XXXX
 */

public class zongF3ragment extends BaseFragment {


    @BindView(R.id.lv_more)
    ListView lvMore;
    private List<TAgBean.DataBean.ItemsBean.ArchiveBean> archive;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_zongf3, null);
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
                .url(AppNetConfig.TAB_TAG)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("TAG", "onResponse: " + response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        TAgBean tAgBean = JSON.parseObject(json, TAgBean.class);
        archive = tAgBean.getData().getItems().getArchive();
        ListMoreAdapter adapter = new ListMoreAdapter(mContext , archive);
        lvMore.setAdapter(adapter);
    }

}
