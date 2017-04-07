package com.myproject.mymodel.found.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myproject.bilibili.R;
import com.myproject.mymodel.found.bean.HuaTiBean;
import com.myproject.mymodel.found.presenter.adapter.HuaTiAdapter;
import com.myproject.mymodel.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class HuodongActivity extends AppCompatActivity {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.list_view)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huodong);

        toolBar.setTitle("活动中心");
        toolBar.setTitleTextColor(Color.WHITE);

        getDataFromNet();

        ButterKnife.bind(this);
    }


    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(AppNetConfig.DISCOVER_HUATI)
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
        JSONObject jsonObject = JSONObject.parseObject(json);
        Integer code = jsonObject.getInteger("code");
        if (code == 0) {
            HuaTiBean huaTiBean = JSON.parseObject(json, HuaTiBean.class);
            List<HuaTiBean.ListBean> list = huaTiBean.getList();

            HuaTiAdapter adapter = new HuaTiAdapter(this, list);
            listView.setAdapter(adapter);


        } else {
            Log.e("TAG", "HuatiActivity initData()联网失败");
        }

    }
}
