package com.myproject.bilibili.model.found;

import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.found.adapter.YuanChaungAdapter;
import com.myproject.bilibili.model.found.bean.YuanChaungBean;
import com.myproject.bilibili.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


/**
 * Created by chen on 2017/3/21 17:13.
 * 作用:XXXX
 */

public class OriginalFragment extends BaseFragment {
    @BindView(R.id.list_view)
    ListView listView;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_original, null);
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
                .url(AppNetConfig.YUANCHUANG)
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
        YuanChaungBean yuanChaungBean = JSON.parseObject(json, YuanChaungBean.class);
        YuanChaungAdapter adapter = new YuanChaungAdapter(mContext , yuanChaungBean.getData());
        listView.setAdapter(adapter);
    }

}
