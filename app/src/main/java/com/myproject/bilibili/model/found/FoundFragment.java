package com.myproject.bilibili.model.found;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.github.hymanme.tagflowlayout.OnTagClickListener;
import com.github.hymanme.tagflowlayout.TagAdapter;
import com.github.hymanme.tagflowlayout.TagFlowLayout;
import com.github.hymanme.tagflowlayout.tags.ColorfulTagView;
import com.github.hymanme.tagflowlayout.tags.DefaultTagView;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.cemare.ImageUtil;
import com.myproject.bilibili.model.found.activity.ShoppingActivity;
import com.myproject.bilibili.model.found.bean.FoundBean;
import com.myproject.bilibili.model.found.bean.ShopBean;
import com.myproject.bilibili.search.SearchFragment;
import com.myproject.bilibili.search.custom.IOnSearchClickListener;
import com.myproject.bilibili.utils.AppNetConfig;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by chen on 2017/3/21 19:13.
 * 作用:发现页面
 */

public class FoundFragment extends BaseFragment {

    public static final String NAME = "name";
    public static final String TITLE = "title";
    public static final String URL = "url";

    @BindView(R.id.edt_search)
    TextView edtSearch;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tag_flow_layout)
    TagFlowLayout tagFlowLayout;
    @BindView(R.id.tv_xingqu_quan)
    TextView tvXingquQuan;
    @BindView(R.id.tv_huatizhong_xin)
    TextView tvHuatizhongXin;
    @BindView(R.id.tv_huodong_zhong_xin)
    TextView tvHuodongZhongXin;
    @BindView(R.id.tv_yuan_chuang)
    TextView tvYuanChuang;
    @BindView(R.id.tv_quan_qu)
    TextView tvQuanQu;
    @BindView(R.id.tv_you_xi)
    TextView tvYouXi;
    @BindView(R.id.tv_shopping)
    TextView tvShopping;


    private LayoutInflater mInflater;
    private List<FoundBean.DataBean.ListBean> list;
    private ShopBean.ResultBean result;
    private String url;
    private SearchFragment searchFragment;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_found, null);
        ButterKnife.bind(this, view);
        mInflater = LayoutInflater.from(getActivity());
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        searchFragment = SearchFragment.newInstance();

        getDataFromNet01();

        getDataFromNet02();
    }

    private void getDataFromNet02() {
        OkHttpUtils.get().url(AppNetConfig.DISCOVER_SHOP).build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
//                Log.i("TAG", "onResponse: "+response);
                processDataShop(response);
            }
        });
    }

    private void processDataShop(String json) {
        ShopBean shopBean = JSON.parseObject(json, ShopBean.class);
        result = shopBean.getResult();
        url = result.getResourceLink();
    }

    private void getDataFromNet01() {
        OkHttpUtils.get().url(AppNetConfig.DISCOVER_TAG).build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
//                Log.i("TAG", "onResponse: "+response);
                processDataFound(response);
            }
        });
    }

    private void processDataFound(String json) {

        FoundBean foundBean = JSON.parseObject(json, FoundBean.class);

        list = foundBean.getData().getList();
        initColor();


        MyTagAdapter tagAdapter = new MyTagAdapter();
        tagAdapter.addAllTags(list);

        tagFlowLayout.setTagAdapter(tagAdapter);

        initListener();

    }

    private void initListener() {
        tagFlowLayout.setTagListener(new OnTagClickListener() {
            @Override
            public void onClick(TagFlowLayout parent, View view, int position) {
                Intent intent = new Intent(getActivity(), TabMoreAcivity.class);
                intent.putExtra(NAME, list.get(position).getKeyword());
                mContext.startActivity(intent);
            }

            @Override
            public void onLongClick(TagFlowLayout parent, View view, int position) {

            }
        });

        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                startActivity(new Intent(getActivity() , TabMoreAcivity.class));
            }
        });
    }

    private void initColor() {
        tagFlowLayout.setTitle("大家都不想搜");
        tagFlowLayout.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tagFlowLayout.setTitleTextSize(12);
        //最小显示高度(单位dp)
        tagFlowLayout.setMinVisibleHeight(100);
        //最大显示高度(单位dp)
        tagFlowLayout.setMaxVisibleHeight(400);
        tagFlowLayout.setAnimationDuration(600);
        //设置背景颜色
        tagFlowLayout.setBackGroundColor(getResources().getColor(R.color.primary_text));
    }

    class MyTagAdapter extends TagAdapter<FoundBean.DataBean.ListBean> {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //定制tag的样式，包括背景颜色，点击时背景颜色，背景形状等
            DefaultTagView textView = new ColorfulTagView(mContext);
            textView.setText(list.get(position).getKeyword());
            return textView;
        }
    }

    @OnClick({R.id.edt_search , R.id.iv_scan ,R.id.tv_xingqu_quan, R.id.tv_huatizhong_xin, R.id.tv_huodong_zhong_xin,
            R.id.tv_yuan_chuang, R.id.tv_quan_qu, R.id.tv_you_xi, R.id.tv_shopping})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edt_search:
                searchFragment.show(getActivity().getSupportFragmentManager(),SearchFragment.TAG);
                break;
            case R.id.iv_scan:
//                Toast.makeText(mContext, "111111 11111111", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity() , CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_xingqu_quan:
                mContext.startActivity(new Intent(getActivity(), XingquActivity.class));
                break;
            case R.id.tv_huatizhong_xin:
                mContext.startActivity(new Intent(getActivity(), HuaTiActivity.class));
                break;
            case R.id.tv_huodong_zhong_xin:
                mContext.startActivity(new Intent(getActivity(), HuaTiActivity.class));
                break;
            case R.id.tv_yuan_chuang:
                mContext.startActivity(new Intent(getActivity(), OriginalActivity.class));
                break;
            case R.id.tv_quan_qu:
                mContext.startActivity(new Intent(getActivity(), OriginalActivity.class));
                break;
            case R.id.tv_you_xi:
                Toast.makeText(mContext, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_shopping:
                mContext.startActivity(new Intent(getActivity() , ShoppingActivity.class));
                /*intent = new Intent(getActivity(), BannerInfoActivity.class);
                intent.putExtra(Constants.URL, "http://bmall.bilibili.com/");
                intent.putExtra(Constants.TITLE, "Bilibili- 周边商城");
                mContext.startActivity(intent);*/
                break;
        }
    }

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);

                    /*String[] d = result.split(",");
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setProduct_id(null);
                    goodsBean.setName(d[1]);
                    goodsBean.setCover_price(d[2]);
                    goodsBean.setFigure(d[0]);*/

                    /*Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                    startActivity(intent);*/
                    Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(mContext, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                           /* String[] d = result.split(",");
                            GoodsBean goodsBean = new GoodsBean();
                            goodsBean.setProduct_id(null);
                            goodsBean.setName(d[1]);
                            goodsBean.setCover_price(d[2]);
                            goodsBean.setFigure(d[0]);

                            Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                            intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                            startActivity(intent);*/
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        else if (requestCode == REQUEST_CAMERA_PERM) {
            Toast.makeText(mContext, "从设置页面返回...", Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
