package com.myproject.mymodel.recommend.model;

import android.util.Log;

import com.myproject.mymodel.base.BaseListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by chen on 2017/4/6 21:08.
 * 作用:XXXX
 */

public class RecommendImpl implements RecommendModel {

    @Override
    public void getDataFromNet(String url, final BaseListener listener) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "onError: "+e.getMessage() );
                        if (listener != null){
                            listener.onFailed(e);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "onResponse: "+response );
                        if (listener != null){
                            listener.onSuccess(response);
                        }
                    }
                });
    }
}
