package com.myproject.mymodel.utils;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by chen on 2017/4/6 9:03.
 * 作用:XXXX
 */

public class HttpUtils {

    private static HttpUtils httpUtils;

    public HttpUtils() {
        httpUtils = new HttpUtils();
    }

    public static void getData(String url , final Listener listener){
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
                        if (listener != null){
                            listener.onSuccess(response);
                        }
                    }
                });
    }

    public static void getDataMore(String url , String moreurl , final Listener listener){
        OkHttpUtils.get()
                .url(url)
                .url(moreurl)
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
                        if (listener != null){
                            listener.onSuccess(response);
                        }
                    }
                });
    }

    public interface Listener{

        void onSuccess(String response);

        void onFailed(Exception e);
    }
}
