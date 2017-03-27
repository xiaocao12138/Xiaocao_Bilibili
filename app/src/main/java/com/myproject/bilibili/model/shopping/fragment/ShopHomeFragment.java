package com.myproject.bilibili.model.shopping.fragment;

import android.view.View;
import android.webkit.WebView;

import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/27 16:14.
 * 作用:XXXX
 */

public class ShopHomeFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView webview;

//    @BindView(R.id.rececleview)
//    RecyclerView rececleview;
//    private List<ShopBannerBean.ResultBean.ModelDetailsBean> modelDetails;
//    private List<ShopAllBean.ResultBean.RecordsBean> records;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

//        getDataFromNet01();
    }

    /*private void setWebViewData(String url) {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);

        //设置检索缓存的
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //设置不跳转到系统的浏览器
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }
        });

        webview.loadUrl(url);
    }*/

//    private void getDataFromNet01() {
//        OkHttpUtils.get()
//                .url(AppNetConfig.SHOPPING)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.e("TAG" , "onError" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.e("TAG" , "onResponse" + response);
//                        processData(response);
//                    }
//                });
//    }
//
//    private void processData(String json) {
//        ShopBannerBean shopBannerBean = JSON.parseObject(json, ShopBannerBean.class);
//        modelDetails = shopBannerBean.getResult().getModelDetails();
//        getDataFromNet02();
//    }
//
//    private void getDataFromNet02() {
//        OkHttpUtils.get()
//                .url(AppNetConfig.SHOPPING_ALL)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.e("TAG" , "onError" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.e("TAG" , "onResponse" + response);
//                        processData02(response);
//                    }
//                });
//    }
//
//    private void processData02(String json) {
//        ShopAllBean shopAllBean = JSON.parseObject(json, ShopAllBean.class);
//        records = shopAllBean.getResult().getRecords();
//        setAdapter();
//    }
//
//    private void setAdapter() {
//        if (modelDetails.size() > 0 && modelDetails != null && records.size() > 0 && records != null){
//
//            ShopHomeAdapter adapter = new ShopHomeAdapter(mContext , records, modelDetails);
//            rececleview.setAdapter(adapter);
//
//            rececleview.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false));
//        }else {
//            Toast.makeText(mContext, "联网失败", Toast.LENGTH_SHORT).show();
//        }
//    }
}
