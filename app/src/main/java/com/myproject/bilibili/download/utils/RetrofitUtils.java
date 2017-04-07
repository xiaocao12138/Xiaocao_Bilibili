package com.myproject.bilibili.download.utils;


import com.myproject.bilibili.download.listener.ResponseListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;


public class RetrofitUtils {

    private static final RetrofitUtils retrofitUtils = new RetrofitUtils();


    public static RetrofitUtils getInstance() {
        return retrofitUtils;
    }

    public static final String URL ="http://47.93.118.241:8081/";

    public  void download(final File file, final ResponseListener listener) {
        //创建Retrofit
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(URL);
        //使用OkHttpClient的拦截器去拦截Response  将自定义的ProgressReponseBody设置进去 得到下载的进度
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Response orginalResponse = chain.proceed(chain.request());

                        return orginalResponse.newBuilder()
                                .body(new ProgressResponseBody(orginalResponse.body(), listener))
                                .build();
                    }
                })
                .build();
        //使用OkHttpClient设置到Retrofit中 创建任务 并传入业务接口
        DownLoadApk api = retrofit.client(client)
                .build().create(DownLoadApk.class);

        //使用业务接口对象 开启任务 开始请求
        Call<ResponseBody> call = api.downLoadApk();
        call.enqueue(new Callback<ResponseBody>() {
            //请求成功 把流转换成文件 写入到指定file中
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                InputStream is = null;
                FileOutputStream fos = null;
                BufferedInputStream bis = null;
                try {
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        fos.flush();
                    }
                    //while写入成功 回调下载成功方法
                    listener.onResponse();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    /**
     * 业务接口
     */
    public interface DownLoadApk {
        //拼接URL后的部分 组成真正的请求地址
        @GET("P2PInvest/app_new.apk")
        Call<ResponseBody> downLoadApk();
    }

    public static String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

}
