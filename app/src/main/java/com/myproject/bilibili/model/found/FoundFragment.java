package com.myproject.bilibili.model.found;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.github.hymanme.tagflowlayout.OnTagClickListener;
import com.github.hymanme.tagflowlayout.TagAdapter;
import com.github.hymanme.tagflowlayout.TagFlowLayout;
import com.github.hymanme.tagflowlayout.tags.ColorfulTagView;
import com.github.hymanme.tagflowlayout.tags.DefaultTagView;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.utils.AppNetConfig;
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
    @BindView(R.id.tag_flow_layout)
    TagFlowLayout tagFlowLayout;

    private LayoutInflater mInflater;
    private List<FoundBean.DataBean.ListBean> list;

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
            public void onError(Call call, Exception e, int id) {
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
                Intent intent = new Intent(getActivity() , TabMoreAcivity.class);
                intent.putExtra(NAME , list.get(position));
                mContext.startActivity(intent);
            }

            @Override
            public void onLongClick(TagFlowLayout parent, View view, int position) {

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
                break;
        }
    }

}
