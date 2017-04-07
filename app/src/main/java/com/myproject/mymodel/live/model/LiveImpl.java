package com.myproject.mymodel.live.model;

import com.myproject.mymodel.base.BaseListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by chen on 2017/4/6 18:49.
 * 作用:XXXX
 */

public class LiveImpl implements LiveModel {


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
