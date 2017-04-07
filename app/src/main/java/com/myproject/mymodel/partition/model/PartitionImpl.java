package com.myproject.mymodel.partition.model;

import com.myproject.mymodel.base.BaseListener;
import com.myproject.mymodel.live.model.LiveListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by chen on 2017/4/7 10:46.
 * 作用:XXXX
 */

public class PartitionImpl implements PartitionModel {

    @Override
    public void getDataFromNet(String url, final BaseListener listener) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (listener != null){
                            listener.onFailed(e);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (listener != null){
                            listener.onSuccess(response);
                        }
                    }
                });
    }

    @Override
    public void getDataFromNet(String url, final LiveListener listener) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (listener != null){
                            listener.onFailed(e);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (listener != null){
                            listener.onSuccess(response);
                        }
                    }
                });
    }
}
