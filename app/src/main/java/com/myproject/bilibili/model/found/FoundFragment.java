package com.myproject.bilibili.model.found;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chen on 2017/3/21 19:13.
 * 作用:XXXX
 */

public class FoundFragment extends BaseFragment {
    public static final String NAME = "name";
    @BindView(R.id.tags_layout)
    TagFlowLayout tagsLayout;
    @BindView(R.id.hide_tags_layout)
    TagFlowLayout hideTagsLayout;
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
    private LayoutInflater mInflater;

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
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(AppNetConfig.DISCOVER_TAG).build().execute(new StringCallback() {

            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                Log.i("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
//                Log.i("TAG", "onResponse: "+response);
                processData(response);
            }
        });
    }

    private void processData(String json) {

        FoundBean foundBean = JSON.parseObject(json, FoundBean.class);

        List<FoundBean.DataBean.ListBean> list = foundBean.getData().getList();
        final String[] mVals = new String[list.size()];

        for (int i = 0; i < mVals.length; i++) {
            mVals[i] = list.get(i).getKeyword();
        }

        if (list != null && list.size() > 0)

            tagsLayout.setAdapter(new TagAdapter<String>(mVals) {

                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                            tagsLayout, false);
                    tv.setText(s);
                    return tv;
                }
            });

        tagsLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

//                Toast.makeText(getActivity(), mVals[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), TabMoreAcivity.class);
                intent.putExtra(NAME, mVals[position]);
                startActivity(intent);

                return true;
            }
        });
    }

    /*LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) svView.getLayoutParams();
    if (!isOpen) {
        layoutParams.height = DensityUtil.dip2px(getActivity(), 300);
        svView.setLayoutParams(layoutParams);
        tvLoadMore.setText("收起");
        Drawable img = getResources().getDrawable(R.drawable.ic_arrow_up);
        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        tvLoadMore.setCompoundDrawables(img, null, null, null); //设置左图标
        isOpen = true;
    } else {
        layoutParams.height = DensityUtil.dip2px(getActivity(), 180);
        svView.setLayoutParams(layoutParams);
        tvLoadMore.setText("查看更多");
        Drawable img = getResources().getDrawable(R.drawable.ic_arrow_down);
        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        tvLoadMore.setCompoundDrawables(img, null, null, null); //设置左图标
        isOpen = false;
    }*/

    @OnClick({R.id.tv_xingqu_quan, R.id.tv_huatizhong_xin, R.id.tv_huodong_zhong_xin, R.id.tv_yuan_chuang, R.id.tv_quan_qu, R.id.tv_you_xi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_xingqu_quan:
                mContext.startActivity(new Intent(getActivity() , XingquActivity.class));
                break;
            case R.id.tv_huatizhong_xin:
                mContext.startActivity(new Intent(getActivity() , HuaTiActivity.class));
                break;
            case R.id.tv_huodong_zhong_xin:
                mContext.startActivity(new Intent(getActivity() , HuodongActivity.class));
                break;
            case R.id.tv_yuan_chuang:
                mContext.startActivity(new Intent(getActivity() , OriginalActivity.class));
                break;
            case R.id.tv_quan_qu:
                mContext.startActivity(new Intent(getActivity() , OriginalActivity.class));
                break;
            case R.id.tv_you_xi:
                break;
        }
    }



}
