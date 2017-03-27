package com.myproject.bilibili.model.Recommend;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.Recommend.activity.RecommendTagActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chen on 2017/3/21 19:13.
 * 作用:推荐页面
 */

public class RecommendFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.iv_recommend_more)
    ImageView ivRecommendMore;
    private List<BaseFragment> baseFragments;
    private RecommendPageAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_recommend, null);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        initFramgents();
        initListener();
    }

    private void initListener() {
        adapter = new RecommendPageAdapter(getChildFragmentManager(), baseFragments);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initFramgents() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new ZongheFragment());
        baseFragments.add(new DongtaiFragment());
    }

    @OnClick(R.id.iv_recommend_more)
    public void onClick() {
        startActivity(new Intent(getActivity() , RecommendTagActivity.class));
    }
}
